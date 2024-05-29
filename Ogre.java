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
public class Ogre extends MonsterPanel {
    // create random for:
    // health 60-80
    // attack 5-15

    public Ogre(Random rand) {
        super("Ogre", rand.nextInt(20) + 60, rand.nextInt(10) + 10, new ImageIcon("./resources/ogre4.png"));
    }

}
