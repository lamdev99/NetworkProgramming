/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Message;
import model.User;
import rmi.ClientRMI;
import tcpip.ClientTCP;
import ui.LoginFrm;

/**
 *
 * @author lamit
 */
public class ClientControl {
    private LoginFrm loginFrm;
//    private ClientRMI clientRMI;
    private ClientTCP clientTCP;
    public ClientControl() {
        loginFrm = new LoginFrm();
        loginFrm.setVisible(true);
        clientTCP = new ClientTCP();
        loginFrm.setAction(new ButtonLogin());
        clientTCP.receiveData();
    }
    private class ButtonLogin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            User u = loginFrm.getUser();
            System.out.println("1");
            clientTCP.sendData(new Message(u, Message.MesType.SEND_FROM_CLIENT_TCP));
        }
        
    }
    
}
