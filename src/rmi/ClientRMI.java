/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Person;
import model.User;
import utils.Usage;

/**
 *
 * @author lamit
 */
public class ClientRMI {
    RMIInterface rmiInterface;
    Registry registry;

    public ClientRMI() {
        try {
            registry = LocateRegistry.getRegistry(Usage.hostAddress,Usage.port);
            rmiInterface = (RMIInterface) registry.lookup("rmiServer");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getResult(){
        try {
            System.out.println(rmiInterface.checkLogin(new Person("Kienml", 21)));
        } catch (RemoteException ex) {
            Logger.getLogger(ClientRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean checkLogin(User u){
        return u.getUsername().equals("user")&&u.getPassword().equals("pass");
    }
}
