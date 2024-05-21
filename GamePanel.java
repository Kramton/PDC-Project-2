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
    private MonsterPanel monster;
    private int room = 1;
    private final int MAX_ROOM = 5;
    private JLabel roomLabel;
    private Image bgImage;
    
    private JPanel centerPanel;
    private JPanel southPanel;
    
    //game chat log set to static so that all classes can update it
    public static JLabel gameChatLog;
    
    private static boolean userInput = false;
    private static int inputNumber;

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
            monster = new MonsterPanel();
            centerPanel.add(monster);
            
            // Revalidate and repaint to update the UI
            centerPanel.revalidate();
            centerPanel.repaint();
            
            while (!monster.isDead() && !player.isDead()) {
                //game is idle and waits until user chooses to do something?
                if (isUserInput()){
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
                player.setImage("./resources/Dead (10).png");
            }
            else{
                //add item drop logic here later
                
                this.room++;
                this.roomLabel.setText("Room: " + room); 
                gameChatLog.setText("Moving to the next room!");
                this.bgImage = new ImageIcon("./resources/dungeon background "+room+".png").getImage();
                repaint();
            }
            
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
            case 2:
                // Use item
                System.out.println("Using Item");
                SwingUtilities.invokeLater(() -> gameChatLog.setText("Using item"));
                break;
            case 3:
                System.out.println("Show help button pressed");
                SwingUtilities.invokeLater(() -> iHandler.help());
                break;
            case 4:
                // Quit
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
