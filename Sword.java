/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Items;

import java.util.Random;
import javax.swing.ImageIcon;
import main.ItemPanel;
import main.PlayerPanel;

/**
 *
 * @author mark
 */
public class Sword extends ItemPanel {

//    private int attackBoost;
    
    public Sword(Random rand) {
        super("Sword", rand.nextInt(10) + 5, new ImageIcon("./resources/swordItem2.png"));
    }
    
    public Sword(int stat) {
        super("Sword", stat, new ImageIcon("./resources/swordItem2.png"));
    }
    
//    public Sword(Random rand) {
//        super("Sword");
//        this.attackBoost = rand.nextInt(10) + 5;
//    }
    
    @Override
    public void applyEffect(PlayerPanel player) {
        player.changeAttack(this.stat);
    }

}
