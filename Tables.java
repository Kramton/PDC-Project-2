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
import java.util.ArrayList;
import java.util.Random;
import main.GamePanel;
import main.ItemPanel;
import main.Items.*;
import main.MonsterPanel;
import main.Monsters.*;
import main.PlayerPanel;

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
            
            if(!(this.checkExistedTable("GAME"))){
                this.statement.addBatch("CREATE TABLE GAME (GAMEID INT, USERID INT, ROOM INT, MONSTERID INT)");//might add monster ID to save it as well
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
                this.statement.addBatch("CREATE  TABLE PLAYER  (USERID INT, USERNAME VARCHAR(50), PLAYER_HEALTH INT, PLAYER_ATK INT, PLAYER_DEF INT, "
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
                    System.out.println("Inserting item with values: " + i + ", " + item.getName() + ", " + item.getStat());
                    this.statement.executeUpdate("INSERT INTO ITEM VALUES (" + i + ", '" + item.getName() + "', " + item.getStat() + ")");
                }
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
    public void createMonsterTable() {
        try {
            Random rand = new Random();
            this.statement = conn.createStatement();
            
            //if table already exists, do nothing, other wise create a new table
            if(!(this.checkExistedTable("MONSTER"))){
                
                this.statement.executeUpdate("CREATE TABLE MONSTER (MONSTERID INT, MONSTER_NAME VARCHAR(20), MONSTER_HEALTH INT, MONSTER_ATK INT)");
                //1-70 will be ogre and goblin only
                for (int i = 1; i<=70; i++){
                    MonsterPanel[] monsters = {new Goblin(rand), new Ogre(rand)};
                    MonsterPanel monster = monsters[rand.nextInt(monsters.length)];
                    System.out.println("Inserting monster with values: " + i + ", " + monster.getName() + ", " + monster.getHealth() + ", " + monster.getAttack());
                    this.statement.executeUpdate("INSERT INTO MONSTER VALUES (" + i + ", '" + monster.getName() + "', " + monster.getHealth() + ", " + monster.getAttack() + ")");
                }
                //last 30 for bosses
                for (int i = 71; i<=100; i++){
                    MonsterPanel monster = new Boss(rand);
                    System.out.println("Inserting monster with values: " + i + ", " + monster.getName() + ", " + monster.getHealth() + ", " + monster.getAttack());
                    this.statement.executeUpdate("INSERT INTO MONSTER VALUES (" + i + ", '" + monster.getName() + "', " + monster.getHealth() + ", " + monster.getAttack() + ")");
                }
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    
    public GamePanel getSavedGame(String userName) {
 
        ResultSet rs = null;
        GamePanel game = null;
        PlayerPanel player = null;
        MonsterPanel monster = null;
        ItemPanel item = null;
        
        //for getting game, player and monster
        String sql1 = String.format(
            "SELECT * FROM game g, player p, monster m " +
            "WHERE p.USERNAME = '%s' " +
            "AND g.USERID = p.USERID " +
            "AND g.MONSTERID = m.MONSTERID",
            userName
        );
        //for getting items
        String sql2 = String.format(
            "SELECT i.ITEMID, i.ITEM_NAME, i.ITEM_STAT " +
            "FROM player p " +
            "JOIN item i ON (" +
            "p.ITEM_1_ID = i.ITEMID " +
            "OR p.ITEM_2_ID = i.ITEMID " +
            "OR p.ITEM_3_ID = i.ITEMID " +
            "OR p.ITEM_4_ID = i.ITEMID " +
            "OR p.ITEM_5_ID = i.ITEMID) " +
            "WHERE p.USERNAME = '%s'", 
            userName
        );
        
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(sql1);
            
            while (rs.next()) {
                //get all necessary variables
                int gameID = rs.getInt("GAMEID");
                int room = rs.getInt("ROOM");
                int userID = rs.getInt("USERID");
                int pHealth = rs.getInt("PLAYER_HEALTH");
                int pAttack = rs.getInt("PLAYER_ATK");
                int pDefense = rs.getInt("PLAYER_DEF");
                int item1ID = rs.getInt("ITEM_1_ID");
                int item2ID = rs.getInt("ITEM_2_ID");
                int item3ID = rs.getInt("ITEM_3_ID");
                int item4ID = rs.getInt("ITEM_4_ID");
                int item5ID = rs.getInt("ITEM_5_ID");
                int monsterID = rs.getInt("MONSTERID");
                String monsterName = rs.getString("MONSTER_NAME");
                int mHealth = rs.getInt("MONSTER_HEALTH");
                int mAttack = rs.getInt("MONSTER_ATK");
                
                //creating saved instances
                player = new PlayerPanel(userName);
                player.setUserID(userID);
                player.setHealth(pHealth);
                player.setAttack(pAttack);
                player.setDefense(pDefense);
                
                switch(monsterName){
                    case "Ogre":
                        monster = new Ogre(mHealth,mAttack, monsterID);
                        break;
                    case "Goblin":
                        monster = new Goblin(mHealth,mAttack, monsterID);
                        break;
                    case "Boss":
                        monster = new Boss(mHealth,mAttack, monsterID);
                        break;
                }
                game = new GamePanel(userName);
                game.setRoom(room);
                game.setBgImage(room);
                
                game.setMonster(monster);
            }
            
            rs = statement.executeQuery(sql2);
            while (rs.next()) {
                int itemID = rs.getInt("ITEMID");
                String itemName = rs.getString("ITEM_NAME");
                int itemStat = rs.getInt("ITEM_STAT");
                
                switch(itemName){
                    case "Sword":
                        item = new Sword(itemStat, itemID);
                        break;
                    case "Shield":
                        item = new Shield(itemStat, itemID);
                        break;
                    case "Potion":
                        item = new Potion(itemStat, itemID);
                        break;
                }
                player.addItemToInvetory(item);
                
            }
            
            if (game != null){
               game.setPlayer(player); 
            }
            
            
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return game;
    }
   
    
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
                        item = new Shield(stat, i);
                        break;
                    case "Sword":
                        item = new Sword(stat, i);
                        break;
                    case "Potion":
                        item = new Potion(stat, i);
                        break;
                }
                //item.setID(i);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return item;
    }
    
    public MonsterPanel getRandomMonster() {
 
        ResultSet rs = null;
        MonsterPanel monster = null;
        try {
            Random rand = new Random();
            int i = rand.nextInt(70) + 1;
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT MONSTER_NAME, MONSTER_HEALTH, MONSTER_ATK "
                    + "FROM MONSTER "
                    + "WHERE MONSTER.MONSTERID = " + i + "");
            
            while (rs.next()) {
                String title = rs.getString("MONSTER_NAME");
                int health = rs.getInt("MONSTER_HEALTH");
                int atk = rs.getInt("MONSTER_ATK");
                switch(title){
                    case "Ogre":
                        monster = new Ogre(health, atk,i);
                        break;
                    case "Goblin":
                        monster = new Goblin(health, atk,i);
                        break;
                }
                //monster.setID(i);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return monster;
    }
    
    public MonsterPanel getRandomBoss() {
 
        ResultSet rs = null;
        MonsterPanel monster = null;
        try {
            Random rand = new Random();
            int i = rand.nextInt(30) + 71;
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT MONSTER_NAME, MONSTER_HEALTH, MONSTER_ATK "
                    + "FROM MONSTER "
                    + "WHERE MONSTER.MONSTERID = " + i + "");
            
            while (rs.next()) {
                int health = rs.getInt("MONSTER_HEALTH");
                int atk = rs.getInt("MONSTER_ATK");
                monster = new Boss(health, atk,i);
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return monster;
    }
    
    
    
    public int savePlayer(PlayerPanel player){
        int i = 1;
        try {
            this.statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM PLAYER ");
            while (rs.next()) {
                ++i;
            }
            rs.close();
            player.setUserID(i);
            String name = player.getName();
            int health= player.getHealth();
            int attack = player.getAttack();
            int defense = player.getDefense();
            ArrayList<ItemPanel> items = player.getInventory();
            int item1ID = items.size() > 0 ? items.get(0).getID() : -1;
            int item2ID = items.size() > 1 ? items.get(1).getID() : -1;
            int item3ID = items.size() > 2 ? items.get(2).getID() : -1;
            int item4ID = items.size() > 3 ? items.get(3).getID() : -1;
            int item5ID = items.size() > 4 ? items.get(4).getID() : -1;

            String sql = String.format(
                    "INSERT INTO PLAYER VALUES (%d, '%s', %d, %d, %d, %s, %s, %s, %s, %s)",
                    i, name, health, attack, defense,
                    item1ID != -1 ? String.valueOf(item1ID) : "NULL",
                    item2ID != -1 ? String.valueOf(item2ID) : "NULL",
                    item3ID != -1 ? String.valueOf(item3ID) : "NULL",
                    item4ID != -1 ? String.valueOf(item4ID) : "NULL",
                    item5ID != -1 ? String.valueOf(item5ID) : "NULL"
            );
            
            this.statement.executeUpdate(sql);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return i;
    }
    
    public void updateMonster(MonsterPanel monster) {
        String sql = String.format(
            "UPDATE MONSTER SET HEALTH = %d, ATK = %d WHERE MONSTERID = %d",
            monster.getHealth(), monster.getAttack(), monster.getID()
        );

        try {
            this.statement = conn.createStatement();
            this.statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //TO DO: save game is not running correctly
    //"The number of values assigned is not the same as the number of specified or implied columns."
    public void saveGame(PlayerPanel player, int room, MonsterPanel monster){
        int i = savePlayer(player);
        updateMonster(monster);
        
        //gameID will be same as userID
        String sql = String.format(
                //check here
            "INSERT INTO GAME VALUES (%d, %d, %d, %d)",
            i, i, room, monster.getID()
        );
        try{
            this.statement = conn.createStatement();
            this.statement.executeUpdate(sql);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
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
