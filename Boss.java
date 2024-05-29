/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Monsters;

import java.util.Random;
import javax.swing.ImageIcon;
import main.MonsterPanel;

/**
 *
 * @author mark
 */
public class Boss extends MonsterPanel {
    // create random for:
    // health 100-120
    // attack 30-40
    public Boss(Random rand) {
        super("Boss", rand.nextInt(20) + 100, rand.nextInt(10) + 30, new ImageIcon("./resources/ogre4.png"));
    }
    
    public Boss() {
        super();
    }
}
