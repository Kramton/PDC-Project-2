/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author cqm0237
 */
public class Potion extends ItemPanel {

    public Potion(Random rand) {
        super("Potion", rand.nextInt(20) + 20, new ImageIcon("./resources/ogre4.png"));
    }

    public Potion() {
        super();
    }
}
