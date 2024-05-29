/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Monsters;

import java.util.Random;
import main.MonsterPanel;

/**
 *
 * @author mark
 */
public class Ogre extends MonsterPanel{
        // create random for:
    // health 60-80
    // attack 10-20

    public Ogre(Random rand) {
        super("Ogre", rand.nextInt(20) + 60, rand.nextInt(10) + 10);
    }
}
