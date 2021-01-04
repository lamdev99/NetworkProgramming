/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lamit
 */
public class Sinhvien implements Serializable{
    static long serialVersionUID = 1L;
    private int id;
    private String name;
    private String dob;
    private String grade;
    private String hometown;

    public Sinhvien() {
    }
    
    public Sinhvien(int id, String name, String dob, String grade, String hometown) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.grade = grade;
        this.hometown = hometown;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
    public Object[] toObjects(){
        return new Object[]{name,dob,grade,hometown};
    }
}
