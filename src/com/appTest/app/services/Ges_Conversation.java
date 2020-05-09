/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.appTest.app.entities.User;
import com.appTest.app.entities.UserConversation;
import com.appTest.app.utils.Adresse;
import com.appTest.app.utils.Statics;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amine Gongi
 */
public class Ges_Conversation {

    public ArrayList<UserConversation> convs;

    public static Ges_Conversation instance = null;
    public boolean resultOK;
    public int addRes;
    public int loginRes;
    public int actRes;
    String res;
    private ConnectionRequest req;
    String token;

    private Ges_Conversation() {
        req = new ConnectionRequest();
    }

    public static Ges_Conversation getInstance() {
        if (instance == null) {
            instance = new Ges_Conversation();
        }
        return instance;
    }

    public void sendChat(int id, int to, String msg) {
        String url = Statics.BASE_URL+"/user/chatSend?id=" + id + "&to=" + to + "&msg=" + msg;
        System.out.println(url);
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                res = new String(req.getResponseData());
                if (res.contains("ok")) {
                    System.out.println("message teb3ath raj3at ok");
                } else {
                    System.out.println("message non");
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<UserConversation> parseConv(String jsonText) {

        try {
            convs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> publicationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            System.out.println(publicationsListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) publicationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                UserConversation t = new UserConversation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                t.setMsg(obj.get("message").toString());

                try {
                    Map<String, Object> content = (Map<String, Object>) obj.get("sender");
                    User u = new User();
                    u.setId((int) Float.parseFloat(content.get("id").toString()));
                    u.setUsername(content.get("username").toString());
                    u.setImage(content.get("image").toString());
                    u.setNumTel(content.get("numTel").toString());
                    u.setRoles(content.get("roles").toString());

                    t.setSender(u);
                } catch (Exception e) {
                }
                
                try {
                    Map<String, Object> content = (Map<String, Object>) obj.get("receiver");
                    User u = new User();
                    System.out.println(content.get("id"));
                    u.setId((int) Float.parseFloat(content.get("id").toString()));
                    u.setUsername(content.get("username").toString());
                    u.setImage(content.get("image").toString());
                    u.setNumTel(content.get("numTel").toString());
                    u.setRoles(content.get("roles").toString());

                    t.setReceiver(u);
                } catch (Exception e) {
                }
                
                convs.add(t);
            }
        } catch (IOException ex) {

        }
        return convs;
    }
    
    public ArrayList<UserConversation> getConvs(int id) {
        String url = Statics.BASE_URL + "/user/chatList?id=" + id;
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jp = new String(req.getResponseData());
                //System.out.println(jp);
                convs = parseConv(jp);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return convs;
    }
}
