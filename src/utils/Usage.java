/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamit
 */
public class Usage {
    public static String hostAddress = "localhost";
    public static int port = 8080;
    public static int portAlternative = 6868;
    public static int clientPort = 6868;
    public static String dbUrl = "jdbc:mysql//localhost:3306/dbname?useSSL=true";
    public static String dbClass = "com.mysql.jdbc.Driver";
    public static String dbUsername = "root";
    public static String dbPassword = "6868";
    public void connectToDatabase(){
        try {
            Class.forName(dbClass);
            Connection conn = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Usage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
