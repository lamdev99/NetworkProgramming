/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demau.model;

/**
 *
 * @author lamit
 */
public class Message {
    private Object o;
    private MessageType messageType;

    public Message(Object o, MessageType messageType) {
        this.o = o;
        this.messageType = messageType;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
    
    public enum MessageType{
        GET_ALL,
        SEARCH,
        UPDATE,
        UPDATE_SUCCESS,
        UPDATE_FAIL
    }
}
