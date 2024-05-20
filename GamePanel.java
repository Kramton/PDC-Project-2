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
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GamePanel extends JPanel {
    
    private PlayerPanel player;
    private int room = 1;
    private final int MAX_ROOM = 5;
    private JLabel roomLabel;
    private Image bgImage;
    
    private JPanel centerPanel;
    private JPanel southPanel;
    
    //game chat log set to static so that all classes can update it
    public static JLabel gameChatLog;
    
    public static boolean userInput = false;

    public GamePanel(String name) {
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                gameStart();
            }
        }).start();
    }
    
    public void gameStart() {
        
        //main main game loop
        while (!(player.isDead()) && this.room <= MAX_ROOM) {
            
            //remember to randomly spawn, for now just do this
            MonsterPanel monster = new MonsterPanel();
            centerPanel.add(monster);
            
            while (!monster.isDead() && !player.isDead()) {
                
                //game is idle and waits until user chooses to do something?
                if (userInput){
                    int input = MenuPanel.getInputNumber();
                    //don't know if we should handle the input inside MenuPanel or here
                    //or maybe we can use input handler again
                    switch (input){
                        case 1:
                            //combat
                            gameChatLog.setText("Combat happening...");
                            System.out.println("Combat happening");
                            break;
                        case 2:
                            //use item
                            break;
                        case 3: 
                            //show help
                            break;
                            
                        case 4:
                            //quit
                            break;
                  }
                  //after input set to false again to wait for next input
                  userInput = false;
                
                }
            }
        }
        /*
        while (!(player.isDead()) && this.room <= MAX_ROOM) {
            
            Random rand = new Random();
            Scanner scan = new Scanner(System.in);

            System.out.println("You are in room " + this.room);

            //TODO: pick a random monster from the database to spawn

            if (this.room != MAX_ROOM) {
                //spawns goblin or ogre
            } 
            else {
                //spawns boss
            }
            
            //display monster image and monster status
            //centerPanel.add(new MonsterPanel());


            /*
            //combat loop
            while (!monster.isDead() && !player.isDead()) {
                System.out.println("Player turn! ");
                System.out.println(oHandler.printMenu());

                System.out.print("> ");

                try {
                    int choice = scan.nextInt();
                    switch (choice) {

                        case 1:
                            iHandler.pCombat(player, monster);
                            if (!monster.isDead()) {
                                System.out.println(monster.getName() + "'s turn! ");
                                iHandler.mCombat(player, monster);
                            }
                            break;
                        case 2:
                            oHandler.printPlayerStatus(player);
                            break;
                        case 3:
                            oHandler.printPlayerItems(player);
                            break;
                        case 4:
                            boolean isValid = false;
                            int itemIndex = 0;
                            while (!isValid) {
                                System.out.println("Which item number do you want to use (1-5)");

                                if (scan.hasNextInt()) { // Check if the next token is an integer
                                    itemIndex = scan.nextInt();
                                    if (itemIndex > 0 && itemIndex <= 5) {
                                        isValid = true; // Set isValidInput to true to exit the loop
                                    } else {
                                        System.out.println("Invalid input. Please enter 1-5");
                                    }
                                } else {
                                    System.out.println("Invalid input. Please enter again");
                                    scan.next(); // Clear the scanner buffer
                                }
                            }
                            iHandler.useItem(player, itemIndex);
                            break;
                        case 5:
                            //print help menu
                            oHandler.printHelp();
                            break;
                        case 6:
                            //save game
                            fio.write(this);
                            break;
                        case 7:
                            //quit
                            isValid = false;
                            while (!isValid) {
                                System.out.println("Would you like to save before you quit? 1. Yes 2. No");
                                if (scan.hasNextInt()) {
                                    choice = scan.nextInt();
                                    if (choice == 1) {
                                        isValid = true;
                                        fio.write(this);
                                        System.out.println("Thank you for playing the game!");
                                        System.exit(0);
                                    } 
                                    else if (choice == 2) {
                                        isValid = true;
                                        System.out.println("Thank you for playing the game!");
                                        System.exit(0);
                                    } 
                                    else {
                                        System.out.println("Invalid input");
                                    }
                                } 
                                else {
                                    System.out.println("Please enter a number");
                                    scan.next();
                                }
                            }

                            break;
                        default:
                            System.out.println("Please enter numbers (1-7)");
                            break;
                    }

                } 
                catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid option (1-7).\n");
                    scan.nextLine(); // takes in the invalid input.
                }
            }

            if (monster.isDead()) {
                System.out.println("You have defeated " + monster.getName() + "!");
            } 
            else if (player.isDead()) {
                System.out.println("You have been defeated!");
            }

            if (this.room != this.MAX_ROOM && !player.isDead()) {
                Item[] rewards = {new Sword(rand), new Shield(rand), new Potion(rand)};
                Item reward = rewards[rand.nextInt(rewards.length)];
                System.out.println("You have received a " + reward.getName());
                for (int i = 0; i < player.getItems().length; i++) {
                    if (player.getItems()[i] == null) {
                        player.setItems(i, reward);
                        i = player.getItems().length - 1;
                    }
                }
                System.out.println("Moving to the next room...");
            }
            this.room++;
        }*/
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(this.bgImage, 0, 0, null);
    }
}
