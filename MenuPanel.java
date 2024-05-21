/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class MenuPanel extends JPanel{
    
    private JButton attackButton;
    private JButton itemButton;
    private JButton helpButton;
    private JButton quitButton;
    
    //this will send which button was pressed to GamePanel
    private static int inputNumber = 0;
    
    public MenuPanel(){
        this.setLayout(new GridLayout(2, 2));
        attackButton = new JButton("Attack");
        itemButton = new JButton("Item");
        helpButton = new JButton("Help");
        quitButton = new JButton("Quit");
        this.add(attackButton);
        this.add(itemButton);
        this.add(helpButton);
        this.add(quitButton);
        
        // Add action listeners for buttons
        attackButton.addActionListener(e -> performAttack());
        itemButton.addActionListener(e -> useItem());
        helpButton.addActionListener(e -> showHelp());
        quitButton.addActionListener(e -> quitGame());
    }
    
    private void performAttack() {
        GamePanel.setUserInput(true, 1);
    }

    private void useItem() {
        GamePanel.setUserInput(true, 2);
    }

    private void showHelp() {
        GamePanel.setUserInput(true, 3);
    }
    

    private void quitGame() {
        GamePanel.setUserInput(true, 4);
    }
    
}
