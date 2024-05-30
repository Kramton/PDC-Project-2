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
public class Potion extends ItemPanel {
    
//    private int healAmount;
    
    //when populating table
    public Potion(Random rand) {
        super("Potion", rand.nextInt(20) + 20);
    }
    
    
    //when generating random item
    public Potion(int stat, int id) {
        super("Potion", stat, id, new ImageIcon("./resources/potionItem4.png"));
    }
    
//    public Potion(Random rand) {
//        super("Potion");
//        this.stat = rand.nextInt(20) + 20;
//    }
    
    @Override
    public void applyEffect(PlayerPanel player) {
        player.changeHealth(this.stat);
    }
}
