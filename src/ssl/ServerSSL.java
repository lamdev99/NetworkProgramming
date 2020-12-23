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
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import model.Message;
import utils.Usage;

/**
 *
 * @author lamit
 */
public class ServerSSL {
    private SSLSocket ssLSocket;
    private SSLServerSocket ssLServerSocket;
    private SSLServerSocketFactory ssLServerSocketFactory;
    Thread tSend,tReceive;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    public ServerSSL() {
        try {
            ssLServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            ssLServerSocket = (SSLServerSocket) ssLServerSocketFactory.createServerSocket(Usage.port);
            ssLServerSocket.setNeedClientAuth(false);
            ssLServerSocket.setEnabledCipherSuites(ssLServerSocketFactory.getDefaultCipherSuites());
            receiveData();
        } catch (IOException ex) {
            Logger.getLogger(ServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void receiveData(){
        tReceive = new Thread(() ->{
            while(true){
                try {  
                    ssLSocket = (SSLSocket) ssLServerSocket.accept();
                    oos = new ObjectOutputStream(ssLSocket.getOutputStream());
                    ois = new ObjectInputStream(ssLSocket.getInputStream());
                    Object o = ois.readObject();
                    if(o instanceof Message){
                        Message mes = (Message) o;
                        switch (mes.getMesType()) {
                            case SEND_FROM_CLIENT: {
                                mes.setMesType(Message.MesType.SEND_FROM_SERVER);
                                sendMessage(mes);
                                break;
                            }
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException | IOException | ClassNotFoundException ex) {
                    Logger.getLogger(ServerSSL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tReceive.start();
    }
    public void sendMessage(Message mes){
        try {
            oos.writeObject(mes);
        } catch (IOException ex) {
            Logger.getLogger(ServerSSL.class.getName()).log(Level.SEVERE, null, ex);
        }
        tSend.start();
    }
    public static void main(String[] args) {
        new ServerSSL();
    }
}
