/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author cqm0237
 */

import javax.swing.*;
import java.awt.*;
import static java.awt.Font.PLAIN;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import main.Monsters.*;
import main.Items.*;
import main.database.Tables;

public class GamePanel extends JPanel implements Runnable{
    
    private PlayerPanel player;
    private MonsterPanel monster;
    private int room = 1;
    private int gameID;
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
    
    public static boolean loadedGame = false;
    
    Tables tables;

    public GamePanel(String name) {
        
        inventory = new ArrayList<>();
        gameID = 0;
        setLayout(new BorderLayout());
        this.bgImage = new ImageIcon("./resources/dungeon background 1.png").getImage();
        roomLabel = new JLabel("Room " + room);
        Font font = new Font("Serif", PLAIN, 36);
        roomLabel.setFont(font);
        roomLabel.setForeground(Color.WHITE);
        this.add(roomLabel, BorderLayout.NORTH);

        //center panel displays Player (image and name), monster (image, name and stats)
        centerPanel = new JPanel(new GridLayout(1, 2));
        player = new PlayerPanel(name);
        monster = null;
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
        
        tables = new Tables();
        tables.createGameEntryTable();
        tables.createPlayerTable();
        tables.createItemTable();
        tables.createMonsterTable();
    }
    
    
    //main game loop is run as a thread
    @Override
    public void run() {
        gameStart();
    }
    
    public void gameStart() {
        
        //main main game loop
        try {

            while (!(player.isDead()) && this.room <= MAX_ROOM) {
                Random rand = new Random();
                
                if(!loadedGame){
                    if(room == 5){
                    monster = tables.getRandomBoss();
                    }
                    else{
                        monster = tables.getRandomMonster();
                    }
                }
                
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
                    loadedGame = false;

                }

                if (player.isDead()) {
                    gameChatLog.setText("You have been defeated!");
                    player.setImage("./resources/defeated.png");
                } else {
                    // Item drop logic and add to inventory

                    ItemPanel reward = tables.getRandomItem();
                    player.addItemToInvetory(reward);
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
                    setBgImage(room);
                    repaint();
                }

            }
            //when you lose
            if (player.isDead()) {
                this.bgImage = new ImageIcon("./resources/gameover.png").getImage();
            }
            //when you win
            else{
                player.setImage("./resources/victorious.png");
                this.bgImage = new ImageIcon("./resources/youwin.png").getImage();
            }
            
            centerPanel.remove(monster);
            this.remove(southPanel);
            this.remove(roomLabel);
            centerPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = -1;

            //gbc.gridx = 1;
            JButton quitButton = new JButton("Quit");
            gbc.gridy = 1;
            centerPanel.add(quitButton, gbc);

            // Add action listeners
            quitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            centerPanel.revalidate();
            centerPanel.repaint();
            repaint();
            
            
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
            case 3:
                System.out.println("Show help button pressed");
                SwingUtilities.invokeLater(() -> iHandler.help());
                break;
            case 4:
                // Quit no save
                System.exit(0);
                break;
            case 5:
                // Quit save
                gameChatLog.setText("Saving Game...");
                tables.saveGame(player, room, monster);
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
    
    public int getID(){
        return this.gameID;
    }
    
    public JPanel getCenterPanel(){
        return this.centerPanel;
    }
    
    public JPanel getSouthPanel(){
        return this.southPanel;
    }
    
    public void setGameID(int id){
        this.gameID = id;
    }
    public void setPlayer(PlayerPanel player){
        this.player = player;
    }
    public void setMonster(MonsterPanel monster){
        this.monster = monster;
    }
    public void setRoom(int room){
        this.room = room;
    }
    
    public void setRoomLabel(int room){
        this.roomLabel.setText("Room: " + room);
    }
    
    public void setBgImage(int room){
        this.bgImage = this.bgImage = new ImageIcon("./resources/dungeon background " + room + ".png").getImage();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(this.bgImage, 0, 0, null);
    }
}
