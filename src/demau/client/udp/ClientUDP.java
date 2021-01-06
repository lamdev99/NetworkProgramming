/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.client.udp;

import demau.model.Message;
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

/**
 *
 * @author lamit
 */
public class ClientUDP {
    private DatagramSocket clieSocket;
    private DatagramPacket sendPacket,receivePacket;
    ByteArrayOutputStream baos;
    ByteArrayInputStream bais;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    public ClientUDP() {
        try {
            clieSocket = new DatagramSocket(8888);
        } catch (SocketException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendData(Message mes){
        baos = new ByteArrayOutputStream();
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(mes);
            oos.flush();
            InetAddress ia = InetAddress.getByName("localhost"); //lay inetaddress cua localhost
            byte[] sendData = baos.toByteArray();
            sendPacket = new DatagramPacket(sendData, sendData.length,ia,6868); //6868 port cua server
            clieSocket.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Object receiveData(){
        byte[] receiveData = new byte[1024];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);
        try {
            clieSocket.receive(receivePacket);
            bais = new ByteArrayInputStream(receiveData);
            ois = new ObjectInputStream(bais);
            Object o = ois.readObject();
            if(o instanceof Message){
                Message mes = (Message) o;
                switch(mes.getMessageType()){
                    case GET_ALL:{
                        return mes.getO();
                    }
                    case SEARCH:{
                        break;
                    }
                    case UPDATE_FAIL:{
                        break;
                    }
                    case UPDATE_SUCCESS:{
                        break;
                    }
                    default:break;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientUDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
