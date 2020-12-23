/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Message;
import tcpip.ClientTCP;

/**
 *
 * @author lamit
 */
public class ClientControl1 implements Event{
    private RegisterUserFrm registerUserFrm;
    private ClientTCP clientTCP;

    public ClientControl1() {
        registerUserFrm = new RegisterUserFrm();
        registerUserFrm.setVisible(true);
        clientTCP = new ClientTCP(this);
        clientTCP.receiveData();
        registerUserFrm.setAction(new ButtonRegister());
    }

    @Override
    public void setMessage(Message message) {
        boolean isRegisterSuccess = (boolean) message.getContent();
        if(isRegisterSuccess){
            registerUserFrm.showMessage("Register Success");
        }else{
            registerUserFrm.showMessage("Register Fail");
        }
    }
    class ButtonRegister implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            clientTCP.sendData(new Message(registerUserFrm.getModelDe1(), Message.MesType.SEND_FROM_CLIENT_1));
        }
        
    }
    
}
