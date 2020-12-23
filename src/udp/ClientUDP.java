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
import model.Person;
import utils.Usage;

/**
 *
 * @author lamit
 */
public class ClientUDP {

    private DatagramSocket myClient;
    Thread tSend, tReceive;

    public ClientUDP() {
        try {
            myClient = new DatagramSocket(Usage.clientPort);
            sendData(new Message(new Person("Lam", 21), Message.MesType.SEND_FROM_CLIENT));
            receiveData();
        } catch (SocketException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendData(Message mes) {
        tSend = new Thread(() -> {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(mes);
                oos.flush();
                InetAddress IpAddress = InetAddress.getByName(Usage.hostAddress);
                byte[] sendData = baos.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(sendData,
                        sendData.length, IpAddress, Usage.port);
                myClient.send(sendPacket);
            } catch (IOException ex) {
                Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        tSend.start();
    }

    public void receiveData() {
        tReceive = new Thread(() -> {

            try {
                while (true) {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    myClient.receive(receivePacket);
                    ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Object o = ois.readObject();
                    if (o instanceof Message) {
                        Message mes = (Message) o;
                        switch (mes.getMesType()) {
                            case SEND_FROM_SERVER: {
                                Person p = (Person) mes.getContent();
                                System.out.println(p);
                                break;
                            }
                        }
                    }
                    Thread.sleep(1000);
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        tReceive.start();
        
    }

    public static void main(String[] args) {
        new ClientUDP();
    }
}
