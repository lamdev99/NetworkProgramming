/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import model.Message;
import model.Person;
import utils.Usage;

/**
 *
 * @author lamit
 */
public class ClientSSL {
    private SSLSocketFactory sSLSocketFactory;
    private SSLSocket sSLSocket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    Thread tSend,tReceive;
    public ClientSSL() {
        try {
            sSLSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            sSLSocket = (SSLSocket) sSLSocketFactory.createSocket(Usage.hostAddress,Usage.port);
            
            oos = new ObjectOutputStream(sSLSocket.getOutputStream());
            ois = new ObjectInputStream(sSLSocket.getInputStream());
            sendData(new Message(new Person("Lam", 21), Message.MesType.SEND_FROM_CLIENT));
            receiveData();
        } catch (IOException ex) {
            Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void receiveData(){
        tReceive = new Thread(() ->{
           while(true){
               try {
                   Object o = ois.readObject();
                   if(o instanceof Message){
                       Message mes = (Message) o;
                       switch(mes.getMesType()){
                           case SEND_FROM_SERVER:{
                               Person p = (Person) mes.getContent();
                               System.out.println(p);
                               break;
                           }
                       }
                   }
                   Thread.sleep(1000);
               } catch (InterruptedException ex) {
                   Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IOException ex) {
                   Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
               } catch (ClassNotFoundException ex) {
                   Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
               }
           } 
        });
        tReceive.start();
    }
    public void sendData(Message mes){
        tSend = new Thread(() ->{
            try {
                oos.writeObject(mes);
            } catch (IOException ex) {
                Logger.getLogger(ClientSSL.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tSend.start();
    }
    public static void main(String[] args) {
        new ClientSSL();
    }
    
}
