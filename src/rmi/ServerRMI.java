/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import model.Person;
import model.User;
import utils.Usage;

/**
 *
 * @author lamit
 */
public class ServerRMI extends UnicastRemoteObject implements RMIInterface{
    Registry registry;

    public ServerRMI() throws RemoteException {
            registry = LocateRegistry.createRegistry(Usage.portAlternative);
            registry.rebind("rmiServer", this);
    }
    
    @Override
    public boolean checkLogin(Person p) throws RemoteException {
        return p.getName().equals("Lam");
    }
    public static void main(String[] args) throws RemoteException {
        new ServerRMI();
    }

    @Override
    public boolean checkLogin(User u) throws RemoteException {
        if(u.getUsername().equals("user") && u.getPassword().equals("pass")){
            return true;
        }
        return false;
    }
    
}
