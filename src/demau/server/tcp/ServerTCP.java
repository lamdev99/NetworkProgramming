/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.server.tcp;

import demau.model.Message;
import demau.model.Sinhvien;
import demau.server.ServerDao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamit
 */
public class ServerTCP {

    ServerSocket serverSocket;
    Socket cliSockket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    ServerDao serverDao;

    public ServerTCP() {
        try {
            serverDao = new ServerDao();
            serverSocket = new ServerSocket(8080);
            receiveMessage();
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveMessage() {
        try {
            while (true) {
                cliSockket = serverSocket.accept();
                oos = new ObjectOutputStream(cliSockket.getOutputStream());
                ois = new ObjectInputStream(cliSockket.getInputStream());
                while (true) {
                    Object o = ois.readObject();
                    if (o instanceof Message) {
                        Message mesReceive = (Message) o;
                        switch (mesReceive.getMessageType()) {
                            case GET_ALL: {
                                List<Sinhvien> list = serverDao.getAllSinhviens();
                                Message mes = new Message(list, Message.MessageType.GET_ALL);
                                sendMessage(mes);
                                break;
                            }
                            case SEARCH: {
                                List<Sinhvien> list = serverDao.search((String) mesReceive.getO());
                                Message mes = new Message(list, Message.MessageType.SEARCH);
                                sendMessage(mes);
                                break;
                            }
                        }
                    }
                    Thread.sleep(1000);
                }
            }
        } catch (IOException | ClassNotFoundException | InterruptedException ex) {
            try {
                cliSockket.close();
                serverSocket.close();
            } catch (IOException ex1) {
                Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

    }

    public void sendMessage(Message mes) {
        try {
            oos.writeObject(mes);
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
