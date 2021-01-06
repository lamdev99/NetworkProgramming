/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.server;

import demau.model.Monhoc;
import demau.model.Point;
import demau.model.Sinhvien;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamit
 */
public class ServerDao {

    Connection conn;

    public ServerDao() {
        SQLConnection sqlc = new SQLConnection();
        conn = SQLConnection.getConnection();
    }

    public List<Sinhvien> getAllSinhviens() {
        List<Sinhvien> list = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareStatement(SQLConnection.getAllSV);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Sinhvien sv = new Sinhvien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(sv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Sinhvien> search(String s) {
        List<Sinhvien> list = new ArrayList<>();
        PreparedStatement pre;
        try {
            pre = conn.prepareStatement(SQLConnection.searchSV);
            pre.setString(1, "%"+s+"%");
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Sinhvien sv = new Sinhvien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                list.add(sv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Point> getAllInfo(int id){
        List<Point> list = new ArrayList<>();
        try {
            PreparedStatement pre = conn.prepareStatement(SQLConnection.getAllInfo);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                Point p = new Point();
                Monhoc mh = new Monhoc();
                mh.setName(rs.getString(1));
                p.setMh(mh);
                p.setDiemcc(rs.getFloat(2));
                p.setDiemtbkt(rs.getFloat(3));
                p.setDiembtl(rs.getFloat(4));
                p.setDiemkt(rs.getFloat(5));
                p.setId(rs.getInt(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public void update(Sinhvien sv, List<Point> list){
        try {
            PreparedStatement pre = conn.prepareStatement(SQLConnection.updateSV);
            pre.setString(1, sv.getName());
            pre.setString(2, sv.getDob());
            pre.setString(3, sv.getGrade());
            pre.setString(4, sv.getHometown());
            pre.setInt(5, sv.getId());
            pre.executeUpdate();
            
            for(Point p : list){
                PreparedStatement pre1 = conn.prepareStatement(SQLConnection.updatePoint);
                System.out.println(p.toString());
                pre1.setFloat(1, p.getDiemcc());
                pre1.setFloat(2, p.getDiemtbkt());
                pre1.setFloat(3, p.getDiembtl());
                pre1.setFloat(4, p.getDiemkt());
                pre1.setFloat(5, p.getId());
                pre1.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
