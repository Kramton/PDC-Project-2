/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JustD
 */

public final class DBManager {

    /**
     * If you try to connect the database on the server, you must start the
     * server first. Meanwhile, you need to import 'derbyclient.jar' to the
     * libraries.
     */
    //    private static final String URL = "jdbc:derby://localhost:1527/BookStoreDB";
    /**
     * If you try to connect the database embedded in the project, you must stop
     * the server first. Meanwhile, you need to import 'derby.jar' to the
     * libraries.
     */
    private static final String USER_NAME = "group28"; //your DB username
    private static final String PASSWORD = "group28"; //your DB password
//    private static final String URL = "jdbc:derby:RPGDB; create=true";  //url of the DB host
    private static final String URL = "jdbc:derby://localhost:1527/RPGDB";

    Connection conn;

    public DBManager() {
        establishConnection();
    }

    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        System.out.println(dbManager.getConnection());
    }

    public Connection getConnection() {
        return this.conn;
    }

    //Establish connection
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println(URL + " Get Connected Successfully ....");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
