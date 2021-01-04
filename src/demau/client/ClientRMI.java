/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.client;

import demau.RMIInterface;
import demau.model.Point;
import demau.model.Sinhvien;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamit
 */
public class ClientRMI {
    RMIInterface rmii;
    Registry registry;

    public ClientRMI() {
        try {
            registry =  LocateRegistry.getRegistry("localhost",8888);
            rmii = (RMIInterface) registry.lookup("rmiServer");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Sinhvien> getAllSinhvien() throws RemoteException{
        return rmii.getAllSinhvien();
    }
    public List<Sinhvien> search(String s) throws RemoteException {
        return rmii.search(s);
    }
    public List<Point> getAllInfo(int id) throws RemoteException{
        return rmii.getAllInfo(id);
    }
    public void update(Sinhvien sv, List<Point> list) throws RemoteException{
        rmii.update(sv, list);
    }
}
