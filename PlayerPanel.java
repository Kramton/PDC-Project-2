/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author cqm0237
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class PlayerPanel extends JPanel {

    public Image image;

    public PlayerPanel() {
        this.image = new ImageIcon("./resources/Idle (10).png").getImage();
        //this.setPreferredSize(new Dimension(587/2, 707/2));
        //this.image = image.getScaledInstance(587/2, 707/2, Image.SCALE_DEFAULT);
        this.setOpaque(false);
    }

    /**
     * Draw the background of this panel.
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, null);
    }
}
