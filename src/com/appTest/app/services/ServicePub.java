/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.services;

import com.appTest.app.entities.Pub;
import com.appTest.app.entities.Publication;
import com.appTest.app.entities.User;
import com.appTest.app.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmed Fourati
 */
public class ServicePub {
        public static ServicePub instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Pub> pubs;

    public ServicePub() {
     req = new ConnectionRequest();

    }
            public static ServicePub getInstance() {
        if (instance == null) {
            instance = new ServicePub();
        }
        return instance;
    }
            
        public ArrayList<Pub> getPub(int idUserConn){
        String url = Statics.BASE_URL+"/RestoOrg/recApi?" +"id="+idUserConn;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                pubs = parsePubs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return pubs;
    }
        
        
        
    
               public ArrayList<Pub> parsePubs(String jsonText){
                
        try {
            String x= "["+jsonText+"]";
            pubs=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> publicationsListJson = j.parseJSON(new CharArrayReader(x.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)publicationsListJson.get("root");
            int u=0;
                            System.out.println("iter "+ u);

            for(Map<String,Object> obj : list){
                System.out.println("iter "+ u);
                Pub t = new Pub();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);

                t.setTitre(obj.get("titre").toString());
                t.setImage(obj.get("image").toString());
                t.setDescription(obj.get("description").toString());

                pubs.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return pubs;
    }     
    
    
}
