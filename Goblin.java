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
public class Goblin extends MonsterPanel {
        // create random for:
    // health 40-60
    // attack 15-25

    //used when populating table
    public Goblin(Random rand) {
        super("Goblin", rand.nextInt(20) + 40, rand.nextInt(10) + 15);
    }
    
    
    //used when generating random monster
    public Goblin(int health, int atk, int id) {
        super("Goblin", health, atk,id, new ImageIcon("./resources/goblin3.png"));
    }
}
