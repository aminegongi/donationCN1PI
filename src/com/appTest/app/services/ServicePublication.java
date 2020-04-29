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
import com.appTest.app.entities.Publication;
import com.appTest.app.entities.User;
import com.appTest.app.services.Ges_User;
import com.appTest.app.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmed Fourati
 */
public class ServicePublication {
        public ArrayList<Publication> publications;
    
    public static ServicePublication instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePublication() {
         req = new ConnectionRequest();
    }
    
        public static ServicePublication getInstance() {
        if (instance == null) {
            instance = new ServicePublication();
        }
        return instance;
    }
        
            public boolean addPublication(Publication t) {
        String url = Statics.BASE_URL + "/RestoOrg/addPublicationApi?"+"titre="+t.getTitre()+"&description="+t.getDescription()+"&ajoutePar="+t.getAjoutePar()+"&nbrePlat="+t.getNbrePlat();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
        
            public ArrayList<Publication> parsePublications(String jsonText){
                
        try {
            publications=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> publicationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(publicationsListJson);
            List<Map<String,Object>> list = (List<Map<String,Object>>)publicationsListJson.get("root");
            for(Map<String,Object> obj : list){
                Publication t = new Publication();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);

                t.setTitre(obj.get("titre").toString());
                t.setType(obj.get("type").toString());
                t.setDescription(obj.get("description").toString());
                t.setDatePublication(obj.get("datePublication").toString());
                if(obj.get("nbrePlat")!=null)
                t.setNbrePlat((int)Float.parseFloat(obj.get("nbrePlat").toString()));

//                Code hedha a changer 
                String s=obj.get("ajoutePar").toString();
                String requiredString = s.substring(s.indexOf("=") + 1, s.indexOf(","));
                float f = Float.parseFloat(requiredString);
                int i = (int)f ;
                ArrayList<User> users =Ges_User.getInstance().getUserbyId(i);
                t.setAjoutePar(users.get(0));
//                Code hedha a changer 

                
                publications.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return publications;
    }
        
        
        
        public ArrayList<Publication> getAllPublications(){
        String url = Statics.BASE_URL+"/RestoOrg/getAllApi";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                publications = parsePublications(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return publications;
    }
        
}
