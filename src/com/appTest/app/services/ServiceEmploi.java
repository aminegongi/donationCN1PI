/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.services;

import com.appTest.app.entities.Emploi;
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
 * @author hechem
 */
public class ServiceEmploi {

    public ArrayList<Emploi> emplois;

    public static ServiceEmploi instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEmploi() {
        req = new ConnectionRequest();
    }

    public static ServiceEmploi getInstance() {
        if (instance == null) {
            instance = new ServiceEmploi();
        }
        return instance;
    }

    public boolean addEmploi(Emploi t) {
        String url = Statics.BASE_URL + "/Emploi/mobile/add?titre="+t.getTitre()+"&desc="+t.getDescription()+"&tcon="+t.getType_contrat()+"&temp="+t.getType_emploi()+"&sal="+t.getSalaire()+"&emp="+t.getEmplacement()+"&user="+t.getUser().getId()+"&cat="+t.getCategorie()+"&photo="+t.getPhoto();
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

    public boolean editEmploi(Emploi t) {
        String url = Statics.BASE_URL;
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

    public boolean deleteEmploi(Emploi t) {
        String url = Statics.BASE_URL + "/Emploi/mobile/delete/" + t.getId();
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

    public ArrayList<Emploi> parseEmploi(String jsonText) {

        try {

            emplois = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> publicationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            System.out.println(publicationsListJson);
            List<Map<String, Object>> list = (List<Map<String, Object>>) publicationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Emploi t = new Emploi();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);

                t.setTitre(obj.get("titre").toString());
                t.setDescription(obj.get("description").toString());

                t.setPhoto(obj.get("photo").toString());
                t.setEmplacement(obj.get("emplacement").toString());
                
                t.setType_emploi(obj.get("typeDemploi").toString());
                t.setType_contrat(obj.get("typeContrat").toString());
                t.setSalaire( Float.parseFloat(obj.get("salaire").toString()));

////                Code hedha a changer 
//                String s=obj.get("ajoutePar").toString();
//                String requiredString = s.substring(s.indexOf("=") + 1, s.indexOf(","));
//                float f = Float.parseFloat(requiredString);
//                int i = (int)f ;
//                ArrayList<User> users =Ges_User.getInstance().getUserbyId(i);
//                t.setAjoutePar(users.get(0));
////                Code hedha a changer 
                try {
                    Map<String, Object> content = (Map<String, Object>) obj.get("idUtilisateur");
                    User u = new User();
                    u.setId((int) Float.parseFloat(content.get("id").toString()));
                    u.setUsername(content.get("username").toString());
                    u.setNumTel(content.get("numTel").toString());
                    u.setRoles(content.get("roles").toString());
                    u.setImage(content.get("image").toString());
                    
                    t.setUser(u);
                } catch (Exception e) {
                }
                
                try {
                    Map<String, Object> content = (Map<String, Object>) obj.get("idcategorie");
                    t.setCategorie(content.get("titreCategorie").toString());
                    
                } catch (Exception e) {
                }

                emplois.add(t);
            }

        } catch (IOException ex) {

        }
        return emplois;
    }

    public ArrayList<Emploi> getAllEmplois() {
        String url = Statics.BASE_URL + "/Emploi/mobile/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                emplois = parseEmploi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return emplois;
    }
    
    public ArrayList<Emploi> getUnEmplois(int idEmp) {
        String url = Statics.BASE_URL + "/Emploi/mobile/single/" + idEmp;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                emplois = parseEmploi("[" + new String(req.getResponseData()) + "]");
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return emplois;
    }
    
    public ArrayList<Emploi> getUnEmploisEn(int idEmp) {
        String url = Statics.BASE_URL + "/Emploi/mobile/singleEn/" + idEmp;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                emplois = parseEmploi("[" + new String(req.getResponseData()) + "]");
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return emplois;
    }
    
    public ArrayList<Emploi> getMesEmplois(int idUser) {
        String url = Statics.BASE_URL + "/Emploi/mobile/mes/" + idUser;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                emplois = parseEmploi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return emplois;
    }
    
    public boolean pdfEmploi(Emploi t) {
        String url = Statics.BASE_URL + "/Emploi/mobile/pdf/" + t.getId();
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

}
