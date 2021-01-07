/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.client;

import demau.client.udp.ClientUDP;
import demau.model.Message;
import demau.model.Point;
import demau.model.Sinhvien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author lamit
 */
public class ClientControl {
    ClientFrm clientFrm;
    DetailFrm detailFrm;
    ClientUDP clientUDP;
    List<Sinhvien> list = new ArrayList<>();
    public ClientControl(){
        clientUDP = new ClientUDP();
        clientFrm = new ClientFrm();
        detailFrm = new DetailFrm();
        clientFrm.setVisible(true);
        clientUDP.sendData(new Message("get all", Message.MessageType.GET_ALL));
        list = (List<Sinhvien>) clientUDP.receiveData();
        clientFrm.setList(list);
        clientFrm.setAction(new ButtonSearch(),new ChooseOne());
    }
    class ButtonSubmit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sinhvien sv = detailFrm.getSinhvien();
            List<Point> listz = detailFrm.getList();
            
        }
        
    }
    class ChooseOne implements MouseListener{

        @Override
        public void mouseClicked(java.awt.event.MouseEvent me) {
            Sinhvien sv = list.get(clientFrm.getIndex());
            detailFrm.setVisible(true);
            detailFrm.setSinhvien(sv);
            detailFrm.setAction(new ButtonSubmit());
           
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent me) {
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent me) {
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent me) {
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent me) {
        }
        
    }
    class ButtonSearch implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
                clientUDP.sendData(new Message(clientFrm.getText(), Message.MessageType.SEARCH));
                list = (List<Sinhvien>) clientUDP.receiveData();
                clientFrm.setList(list);
           
        }
        
    }
    public static void main(String[] args){
        new ClientControl();
    }
}
