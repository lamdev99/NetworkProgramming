/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpip;

import de1.ServerDao1Impl;
import de1.UserModelDe1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;
import model.User;
import rmi.ServerRMI;
import utils.Usage;

/**
 *
 * @author lamit
 */
public class ServerTCP {

    private ServerRMI serverRMI;
    private ServerSocket serverSocket;
    private Socket cliSocket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    Thread tReceive, tSend;
    //serverRmi
    public ServerTCP(ServerRMI serverRMI) {
        this.serverRMI = serverRMI;
        try {
            serverSocket = new ServerSocket(Usage.port);
            receiveMessage();
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ServerTCP() {
        this.serverRMI = serverRMI;
        try {
            serverSocket = new ServerSocket(Usage.port);
            receiveMessage();
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveMessage() {
        tReceive = new Thread(() -> {

            try {
                while (true) {
                    cliSocket = serverSocket.accept();
                    oos = new ObjectOutputStream(cliSocket.getOutputStream());
                    ois = new ObjectInputStream(cliSocket.getInputStream());
                    while (true) {
                        Object o = ois.readObject();
                        if (o instanceof Message) {
                            Message mes = (Message) o;
                            switch (mes.getMesType()) {
                                case SEND_FROM_CLIENT: {
                                    mes.setMesType(Message.MesType.SEND_FROM_SERVER);
                                    sendMessage(mes);
                                    break;
                                }
                                case SEND_FROM_CLIENT_TCP: {
                                    System.out.println("3");
                                    User u = (User) mes.getContent();
                                    boolean isLoginTrue = serverRMI.checkLogin(u);
                                    sendMessage(new Message(isLoginTrue, Message.MesType.SEND_FROM_SERVER_TCP));
                                    break;
                                }
                                case SEND_FROM_CLIENT_1:{
                                    ServerDao1Impl sdi = new ServerDao1Impl();
                                    sendMessage(new Message(sdi.checkRegister((UserModelDe1) mes.getContent()), Message.MesType.SEND_FROM_SERVER_1));
                                    break;
                                }
                            }
                        }
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException | IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });
        tReceive.start();
    }

    public void sendMessage(Message mes) {
        tSend = new Thread(() -> {
            try {
                oos.writeObject(mes);
            } catch (IOException ex) {
                Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tSend.start();
    }

    public static void main(String[] args) {
//        new ServerTCP();
    }
}
