/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
    protected JLabel itemLabel;

//    public ItemPanel(String name, int stat) {
//        this.name = name;
//        this.stat = stat;
//    }

    public ItemPanel(String name) {
        this.name = name;
    }
    
    public abstract void applyEffect(PlayerPanel player);

    public String getName() {
        return this.name;
    }

    public int getStat() {
        return this.stat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }
    
    public void setImage(String path){
        
        ImageIcon originalIcon = new ImageIcon(path);
        Image image = originalIcon.getImage().getScaledInstance(500, 600, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon scaledIcon = new ImageIcon(image);
        this.itemLabel.setIcon(scaledIcon);
    }

}
