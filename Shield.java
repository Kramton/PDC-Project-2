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
public class Shield extends ItemPanel {
    
    //private int defenseBoost;
    
    public Shield(Random rand) {
        super("Shield", rand.nextInt(10) + 5);
    }
        
    public Shield(int stat, int id) {
        super("Shield", stat, id, new ImageIcon("./resources/shieldItem2.png"));
    }
    
//    public Shield(Random rand) {
//        super("Shield");
//        this.stat = rand.nextInt(10) + 5;
//    }
    
    @Override
    public void applyEffect(PlayerPanel player) {
        player.changeDefense(this.stat);
    }

}
