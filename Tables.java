/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import main.ItemPanel;
import main.Items.*;

/**
 *
 * @author JustD
 */
public class Tables {

    private final DBManager dbManager;
    private final Connection conn;
    private static Statement statement;

    public Tables() {
        dbManager = new DBManager();
        this.conn = dbManager.getConnection();

    }
    
    public void createGameEntryTable() {
        try {
            this.statement = conn.createStatement();
            
            if(!(this.checkExistedTable("PLAYER"))){
                this.statement.addBatch("CREATE TABLE GAME  (GAMEID INT, USERID INT, ROOM INT)");//might add monster ID to save it as well
                this.statement.executeBatch();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void createPlayerTable() {
        try {
            this.statement = conn.createStatement();
            
            if(!(this.checkExistedTable("PLAYER"))){
            
                //when you create this table you shouldn't add any entries to it, but we're doing it to test "load game"
                this.statement.addBatch("CREATE  TABLE PLAYER  (USERID INT, USERNAME VARCHAR(50), HEALTH INT, ATK INT, DEF INT, "
                        + "ITEM_1_ID INT, ITEM_2_ID INT, ITEM_3_ID INT, ITEM_4_ID INT, ITEM_5_ID INT)");

                this.statement.addBatch("INSERT INTO PLAYER VALUES (1, 'Dai', 100, 20, 5, NULL, NULL, NULL, NULL, NULL),\n"
                        + "(2, 'Mark', 50, 40, 10, 1, NULL, 2, NULL, NULL)");

                this.statement.executeBatch();
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void createItemTable() {
        try {
            Random rand = new Random();
            this.statement = conn.createStatement();
            
            if(!(this.checkExistedTable("ITEM"))){
 
                this.statement.executeUpdate("CREATE TABLE ITEM (ITEMID INT, ITEM_NAME VARCHAR(20), ITEM_STAT INT)");
                
                for (int i = 1; i<=100; i++){
                    ItemPanel[] items = {new Sword(rand), new Shield(rand), new Potion(rand)};
                    ItemPanel item = items[rand.nextInt(items.length)];
                    System.out.println("Inserting item with values: " + i + ", " + item.name + ", " + item.stat);
                    this.statement.executeUpdate("INSERT INTO ITEM VALUES (" + i + ", '" + item.name + "', " + item.stat + ")");
                }
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    /*
    public void createMonsterTable() {
        try {
            Random rand = new Random();
            
            this.statement = conn.createStatement();
            
            //if table already exists, do nothing, other wise create a new table
            if(!(this.checkExistedTable("MONSTER"))){
                
                this.statement.addBatch("CREATE TABLE MONSTER (MONSTERID INT, MONSTER_NAME VARCHAR(20), HEALTH INT, ATK INT)");
                //1-70 will be ogre and goblin only
                for (int i = 1; i<=70; i++){
                    MonsterPanel[] monsters = {new Goblin(rand), new Ogre(rand)};
                    MonsterPanel monster = monsters[rand.nextInt(monsters.length)];
                    this.statement.addBatch("INSERT INTO ITEM VALUES (" + i + ",'" +monster.name+"', "+ monster.health + ","+ monster.attack +"),\n");
                }
                //last 30 for bosses
                for (int i = 71; i<=100; i++){
                    MonsterPanel monster = new Boss(rand);
                    this.statement.addBatch("INSERT INTO ITEM VALUES (" + i + ",'" + monster.name +"', "+ monster.health + ","+ monster.attack +"),\n");            }
                /*
                this.statement.addBatch("INSERT INTO ITEM VALUES ('Fiction', 0),\n"
                        + "('Non-fiction', 10),\n"
                        + "('Textbook', 20)");
                this.statement.executeBatch();
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }*/

   
    
    public ItemPanel getRandomItem() {
 
        ResultSet rs = null;
        ItemPanel item = null;
        try {
            Random rand = new Random();
            int i = rand.nextInt(100) + 1;
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT ITEM_NAME, ITEM_STAT "
                    + "FROM ITEM "
                    + "WHERE ITEM.ITEMID = " + i + "");
            
            while (rs.next()) {
                
                String title = rs.getString("ITEM_NAME");
                int stat = rs.getInt("ITEM_STAT");
                switch(title){
                    case "Shield":
                        item = new Shield(stat);
                        break;
                    case "Sword":
                        item = new Sword(stat);
                        break;
                    case "Potion":
                        item = new Potion(stat);
                        break;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return item;
    }

    //returns if table exists or not
    public boolean checkExistedTable(String name) {
        boolean exists = false;
        try {
            DatabaseMetaData dbmd = this.conn.getMetaData();
            String[] types = {"TABLE"};
            statement = this.conn.createStatement();
            ResultSet rs = dbmd.getTables(null, null, null, types);

            while (rs.next()) {
                String table_name = rs.getString("TABLE_NAME");
                System.out.println(table_name + " found");
                if (table_name.equalsIgnoreCase(name)) {
                    /*
                    statement.executeUpdate("Drop table " + name);
                    System.out.println("Table " + name + " has been deleted.");*/
                    System.out.println(table_name + " already exists");
                    exists = true;
                    break;
                }
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return exists;
    }

    public void closeConnection() {
        this.dbManager.closeConnections();
    }

}
