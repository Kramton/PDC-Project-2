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

public class GamePanel extends JPanel {
    private JLabel roomLabel;
    //private JLabel playerStatusLabel;
    //private JLabel monsterStatusLabel;
    private JButton attackButton;
    private JButton itemButton;
    private JButton helpButton;
    private JButton quitButton;
    private Image image;

    public GamePanel() {
        setLayout(new BorderLayout());
        
        this.image = new ImageIcon("./resources/dungeon_background_1.png").getImage();

        roomLabel = new JLabel("Room 1");
        this.add(roomLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        
        PlayerPanel player = new PlayerPanel();
        centerPanel.add(player);
        
        centerPanel.setOpaque(false);
        
        this.add(centerPanel, BorderLayout.CENTER);

        
        JPanel southPanel = new JPanel(new GridLayout(1, 3));
        southPanel.setPreferredSize(new Dimension(1500, 200));
        this.add(southPanel, BorderLayout.SOUTH);
        
        PlayerIconPanel playerIcon = new PlayerIconPanel();
        
        JLabel gameChatLog = new JLabel("Game chat goes here");
        
        JPanel menuPanel = new JPanel(new GridLayout(2, 2));
        attackButton = new JButton("Attack");
        itemButton = new JButton("Item");
        helpButton = new JButton("Help");
        quitButton = new JButton("Quit");
        menuPanel.add(attackButton);
        menuPanel.add(itemButton);
        menuPanel.add(helpButton);
        menuPanel.add(quitButton);
        
        
        southPanel.add(playerIcon);
        southPanel.add(gameChatLog);
        southPanel.add(menuPanel);
        
       
        

        // Add action listeners for buttons
        attackButton.addActionListener(e -> performAttack());
        itemButton.addActionListener(e -> useItem());
        helpButton.addActionListener(e -> showHelp());
        quitButton.addActionListener(e -> quitGame());
    }

    private void performAttack() {
        // Logic for attack
    }

    private void useItem() {
        // Logic for using item
    }

    private void showHelp() {
        // Logic for showing help
    }

    private void quitGame() {
        // Logic for quitting game
        System.exit(0);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
    }
}
