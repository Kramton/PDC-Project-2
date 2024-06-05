/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author cqm0237
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.*;

public class PlayerPanel extends JPanel {

    //public Image image;
    private MenuPanel menuPanel;
    private JPanel playerIcon;
    private String name;
    private JLabel nameLabel;
    private int user_id;
    private int health;
    private int attack;
    private int defense;
    private JLabel playerStatusLabel;
    private JLabel imageLabel;
    
    private ArrayList<ItemPanel> inventory;
    

    public PlayerPanel(String name) {
        
        this.inventory = new ArrayList<>();
        this.menuPanel = new MenuPanel();
        this.setLayout(new BorderLayout());
        //this.image = new ImageIcon("./resources/Idle (10).png").getImage();
        //this.setPreferredSize(new Dimension(587/2, 707/2));
        //this.image = image.getScaledInstance(587/2, 707/2, Image.SCALE_DEFAULT);
        this.setOpaque(false);
        this.user_id = 0;
        this.name = name;
        this.nameLabel = new JLabel(name);
        this.health = 100; // deafult 100
        this.attack = 1000; // default 10
        this.defense = 5; // default 5
        this.playerStatusLabel = new JLabel("<html>Health: " + this.health
                                            + "<br>Attack: " + this.attack 
                                            + "<br>Defense: " + this.defense + "</html>");
        
        ImageIcon icon = new ImageIcon("./resources/icon.png");
        playerIcon = new JPanel();
        playerIcon.add(new JLabel(icon));
        
        ImageIcon originalIcon = new ImageIcon("./resources/idle.png");
        Image image = originalIcon.getImage().getScaledInstance(500, 550, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon scaledIcon = new ImageIcon(image);
        
        Font font = new Font("Serif", PLAIN, 36);
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
       //nameLabel.setVerticalAlignment(JLabel.CENTER);
        
        imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        this.add(imageLabel, BorderLayout.CENTER);
        this.add(nameLabel, BorderLayout.SOUTH);
        
        
    }
    
    public void addItemToInvetory(ItemPanel item) {
        inventory.add(item);
    }
    
    public ItemPanel useItem(int index) {
        if (index >= 0 && index < inventory.size()) {
            return inventory.remove(index);
        }
        
        return null;
    }
    
    public boolean isInventoryEmpty() {
        return inventory.isEmpty();
    }
    
    public ArrayList<ItemPanel> getInventory() {
        return inventory;
    }
    
    public int getInventoryLength() {
        return inventory.size();
    }

    public boolean isDead() {
        if (getHealth() <= 0) {
            return true;
        }
        return false;
    }
    
    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setAttack(int value) {
        this.attack = value;
    }

    public void setDefense(int value) {
        this.defense = value;
    }

    public void setHealth(int value) {
        this.health = value;
    }
    
    public void setUserID(int id){
        this.user_id = id;
    }

    public void changeHealth(int value) {
        this.health += value;
    }

    public void changeAttack(int value) {
        this.attack += value;
    }

    public void changeDefense(int value) {
        
        //if defense increases (i.e uses shield) or the attack of the monster is less than or equal to the defese. then just modify defense.
        if (value > 0) {
            this.defense += value;
        } //if the attack of the monster is greater than current defense, subtract health by the remainder
        else {
            if (this.defense < (-value)){
                changeHealth(this.defense + value);
                int difference = this.defense + value;
                System.out.println("You take " + (-difference) + " damage");
            }
            else if(this.defense >= (-value)){
                System.out.println("Your defense is too high. You don't take damage!");
            }
        }
    }
    
    public int getID(){
        return this.user_id;
    }
    
    public JLabel getPlayerStatusLabel(){
        return this.playerStatusLabel;
    }
    
    public JLabel getImageLabel(){
        return this.imageLabel;
    }
    
    public JPanel getPlayerIcon(){
        return this.playerIcon;
    }
    
    public void setImage(String path){
        
        ImageIcon originalIcon = new ImageIcon(path);
        Image image = originalIcon.getImage().getScaledInstance(500, 600, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon scaledIcon = new ImageIcon(image);
        this.imageLabel.setIcon(scaledIcon);
    }
    
    public void updateStatusLabel() {
        this.playerStatusLabel.setText("<html>Health: " + this.health
                                + "<br>Attack: " + this.attack
                                + "<br>Defense: " + this.defense + "</html>");
        revalidate();
        repaint();
    }
    
    //don't need to print since we're using label image icon and add it to panel
    /*
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
    }*/
}
