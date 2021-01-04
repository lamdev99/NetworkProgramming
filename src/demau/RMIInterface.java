/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau;

import demau.model.Point;
import demau.model.Sinhvien;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author lamit
 */
public interface RMIInterface extends Remote{
    List<Sinhvien> getAllSinhvien() throws RemoteException;
    List<Sinhvien> search(String name) throws RemoteException;
    List<Point> getAllInfo(int id) throws RemoteException;
    void update(Sinhvien sv, List<Point> list) throws RemoteException;
}
