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
public class Goblin extends MonsterPanel {
        // create random for:
    // health 40-60
    // attack 15-25

    public Goblin(Random rand) {
        super("Goblin", rand.nextInt(20) + 40, rand.nextInt(10) + 15);
    }
}
