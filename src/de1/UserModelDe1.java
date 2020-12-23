/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de1;

import java.io.Serializable;

/**
 *
 * @author lamit
 */
public class UserModelDe1 implements Serializable{
    static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String address;
    private String birthday;
    private String sex;
    private String des;

    public UserModelDe1(int id, String username, String password, String address, String birthday, String sex, String des) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
        this.sex = sex;
        this.des = des;
    }

    public UserModelDe1(String username, String password, String address, String birthday, String sex, String des) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.birthday = birthday;
        this.sex = sex;
        this.des = des;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    
}
