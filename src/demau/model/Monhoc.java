/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.model;

import java.io.Serializable;

/**
 *
 * @author lamit
 */
public class Monhoc implements Serializable{
    static long serialVersionUID = 1L;
    private int id;
    private String name;
    private int numtc;
    private String des;

    public Monhoc() {
    }
    
    public Monhoc(int id, String name, int numtc, String des) {
        this.id = id;
        this.name = name;
        this.numtc = numtc;
        this.des = des;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumtc() {
        return numtc;
    }

    public void setNumtc(int numtc) {
        this.numtc = numtc;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    public Object[] toObjects(){
        return new Object[]{name,numtc,des};
    }
    
}
