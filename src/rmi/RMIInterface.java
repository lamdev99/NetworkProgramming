/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Person;
import model.User;

/**
 *
 * @author lamit
 */
public interface RMIInterface extends Remote{
    public boolean checkLogin(Person p) throws RemoteException;
    public boolean checkLogin(User u) throws RemoteException;
}
