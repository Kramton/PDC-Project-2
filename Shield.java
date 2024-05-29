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
public class Shield extends ItemPanel {
    
    private int defenseBoost;
    
    public Shield(Random rand) {
        super("Shield");
        this.defenseBoost = rand.nextInt(10) + 5;
    }
    
    @Override
    public void applyEffect(PlayerPanel player) {
        player.changeDefense(defenseBoost);
    }

}
