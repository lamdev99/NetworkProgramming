/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author lamit
 */
public class Message implements Serializable{
    static final long serialVersionUID = 1L;
    private Object content;
    private MesType mesType;

    public Message(Object content, MesType mesType) {
        this.content = content;
        this.mesType = mesType;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public MesType getMesType() {
        return mesType;
    }

    public void setMesType(MesType mesType) {
        this.mesType = mesType;
    }
    
    public enum MesType{
        SEND_FROM_CLIENT,
        SEND_FROM_SERVER,
        SEND_FROM_CLIENT_TCP,
        SEND_FROM_SERVER_TCP,
        SEND_FROM_CLIENT_1,
        SEND_FROM_SERVER_1
    }
}
