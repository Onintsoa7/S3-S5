/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

/**
 *
 * @author Chan Kenny
 */
public class ConnectionPs {
    public static Connection connexionPostgreSQL() {
        String user = "postgres";
         String password = "2004";
        Connection connect = null;
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/meuble", user, password);
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return connect;
    }
}
