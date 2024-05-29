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
public class Boss extends MonsterPanel {

    // create random for:
    // health 60-80
    // attack 15-25
    public Boss(Random rand) {
        super("Boss", rand.nextInt(20) + 100, rand.nextInt(10) + 30,new ImageIcon("./resources/ogre4.png"));
    }
    
    public Boss() {
        super();
    }
}
