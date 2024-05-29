/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.Items;

import java.util.Random;
import main.ItemPanel;
import main.PlayerPanel;

/**
 *
 * @author mark
 */
public class Potion extends ItemPanel {
    
    private int healAmount;
    
    public Potion(Random rand) {
        super("Potion");
        this.healAmount = rand.nextInt(20) + 20;
    }
    
    @Override
    public void applyEffect(PlayerPanel player) {
        player.changeHealth(healAmount);
    }
}
