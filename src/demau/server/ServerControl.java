/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.server;

import demau.server.tcp.ServerTCP;
import java.rmi.RemoteException;

/**
 *
 * @author lamit
 */
public class ServerControl {
    public static void main(String[] args) throws RemoteException {
        ServerTCP serverTCP = new ServerTCP();
    }
}
