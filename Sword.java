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
public class Sword extends ItemPanel {

    //Random rand = new Random();
    public Sword(Random rand) {
        super("Sword", rand.nextInt(10) + 5, new ImageIcon("./resources/ogre4.png"));
    }

    public Sword() {
        super();
    }

}
