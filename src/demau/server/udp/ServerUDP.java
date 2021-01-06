/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.server.udp;

import demau.model.Message;
import demau.model.Sinhvien;
import demau.server.ServerDao;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamit
 */
public class ServerUDP {
    DatagramSocket serverSocket;
    DatagramPacket sendPacket,receivePacket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    ByteArrayInputStream bais;
    ByteArrayOutputStream baos;
    ServerDao serverDao;
    public ServerUDP() {
        try {
            serverSocket = new DatagramSocket(6868);
            serverDao = new ServerDao();
            receiveData();
        } catch (SocketException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void receiveData(){
        while(true){
            byte[] receiveData = new byte[1024];
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            try {
                serverSocket.receive(receivePacket);
                bais = new ByteArrayInputStream(receiveData);
                ois = new ObjectInputStream(bais);
                Object o = ois.readObject();
                if(o instanceof Message){
                    System.out.println("Receive Data from : "+receivePacket.getAddress()+" "+receivePacket.getPort());
                    Message mes = (Message) o;
                    switch(mes.getMessageType()){
                        case GET_ALL:{
                            List<Sinhvien> list = serverDao.getAllSinhviens();
                            Message mesSend = new Message(list, Message.MessageType.GET_ALL);
                            sendData(mesSend);
                            break;
                        }
                        case SEARCH:{
                            String s = (String) mes.getO();
                            List<Sinhvien> list = serverDao.search(s);
                            Message mesSend = new Message(list, Message.MessageType.SEARCH);
                            sendData(mesSend);
                            break;
                        }
                        case UPDATE:{
                            break;
                        }
                        default:break;
                    }
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }
    public void sendData(Message mes){
        baos = new ByteArrayOutputStream();
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(mes);
            oos.flush();
            InetAddress ia = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            byte[] sendData = baos.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length,ia,clientPort);
            serverSocket.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
