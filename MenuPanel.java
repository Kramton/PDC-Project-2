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
        GamePanel.userInput = true;
        // Logic for attack...
        inputNumber = 1;
    }

    private void useItem() {
        GamePanel.userInput = true;
        // Logic for use item...
        inputNumber = 2;
    }

    private void showHelp() {
        GamePanel.userInput = true;
        // Logic for showing help
      
        GamePanel.gameChatLog.setText("<html>=============================== HELP =============================="
                   + "<br> + Defeat the monster in each room to advance to the next level!"
                   + "<br> + After clearing the room, you will receive a random item."
                   + "<br> + Defeat the Boss at room 5 to beat the game!"
                   + "<br> + Use swords to increase your damage!"
                   + "<br> + Use shields to increase your defense!"
                   + "<br> + Use potions to heal yourself!"
                   + "<br> + Don't forget to save game!"
                   + "<br> ===================================================================</html>");
        inputNumber = 3;
    }
    

    private void quitGame() {
        // Logic for quitting game
        System.exit(0);
    }
    
    public static int getInputNumber(){
        return inputNumber;
    }
    
}
