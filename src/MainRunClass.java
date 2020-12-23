
import control.ClientControl;
import control.ServerControl;
import java.rmi.RemoteException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lamit
 */
public class MainRunClass {
    public static void main(String[] args) throws RemoteException {
//        new ServerControl();
        ClientControl clientControl = new ClientControl();
    }
    
}
