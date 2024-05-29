/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.PLAIN;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;

/**
 *
 * @author ADMIN
 */


public abstract class MonsterPanel extends JPanel{
    
    //public Image image;
    
    protected String name;
    protected int health;
    protected int attack;
    protected JLabel monsterStatusLabel;
    protected JLabel nameLabel;
    ImageIcon originalIcon;

   
    public MonsterPanel(String name, int health, int attack, ImageIcon originalIcon) {
        
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.name = name;
        nameLabel = new JLabel(name);
        this.health = health;
        this.attack = attack;
        //when child class instantiates pass in corresponding image
        this.originalIcon = originalIcon;
        Image image = originalIcon.getImage().getScaledInstance(400, 600, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon scaledIcon = new ImageIcon(image);
        
        this.monsterStatusLabel = new JLabel("<html>Health: " + this.health
                                            + "<br>Attack: " + this.attack + "</html>");
        
        this.add(monsterStatusLabel, BorderLayout.NORTH);
        
        Font font = new Font("Serif", PLAIN, 36);
        monsterStatusLabel.setFont(font);
        monsterStatusLabel.setForeground(Color.WHITE);
        monsterStatusLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        //nameLabel.setVerticalAlignment(JLabel.CENTER);
        
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        this.add(imageLabel, BorderLayout.CENTER);
        this.add(nameLabel, BorderLayout.SOUTH);
    }
    
    //just to test before we can connect to database
    public MonsterPanel() {
        this.setLayout(new BorderLayout());
        //this.image = new ImageIcon("./resources/Dead (10).png").getImage();
        this.setOpaque(false);
        this.name = "Monster";
        nameLabel = new JLabel(name);
        this.health = 50;
        this.attack = 10;
        this.monsterStatusLabel = new JLabel("<html>Health: " + this.health
                                            + "<br>Attack: " + this.attack + "</html>");
        this.add(monsterStatusLabel, BorderLayout.NORTH);
        
        //ImageIcon image = new ImageIcon("./resources/Dead (10).png");
        
        originalIcon = new ImageIcon("./resources/ogre4.png");
        Image image = originalIcon.getImage().getScaledInstance(400, 600, Image.SCALE_SMOOTH); // Adjust size as needed
        ImageIcon scaledIcon = new ImageIcon(image);
        
        Font font = new Font("Serif", PLAIN, 36);
        monsterStatusLabel.setFont(font);
        monsterStatusLabel.setForeground(Color.WHITE);
        monsterStatusLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(font);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        //nameLabel.setVerticalAlignment(JLabel.CENTER);
        
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.BOTTOM);
        
        this.add(imageLabel, BorderLayout.CENTER);
        this.add(nameLabel, BorderLayout.SOUTH);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setHealth(int value) {
        this.health = value;
    }

    public void setAttack(int value) {
        this.attack = value;
    }
    
    public void updateStatusLabel() {
        monsterStatusLabel.setText("<html>Health: " + this.health
                                    + "<br>Attack: " + this.attack + "</html>");
        revalidate();
        repaint();
    }
    
    //what if we don't draw the image itself but just add it as a panel
    /*
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
        g.setColor(Color.white);
        Font font = new Font("Serif", PLAIN, 36);
        g.setFont(font);
        g.drawString(name, 300, 300);
    }*/
}
