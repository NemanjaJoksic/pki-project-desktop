/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nemanja
 */
public class DB {

    private static DB instance = null;

    private Connection connection;

    protected DB() {
        try {
            String url = "jdbc:sqlite:pkidb2.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    /*
    *   Before destroying object close connection to db
    */
    @Override
    public void finalize() throws Throwable {
        super.finalize();
        if (connection != null) {
            connection.close();
        }
    }

}
