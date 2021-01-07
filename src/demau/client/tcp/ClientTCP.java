/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.client.tcp;

import demau.model.Message;
import demau.model.Sinhvien;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamit
 */
public class ClientTCP {
    Socket cliSocket;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public ClientTCP() {
        try {
            cliSocket = new Socket("localhost",8080);
            oos = new ObjectOutputStream(cliSocket.getOutputStream());
            ois = new ObjectInputStream(cliSocket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendMessage(Message mes){
        try {
            oos.writeObject(mes);
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Object receiveMessage(){
        try {
            Object o = ois.readObject();
            if(o instanceof Message){
                Message mes = (Message) o;
                switch(mes.getMessageType()){
                    case GET_ALL:{
                        List<Sinhvien> list = (List<Sinhvien>) mes.getO();
                        return list;
                    }
                    case SEARCH:{
                        List<Sinhvien> list = (List<Sinhvien>) mes.getO();
                        return list;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
