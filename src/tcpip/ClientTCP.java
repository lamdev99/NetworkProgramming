/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpip;

import de1.Event;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Message;
import model.Person;
import model.User;
import rmi.ServerRMI;
import utils.Usage;

/**
 *
 * @author lamit
 */
public class ClientTCP {
    private Socket clientSocket;
    Thread tSend,tReceive;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    Event event;
    public ClientTCP(Event event){
        this.event =event;
        try {
            clientSocket = new Socket(Usage.hostAddress,Usage.port);
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
//            sendData(new Message(new Person("Lam", 21), Message.MesType.SEND_FROM_CLIENT));
//            receiveData();
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ClientTCP() {
        try {
            clientSocket = new Socket(Usage.hostAddress,Usage.port);
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
//            sendData(new Message(new Person("Lam", 21), Message.MesType.SEND_FROM_CLIENT));
//            receiveData();
        } catch (IOException ex) {
            Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendData(Message mesSend){
        tSend = new Thread(() -> {
            try {
                oos.writeObject(mesSend);
                System.out.println("2");
            } catch (IOException ex) {
                Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        tSend.start();
    }
    public void receiveData(){
        tReceive = new Thread(() ->{
           while(true){
               try {
                   Object oReceive = ois.readObject();
                   if(oReceive instanceof Message){
                       Message mes = (Message) oReceive;
                       switch(mes.getMesType()){
                           case SEND_FROM_SERVER:{
                               Person p = (Person) mes.getContent();
                               System.out.println(p);
                               break;
                           }
                           case SEND_FROM_SERVER_TCP:{
                               boolean isLoginSuccess = (boolean) mes.getContent();
                               System.out.println(isLoginSuccess);
                               break;
                           }
                           case SEND_FROM_SERVER_1:{
                               event.setMessage(mes);
                               break;
                           }
                       }
                   }
                   Thread.sleep(1000);
               } catch (IOException | ClassNotFoundException | InterruptedException ex) {
                   Logger.getLogger(ClientTCP.class.getName()).log(Level.SEVERE, null, ex);
               }
           } 
        });
        tReceive.start();
        
    }
    public static void main(String[] args) {
        new ClientTCP();
    }
    
}
