/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BorderLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */
public abstract class ItemPanel extends JPanel{

    protected String name;
    protected int stat;
    protected int itemID;
    
    ImageIcon originalIcon;
    
        // TODO: itemID
//    public ItemPanel(int itemID, String name, int stat, ImageIcon originalIcon) {
    
    //this constructor is called when generating a random item
    public ItemPanel(String name, int stat, int itemID, ImageIcon originalIcon) {
        this.setOpaque(false);
        this.itemID = itemID;
        this.name = name;
        this.stat = stat;
        this.originalIcon = originalIcon;
        Image image = originalIcon.getImage().getScaledInstance(200, 400, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon scaledIcon = new ImageIcon(image);
        
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        this.add(imageLabel, BorderLayout.CENTER);
        
        
    }
    
    //this constructor is called when populating the table
    public ItemPanel(String name, int stat) {
        this.name = name;
        this.stat = stat;
    }
    
    public abstract void applyEffect(PlayerPanel player);

    public String getName() {
        return this.name;
    }

    public int getStat() {
        return this.stat;
    }
    
    public int getID() {
        return this.itemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
    
    public void setID(int id){
        this.itemID = id;
    }
    
    /*
    public void setImage(String path){
        
        ImageIcon originalIcon = new ImageIcon(path);
        Image image = originalIcon.getImage().getScaledInstance(500, 600, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon scaledIcon = new ImageIcon(image);
        this.itemLabel.setIcon(scaledIcon);
    }
    */
}


