/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.rmi.RemoteException;
import rmi.ServerRMI;
import tcpip.ServerTCP;

/**
 *
 * @author lamit
 */
public class ServerControl {

    public ServerControl() throws RemoteException {
        ServerRMI serverRMI = new ServerRMI();
        ServerTCP serverTCP = new ServerTCP(serverRMI);
    }
    public static void main(String[] args) throws RemoteException {
         new ServerControl();
    }
}
