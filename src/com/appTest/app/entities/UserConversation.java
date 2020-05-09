/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.entities;

import com.appTest.app.entities.User;
import java.util.Date;

/**
 *
 * @author Ahmed Fourati
 */
public class UserConversation {
    private int id ; 
    private Date dateEnvoi;
    private String msg ; 
    private User sender;
    private User receiver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(Date dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public UserConversation() {
    }
    
    public UserConversation(int id, Date dateEnvoi, String msg, User sender, User receiver) {
        this.id = id;
        this.dateEnvoi = dateEnvoi;
        this.msg = msg;
        this.sender = sender;
        this.receiver = receiver;
    }

    public UserConversation(Date dateEnvoi, String msg, User sender, User receiver) {
        this.dateEnvoi = dateEnvoi;
        this.msg = msg;
        this.sender = sender;
        this.receiver = receiver;
    }

    public UserConversation(Date dateEnvoi, String msg, User receiver) {
        this.dateEnvoi = dateEnvoi;
        this.msg = msg;
        this.receiver = receiver;
    }

    public UserConversation(String msg, User receiver) {
        this.msg = msg;
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "UserConversation{" + "id=" + id + ", dateEnvoi=" + dateEnvoi + ", msg=" + msg + ", sender=" + sender + ", receiver=" + receiver + '}';
    }
    
    
    
    
}
