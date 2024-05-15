/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author cqm0237
 */

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;

public class PlayerIconPanel extends JPanel {

    public Image image;

    public PlayerIconPanel() {
        this.image = new ImageIcon("./resources/PlayerIcon (1).png").getImage();
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
