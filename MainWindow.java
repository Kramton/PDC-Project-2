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
import main.database.Tables;

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
        
        //text field asking for username
        Panel createUser = new Panel();
        JTextField textField = new JTextField("Choose Your Username", 20);
        JButton buttonSubmit = new JButton("Submit");
        createUser.add(textField);
        createUser.add(buttonSubmit);
        
        mainPanel.add(createUser, "Create User");
        cardLayout.show(mainPanel, "Create User");
        
        //creates a new user/player with the submitted username
        buttonSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePanel gamePanel = new GamePanel(textField.getText().trim());
                //gamePanel.gameStart();
                mainPanel.add(gamePanel, "Game");
                cardLayout.show(mainPanel, "Game");
                Thread thread= new Thread(gamePanel);
                thread.start();
            }
        });
    }

    private void loadGame() {
        // Logic to load a game
        //retrieve player with username from database
        //if there is no player with that username create a new player
        //text field asking for username
        Panel loadUser = new Panel();
        JTextField textField = new JTextField("Enter your saved username", 20);
        JButton buttonLoad = new JButton("Load");
        loadUser.add(textField);
        loadUser.add(buttonLoad);
        
        mainPanel.add(loadUser, "Load User");
        cardLayout.show(mainPanel, "Load User");
        
        //creates a new user/player with the submitted username
        buttonLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = textField.getText().trim();
                GamePanel gamePanel = new Tables().getSavedGame(userName);
                GamePanel.loadedGame = true;
                
                //if game doesnt exist, create a new game
                if(gamePanel == null){
                    SwingUtilities.invokeLater(() -> {
                        JLabel messageLabel = new JLabel("User doesn't exist! Creating new game...");
                        loadUser.add(messageLabel);
                        loadUser.revalidate();
                        loadUser.repaint();
                    });

                    // Allow player to read message
                    new Timer(2000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("Playerdoes not exist");
                            GamePanel.loadedGame = false;
                            GamePanel newGamePanel = new GamePanel(userName);
                            mainPanel.add(newGamePanel, "Game");
                            cardLayout.show(mainPanel, "Game");
                            Thread thread= new Thread(newGamePanel);
                            thread.start();
                            ((Timer) e.getSource()).stop();
                        }
                    }).start();
                }
                else{
                    mainPanel.add(gamePanel, "Game");
                    cardLayout.show(mainPanel, "Game");
                    Thread thread= new Thread(gamePanel);
                    thread.start();
                }
            }
        });
    }

    public static void main(String[] args) {
        
        /*
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainWindow window = new MainWindow();
                window.setVisible(true);
            }
        });*/
        MainWindow window = new MainWindow();
        window.setVisible(true);
        
//        Tables tables = new Tables();
//        
//        tables.createGameEntryTable();
//        tables.createPlayerTable();
//        tables.createItemTable();
////        tables.createMonsterTable();
//        
//        tables.closeConnection();
    }
}