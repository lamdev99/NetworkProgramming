/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.server;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamit
 */
public class SQLConnection {

    private static Connection conn;

    public SQLConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/networkpro", "root", "6868");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Connection getConnection(){
        return conn;
    }
    public static String getAllSV = "select * from tblsinhvien";
    public static String searchSV = "select * from tblsinhvien where name like ?";
    public static String getAllInfo =
                    "select s.name,p.diemcc,p.diemtbkt,p.diembtl,p.diemkt,p.id from tblpoint as p\n" +
                    "inner join tblsinhvien as sv on p.idsv = sv.id\n" +
                    "inner join tblsubject as s on s.id = p.ids\n" +
                    "where sv.id = ?";
    public static String updateSV = "update tblsinhvien set name = ?,dob = ?,grade=?,hometown=? where id = ?";
    public static String updatePoint = "update tblpoint set diemcc = ?, diemtbkt=?,diembtl=?,diemkt=? where id = ?";
}
