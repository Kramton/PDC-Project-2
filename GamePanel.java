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
    private JLabel playerStatusLabel;
    private JLabel monsterStatusLabel;
    private JButton attackButton;
    private JButton itemButton;
    private JButton helpButton;
    private JButton quitButton;

    public GamePanel() {
        setLayout(new BorderLayout());

        roomLabel = new JLabel("Room 1");
        this.add(roomLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2));
        playerStatusLabel = new JLabel("Player");
        monsterStatusLabel = new JLabel("Monster");
        centerPanel.add(playerStatusLabel);
        centerPanel.add(monsterStatusLabel);
        this.add(centerPanel, BorderLayout.CENTER);

        JPanel menuPanel = new JPanel(new GridLayout(1, 4));
        attackButton = new JButton("Attack");
        itemButton = new JButton("Item");
        helpButton = new JButton("Help");
        quitButton = new JButton("Quit");
        menuPanel.add(attackButton);
        menuPanel.add(itemButton);
        menuPanel.add(helpButton);
        menuPanel.add(quitButton);
        add(menuPanel, BorderLayout.SOUTH);

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
    }
}
