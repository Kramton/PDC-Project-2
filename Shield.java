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
public class Shield extends ItemPanel {

    public Shield(Random rand) {
        super("Shield", rand.nextInt(10) + 5, new ImageIcon("./resources/ogre4.png"));
    }

    public Shield() {
        super();
    }
}
