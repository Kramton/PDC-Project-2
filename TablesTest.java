/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.database;

import main.GamePanel;
import main.ItemPanel;
import main.MonsterPanel;
import main.PlayerPanel;
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
public class TablesTest {
    
    public TablesTest() {
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
     * Test of getSavedGame method, of class Tables.
     * If exists, game should return the same game ID
     */
    @Test
    public void testGetSavedGame() {
        System.out.println("getSavedGame");
        String userName = "test_save_3";
        Tables instance = new Tables();
        int expResult = 3;
        GamePanel resultGame = instance.getSavedGame(userName);
        int result = resultGame.getID();
        assertEquals(expResult, result);
        
    }

    /**
     * Testing check existed tables
     */
    @Test
    public void testCheckExistedTable() {
        System.out.println("checkExistedTable");
        String name = "PLAYER1";
        Tables instance = new Tables();
       
        boolean result = instance.checkExistedTable(name);
        assertFalse(result);
    }
    
}
