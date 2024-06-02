/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import main.Items.Sword;
import main.Monsters.Ogre;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JustD
 */
public class InputHandlerTest {
    
    public InputHandlerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Testing combat mechanism, of class InputHandler.
     */
    @Test
    public void testPCombat() {
        System.out.println("pCombat");
        PlayerPanel player = new PlayerPanel("Test");
        MonsterPanel monster = new Ogre(100,10,0);
        InputHandler instance = new InputHandler();
        instance.pCombat(player, monster);
        int result = monster.getHealth();
        ArrayList<Integer> expResults = new ArrayList<>();
        expResults.add(90);
        expResults.add(80);
        expResults.add(70);
        assertTrue(expResults.contains(result));
    }
    
    @Test
    public void testMCombat() {
        System.out.println("mCombat");
        PlayerPanel player = new PlayerPanel("Test");
        MonsterPanel monster = new Ogre(100,10,0);
        InputHandler instance = new InputHandler();
        instance.mCombat(player, monster);
        
        //10 attack -5 shield
        int expResult = 95;
        int result = player.getHealth();
        assertEquals(expResult,result);
    }

    
    /**
     * Testing using items, of class InputHandler.
     */
    @Test
    public void testUseItem() {
        System.out.println("useItem");
        PlayerPanel player = new PlayerPanel("Test");
        ItemPanel item = new Sword(10,0);
        InputHandler instance = new InputHandler();
        instance.useItem(player, item);
        
        int expResult = 20;
        int result = player.getAttack();
        
        assertEquals(expResult,result);
    }

    
}
