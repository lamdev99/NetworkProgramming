/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de1;

import database.ConnectDatabase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamit
 */
public class ServerDao1Impl implements ServerDao1{

    @Override
    public boolean checkRegister(UserModelDe1 umd) {
        boolean registerSuccess = false;
        try {
            PreparedStatement pre = ConnectDatabase.getConnection().prepareStatement(checkExist);
            pre.setString(1, umd.getUsername());
            ResultSet rs = pre.executeQuery();
            if(!rs.next()){
                pre = ConnectDatabase.getConnection().prepareStatement(addUser);
                pre.setString(1, umd.getUsername());
                pre.setString(2, umd.getPassword());
                pre.setString(3, umd.getAddress());
                pre.setString(4, umd.getBirthday());
                pre.setString(5, umd.getSex());
                pre.setString(6, umd.getDes());
                pre.executeUpdate();
                registerSuccess = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerDao1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registerSuccess;
    }
    
}
interface ServerDao1{
    String checkExist = "select * from tbluser where username = ?";
    String addUser = "insert into tbluser(username,password,address,birthday,sex,description) values(?,?,?,?,?,?)";
    boolean checkRegister(UserModelDe1 umd);
}