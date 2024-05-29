/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author cqm0237
 */
import main.Items.*;
import javax.swing.*;
import java.awt.*;
import static java.awt.Font.PLAIN;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import main.Monsters.*;
import main.database.Tables;

public class GamePanel extends JPanel {
    
    private PlayerPanel player;
    private MonsterPanel monster;
    private int room = 1;
    private final int MAX_ROOM = 5;
    private JLabel roomLabel;
    private JLabel itemLabel;
    private Image bgImage;
    private Image item;
    private ArrayList<ItemPanel> inventory;
    
    private JPanel centerPanel;
    private JPanel southPanel;
    
    //game chat log set to static so that all classes can update it
    public static JLabel gameChatLog;
    
    private static boolean userInput = false;
    private static int inputNumber;
    
    Tables tables;

    public GamePanel(String name) {
        
        inventory = new ArrayList<>();
        
        setLayout(new BorderLayout());
        this.bgImage = new ImageIcon("./resources/dungeon_background_1.png").getImage();
        roomLabel = new JLabel("Room " + room);
        Font font = new Font("Serif", PLAIN, 36);
        roomLabel.setFont(font);
        roomLabel.setForeground(Color.WHITE);
        this.add(roomLabel, BorderLayout.NORTH);

        //center panel displays Player (image and name), monster (image, name and stats)
        centerPanel = new JPanel(new GridLayout(1, 2));
        player = new PlayerPanel(name);
        centerPanel.add(player);
        centerPanel.setOpaque(false);
        this.add(centerPanel, BorderLayout.CENTER);

        //South Panel will hold Player Icon, Player Stats, Game Chat Log and Menu
        southPanel = new JPanel(new GridLayout(1, 4));
        southPanel.setPreferredSize(new Dimension(1500, 200));
        gameChatLog = new JLabel("Game chat goes here");
        MenuPanel menuPanel = new MenuPanel();
        southPanel.add(player.getPlayerIcon());
        southPanel.add(player.getPlayerStatusLabel());
        southPanel.add(gameChatLog);
        southPanel.add(menuPanel);
        this.add(southPanel, BorderLayout.SOUTH);
        
        // Start a new thread to handle the loop, otherwise it wont run 
        /*A separate thread is started to handle the loop action. 
        This prevents the main event dispatch thread (EDT) from being blocked, 
        which is crucial to keep the GUI responsive. - Chat GPT*/
        
        
        tables = new Tables();
        
        tables.createGameEntryTable();
        tables.createPlayerTable();
        tables.createItemTable();
//        tables.createMonsterTable();
        
//        tables.closeConnection();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                gameStart();
            }
        }).start();
    }
    
    public void gameStart() {
        
        //main main game loop
        try {

            while (!(player.isDead()) && this.room <= MAX_ROOM) {
                Random rand = new Random();

//            remember to randomly spawn, for now just do this
//            Goblin goblin = new Goblin(rand);
//            Ogre ogre = new Ogre(rand);
//            Boss boss = new Boss(rand);
//            
//            MonsterPanel[] monsters = {goblin, ogre, boss};
//            System.out.println(monsters);
                monster = new Goblin(rand);
                centerPanel.add(monster);

                // Revalidate and repaint to update the UI
                centerPanel.revalidate();
                centerPanel.repaint();

                while (!monster.isDead() && !player.isDead()) {
                    //game is idle and waits until user chooses to do something?
                    if (isUserInput()) {
                        int input = getInputNumber();
                        handleUserInput(input);
                        resetUserInput();
                    }
                }

                if (monster.isDead()) {
                    //System.out.println("You have defeated " + monster.getName() + "!");

                    centerPanel.remove(monster);

                }

                if (player.isDead()) {
                    gameChatLog.setText("You have been defeated!");
                    player.setImage("./resources/defeated.png");
                } else {
                    // Item drop logic and add to inventory
//                    ItemPanel[] rewards = {new Sword(rand), new Shield(rand), new Potion(rand)};
//                    ItemPanel reward = rewards[rand.nextInt(rewards.length)];

                    ItemPanel reward = tables.getRandomItem();
                    player.addItemToInvetory(reward);

//                    String imagePath = "";
//                    switch (reward.getName()) {
//                        case "Potion":
//                            imagePath = "./resources/test3.png";
//                            break;
//                        case "Shield":
//                            imagePath = "./resources/test.png";
//                            break;
//                        case "Sword":
//                            imagePath = "./resources/test2.png";
//                            break;
//                    }

                    // Adding the image to the centerPanel
//                    if (!imagePath.isEmpty()) {
//                        
//                    }
//                    itemLabel = new JLabel(new ImageIcon(imagePath));
                    centerPanel.add(reward);
                        
                        
                        
                    // update UI to show item drop
                    gameChatLog.setText("You have received a " + reward.getName() + "!");
                    centerPanel.revalidate();
                    centerPanel.repaint();
                    
                    Thread.sleep(1500);

                    centerPanel.remove(reward);

                    this.room++;
                    this.roomLabel.setText("Room: " + room);
                    gameChatLog.setText("Moving to the next room!");
                    this.bgImage = new ImageIcon("./resources/dungeon background " + room + ".png").getImage();
                    repaint();
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void handleUserInput(int input) {
        InputHandler iHandler = new InputHandler();
        switch (input) {
            case 1:
                iHandler.pCombat(player, monster);

                if (!monster.isDead()) {
                    System.out.println(monster.getName() + "'s turn! ");
                    iHandler.mCombat(player, monster);
                }
                break;
            case 21:
                // Use item
                ItemPanel item1 = player.useItem(0);
                if (item1 != null) {
                    iHandler.useItem(player, item1);
                    gameChatLog.setText("Using item: " + item1.getName());
                } else {
                    gameChatLog.setText("No item to use in slot 1.");
                }
                break;
            case 22:
                // Use item
                ItemPanel item2 = player.useItem(1);
                if (item2 != null) {
                    iHandler.useItem(player, item2);
                    gameChatLog.setText("Using item: " + item2.getName());
                } else {
                    gameChatLog.setText("No item to use in slot 2.");
                }
                break;
            case 23:
                // Use item
                ItemPanel item3 = player.useItem(2);
                if (item3 != null) {
                    iHandler.useItem(player, item3);
                    gameChatLog.setText("Using item: " + item3.getName());
                } else {
                    gameChatLog.setText("No item to use in slot 3.");
                }
                break;
            case 24:
                // Use item
                ItemPanel item4 = player.useItem(3);
                if (item4 != null) {
                    iHandler.useItem(player, item4);
                    gameChatLog.setText("Using item: " + item4.getName());
                } else {
                    gameChatLog.setText("No item to use in slot 4.");
                }
                break;
            case 25:
                // Use item
                ItemPanel item5 = player.useItem(4);
                if (item5 != null) {
                    iHandler.useItem(player, item5);
                    gameChatLog.setText("Using item: " + item5.getName());
                } else {
                    gameChatLog.setText("No item to use in slot 5.");
                }
                break;
//            case 21:
//                // Use item
//                iHandler.useItem(player, 1);
//                SwingUtilities.invokeLater(() -> gameChatLog.setText("Using item 1"));
//                
//                break;
//            case 22:
//                // Use item
//                iHandler.useItem(player, 2);
//                SwingUtilities.invokeLater(() -> gameChatLog.setText("Using item 2"));
//                break;
//            case 23:
//                // Use item
//                iHandler.useItem(player, 3);
//                SwingUtilities.invokeLater(() -> gameChatLog.setText("Using item 3"));
//                break;
//            case 24:
//                // Use item
//                iHandler.useItem(player, 4);
//                SwingUtilities.invokeLater(() -> gameChatLog.setText("Using item 4"));
//                break;
//            case 25:
//                // Use item
//                iHandler.useItem(player, 5);
//                SwingUtilities.invokeLater(() -> gameChatLog.setText("Using item 5"));
//                break;
            case 3:
                System.out.println("Show help button pressed");
                SwingUtilities.invokeLater(() -> iHandler.help());
                break;
            case 4:
                // Quit no save
                System.exit(0);
                break;
            case 5:
                // Quit no save
                gameChatLog.setText("Saving Game...");
                try {
                    Thread.sleep(1000); // Simulate saving game
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
                break;
            default:
                break;
        }
    }
    
    //avoid race condition with synchronized methods, only 1 thread can submit it's input
    public synchronized static void setUserInput(boolean input, int number) {
        userInput = input;
        inputNumber = number;
    }
    
    private synchronized boolean isUserInput() {
        return userInput;
    }
    
    private synchronized int getInputNumber() {
        return inputNumber;
    }

    private synchronized void resetUserInput() {
        userInput = false;
        inputNumber = 0;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(this.bgImage, 0, 0, null);
    }
}
