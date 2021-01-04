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
public class Point implements Serializable{
    static long serialVersionUID = 1L;
    private int id;
    private Sinhvien sv;
    private Monhoc mh;
    private float diemcc;
    private float diemtbkt;
    private float diembtl;
    private float diemkt;

    public Point() {
    }
    
    public Point(int id, Sinhvien sv, Monhoc mh, float diemcc, float diemtbkt, float diembtl, float diemkt) {
        this.id = id;
        this.sv = sv;
        this.mh = mh;
        this.diemcc = diemcc;
        this.diemtbkt = diemtbkt;
        this.diembtl = diembtl;
        this.diemkt = diemkt;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        Point.serialVersionUID = serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Sinhvien getSv() {
        return sv;
    }

    public void setSv(Sinhvien sv) {
        this.sv = sv;
    }

    public Monhoc getMh() {
        return mh;
    }

    public void setMh(Monhoc mh) {
        this.mh = mh;
    }

    public float getDiemcc() {
        return diemcc;
    }

    public void setDiemcc(float diemcc) {
        this.diemcc = diemcc;
    }

    public float getDiemtbkt() {
        return diemtbkt;
    }

    public void setDiemtbkt(float diemtbkt) {
        this.diemtbkt = diemtbkt;
    }

    public float getDiembtl() {
        return diembtl;
    }

    public void setDiembtl(float diembtl) {
        this.diembtl = diembtl;
    }

    public float getDiemkt() {
        return diemkt;
    }

    public void setDiemkt(float diemkt) {
        this.diemkt = diemkt;
    }
    public Object[] toObjects(){
        return new Object[]{mh.getName(),diemcc,diemtbkt,diembtl,diemkt};
    }

    @Override
    public String toString() {
        return "Point{" + "diemcc=" + diemcc + ", diemtbkt=" + diemtbkt + ", diembtl=" + diembtl + ", diemkt=" + diemkt + '}';
    }
    
}
