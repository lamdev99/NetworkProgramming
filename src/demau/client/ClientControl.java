/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.client;

import demau.model.Point;
import demau.model.Sinhvien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.events.MouseEvent;

/**
 *
 * @author lamit
 */
public class ClientControl {
    ClientFrm clientFrm;
    DetailFrm detailFrm;
    ClientRMI clientRMI;
    List<Sinhvien> list = new ArrayList<>();
    public ClientControl() throws RemoteException {
        clientRMI = new ClientRMI();
        clientFrm = new ClientFrm();
        detailFrm = new DetailFrm();
        clientFrm.setVisible(true);
        list = clientRMI.getAllSinhvien();
        clientFrm.setList(list);
        clientFrm.setAction(new ButtonSearch(),new ChooseOne());
    }
    class ButtonSubmit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            Sinhvien sv = detailFrm.getSinhvien();
            List<Point> listz = detailFrm.getList();
            try {
                clientRMI.update(sv, listz);
            } catch (RemoteException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            detailFrm.dispose();
            try {
                list = clientRMI.getAllSinhvien();

                clientFrm.setList(list);
            } catch (RemoteException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    class ChooseOne implements MouseListener{

        @Override
        public void mouseClicked(java.awt.event.MouseEvent me) {
            Sinhvien sv = list.get(clientFrm.getIndex());
            detailFrm.setVisible(true);
            detailFrm.setSinhvien(sv);
            detailFrm.setAction(new ButtonSubmit());
            try {
                detailFrm.setList(clientRMI.getAllInfo(sv.getId()));
            } catch (RemoteException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                list = clientRMI.search(clientFrm.getText());
                clientFrm.setList(list);
            } catch (RemoteException ex) {
                Logger.getLogger(ClientControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public static void main(String[] args) throws RemoteException {
        new ClientControl();
    }
}
