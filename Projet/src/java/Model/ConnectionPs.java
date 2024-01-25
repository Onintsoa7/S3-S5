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
        String password = "1767";
        Connection connect = null;
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/meuble", user, password);
            connect.setAutoCommit(false);
            System.out.println("openned " + connect);
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
        return connect;
    }

    public static void isFloat(String str) throws Exception {
        try {
            Float.parseFloat(str);
        } catch (NumberFormatException e) {
            throw new Exception("valeur non correcte");
        }
    }

    public static void isNotNegative(String str) throws Exception {
        try {
            float floatValue = Float.valueOf(str);
            if (floatValue < 0) {
                throw new Exception("valeur non correcte");
            }
        } catch (Exception e) {
            throw e; // Re-throw the caught exception
        }
    }

    public static void isNotEmpty(String str) throws Exception {
        try {
            if (str.isEmpty()) {
                throw new Exception("valeur non correcte");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
