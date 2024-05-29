/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author ADMIN
 */

//borrowed from Item class in V1
public abstract class ItemPanel extends JPanel{
    
    protected String name;
    protected int stat;
    ImageIcon originalIcon;

    public ItemPanel(String name, int stat, ImageIcon originalIcon) {
        this.name = name;
        this.stat = stat;
        this.originalIcon = originalIcon;
    }

    public ItemPanel() {
        this.name = null;
        this.stat = 0;
    }

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
    
}
