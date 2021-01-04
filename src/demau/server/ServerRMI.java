/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.server;

import demau.RMIInterface;
import demau.model.Point;
import demau.model.Sinhvien;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamit
 */
public class ServerRMI extends UnicastRemoteObject implements RMIInterface{
    Registry registry;
    ServerDao serverDao;
    public ServerRMI() throws RemoteException{
        serverDao = new ServerDao();
        try {
            registry = LocateRegistry.createRegistry(8888);
            registry.rebind("rmiServer", this);
        } catch (RemoteException ex) {
            Logger.getLogger(ServerRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Sinhvien> getAllSinhvien() throws RemoteException {
        return this.serverDao.getAllSinhviens();
    }

    @Override
    public List<Sinhvien>search(String name) throws RemoteException {
        return this.serverDao.search(name);
    }

    @Override
    public List<Point> getAllInfo(int id) throws RemoteException {
        return this.serverDao.getAllInfo(id);
    }

    /**
     *
     * @param sv
     * @param list
     */
    @Override
    public void update(Sinhvien sv, List<Point> list) {
        this.serverDao.update(sv, list);
    }
    
}
