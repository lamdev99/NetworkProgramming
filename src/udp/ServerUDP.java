/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;
import utils.Usage;

/**
 *
 * @author lamit
 */
public class ServerUDP {

    private DatagramSocket serverSocket;
    private DatagramPacket receivePacket;
    Thread tSend, tReceive;
    ByteArrayInputStream bais;
    ByteArrayOutputStream baos;
    public ServerUDP() {
        try {
            serverSocket = new DatagramSocket(Usage.port);
            receiveData();
        } catch (SocketException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receiveData() {
        tReceive = new Thread(() -> {
            while (true) {
                try {
                    byte[] receiveData = new byte[1024];
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    serverSocket.receive(receivePacket);
                    bais = new ByteArrayInputStream(receiveData);
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Object o = ois.readObject();
                    if (o instanceof Message) {
                        Message mes = (Message) o;
                        switch (mes.getMesType()) {
                            case SEND_FROM_CLIENT: {
                                mes.setMesType(Message.MesType.SEND_FROM_SERVER);
                                sendData(mes);
                                break;
                            }
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tReceive.start();
    }

    public void sendData(Message mes) {
        tSend = new Thread(() -> {

            ObjectOutputStream oos = null;
            try {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(mes);
                oos.flush();
                InetAddress IpAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                byte[] sendData = baos.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(sendData,
                        sendData.length, IpAddress, clientPort);
                serverSocket.send(sendPacket);
            } catch (IOException ex) {
                Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    public static void main(String[] args) {
        new ServerUDP();
    }
}
