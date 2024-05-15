/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author cqm0237
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainWindow() {
        setTitle("Escape Dungeon");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create menu panel
        JPanel mainMenuPanel = createMenuPanel();

        // Add panels to main panel
        mainPanel.add(mainMenuPanel, "Menu");

        // Add main panel to frame
        this.add(mainPanel);

        // Show the menu panel initially
        cardLayout.show(mainPanel, "Menu");
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        JLabel titleLabel = new JLabel("Escape Dungeon");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        JButton newGameButton = new JButton("New Game");
        gbc.gridy = 1;
        panel.add(newGameButton, gbc);

        JButton loadGameButton = new JButton("Load Game");
        gbc.gridy = 2;
        panel.add(loadGameButton, gbc);

        JButton quitButton = new JButton("Quit");
        gbc.gridy = 3;
        panel.add(quitButton, gbc);

        // Add action listeners
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return panel;
    }

    private void startNewGame() {
        GamePanel gamePanel = new GamePanel();
        mainPanel.add(gamePanel, "Game");
        cardLayout.show(mainPanel, "Game");
    }

    private void loadGame() {
        // Logic to load a game
    }

    public static void main(String[] args) {
        /*
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });*/
        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
}