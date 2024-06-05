/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public class MenuPanel extends JPanel{
    
    private CardLayout cardLayout;
    
    private JPanel mainPanel;
    private JPanel itemPanel;
    private JPanel quitPanel;
    
    
    private JButton attackButton;
    private JButton itemButton;
    private JButton helpButton;
    private JButton quitButton;
    
    
    private JButton item1Button;
    private JButton item2Button;
    private JButton item3Button;
    private JButton item4Button;
    private JButton item5Button;
    
    private JButton quitSave;
    private JButton quitNoSave;
    
    private JButton itemBackButton;
    private JButton quitBackButton;
    
    //this will send which button was pressed to GamePanel
    private static int inputNumber = 0;
    
    public MenuPanel(){
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        attackButton = new JButton("Attack");
        itemButton = new JButton("Item");
        helpButton = new JButton("Help");
        quitButton = new JButton("Quit");

        item1Button = new JButton("Slot 1");
        item2Button = new JButton("Slot 2");
        item3Button = new JButton("Slot 3");
        item4Button = new JButton("Slot 4");
        item5Button = new JButton("Slot 5");
        
        quitSave = new JButton("Save and Quit");
        quitNoSave = new JButton("Quit Without Saving");
        
        itemBackButton = new JButton("Back");
        quitBackButton = new JButton("Back");
        
        mainPanel = new JPanel(new GridLayout(2, 2));
        mainPanel.add(attackButton);
        mainPanel.add(itemButton);
        mainPanel.add(helpButton);
        mainPanel.add(quitButton);
        
        itemPanel = new JPanel(new GridLayout(2,3));
        itemPanel.add(item1Button);
        itemPanel.add(item2Button);
        itemPanel.add(item3Button);
        itemPanel.add(item4Button);
        itemPanel.add(item5Button);
        itemPanel.add(itemBackButton);
        
        
        quitPanel = new JPanel(new GridLayout(2, 2));
        quitPanel.add(quitSave);
        quitPanel.add(quitNoSave);
        quitPanel.add(quitBackButton);
        
        
        
        // Add main panel to frame
        this.add(mainPanel, "Main");
        this.add(quitPanel, "Quit");
        this.add(itemPanel, "Items");
        
        
        // Show the menu panel initially
        cardLayout.show(this, "Main");
        
        // Add action listeners for buttons
        attackButton.addActionListener(e -> performAttack());
        itemButton.addActionListener(e -> showItems());
        helpButton.addActionListener(e -> showHelp());
        quitButton.addActionListener(e -> showQuit());
        
        item1Button.addActionListener(e -> useItem1());
        item2Button.addActionListener(e -> useItem2());
        item3Button.addActionListener(e -> useItem3());
        item4Button.addActionListener(e -> useItem4());
        item5Button.addActionListener(e -> useItem5());
        
        quitNoSave.addActionListener(e -> quitNoSave());
        quitSave.addActionListener(e -> quitSave());
        
        itemBackButton.addActionListener(e -> back());
        quitBackButton.addActionListener(e -> back());
        
    }
    
    
    public void updateItemButton(int slot, String itemName, int stat) {
        switch (slot) {
            case 0:
                item1Button.setText("<html> " + itemName + " <br> " + stat + "</html>");
                break;
            case 1:
                item2Button.setText("<html> " + itemName + " <br> " + stat + "</html>");
                break;
            case 2:
                item3Button.setText("<html> " + itemName + " <br> " + stat + "</html>");
                break;
            case 3:
                item4Button.setText("<html> " + itemName + " <br> " + stat + "</html>");
                break;
            case 4:
                item5Button.setText("<html> " + itemName + " <br> " + stat + "</html>");
                break;
        }

//        if (slot == 1) {
//            item1Button.setText(itemName);
//        }
//        else if (slot == 2) {
//            item2Button.setText(itemName);
//        }        
//        else if (slot == 3) {
//            item3Button.setText(itemName);
//        }
//        else if (slot == 4) {
//            item4Button.setText(itemName);
//        }
//        else if (slot == 5) {
//            item5Button.setText(itemName);
//        }
    }
    
    private void performAttack() {
        GamePanel.setUserInput(true, 1);
    }

    private void showItems() {
        //GamePanel.setUserInput(true, 2);
        cardLayout.show(this, "Items");
    }
    
    private void useItem1() {
        GamePanel.setUserInput(true, 21);
        //item1Button.setText("Slot 1");
        //cardLayout.show(this, "Items");
    }
    private void useItem2() {
        GamePanel.setUserInput(true, 22);
        //item2Button.setText("Slot 2");
        //cardLayout.show(this, "Items");
    }
    private void useItem3() {
        GamePanel.setUserInput(true, 23);
        //item3Button.setText("Slot 3");
        //cardLayout.show(this, "Items");
    }
    private void useItem4() {
        GamePanel.setUserInput(true, 24);
        //item4Button.setText("Slot 4");
        //cardLayout.show(this, "Items");
    }
    private void useItem5() {
        GamePanel.setUserInput(true, 25);
        //item5Button.setText("Slot 5");
        //cardLayout.show(this, "Items");
    }

    private void showHelp() {
        GamePanel.setUserInput(true, 3);
    }

    private void showQuit() {
        //GamePanel.setUserInput(true, 4);
        cardLayout.show(this, "Quit");
    }
    
    private void quitNoSave() {
        GamePanel.setUserInput(true, 4);
    }
    
    private void quitSave() {
        GamePanel.setUserInput(true, 5);
    }
    
    private void back(){
        cardLayout.show(this, "Main");
    }
    
    private static void setItemImage(ImageIcon image, String imagePath) {
        image = new ImageIcon(imagePath);
    }
    
}
