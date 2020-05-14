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

import com.appTest.app.entities.DemandeAide;
import com.appTest.app.entities.User;
import com.appTest.app.gui.FLogIns_gui;

import com.appTest.app.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hedi
 */
public class ServiceDemandeAide {
    
    public ArrayList<DemandeAide> demandes;
    
    public static ServiceDemandeAide instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceDemandeAide() {
         req = new ConnectionRequest();
    }

    public static ServiceDemandeAide getInstance() {
        if (instance == null) {
            instance = new ServiceDemandeAide();
        }
        return instance;
    }
    
    public boolean addDemandeAide(DemandeAide demandeAide) {
        String url = Statics.BASE_URL + "/aide/demandeaide/add?" + "user="+demandeAide.getIdUser()+"&photo="+demandeAide.getPhoto()+"&titre="+demandeAide.getTitre()+"&description="+demandeAide.getDescription()+"&categorie="+demandeAide.getIdCategorie()+"&etat="+demandeAide.getEtat()+"&nbreaction="+demandeAide.getNbReaction(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
        public ArrayList<DemandeAide> parseDemandes(String jsonText){
        try {
            
            
         
            // a afficher
            demandes =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> demandesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            //List<Map<String,Object>> list = (List<Map<String,Object>>)demandesListJson.get("demandeAides");
            List<Map<String,Object>> list = (List<Map<String,Object>>)demandesListJson.get("demandeWithStatus");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                DemandeAide dmnd = new DemandeAide();
                //float id = Float.parseFloat(obj.get("id").toString());
                
                //les obj JSON tnm ta9rahom java ken fi map 
                
                 Map<String,Object> demandeObj = (Map<String,Object>)obj.get("demande");
                 float id = Float.parseFloat(demandeObj.get("id").toString());
                 
                 
                
                 Map<String,Object> categorieObj = (Map<String,Object>)demandeObj.get("idCategorie");
                 float idCat = Float.parseFloat(categorieObj.get("id").toString());
                
                 Map<String,Object> userObj = (Map<String,Object>)demandeObj.get("idUser");
                 float idUser = Float.parseFloat(userObj.get("id").toString());
                
                float nbReaction = Float.parseFloat(obj.get("nbReaction").toString());
                
                dmnd.setId((int)id);
                dmnd.setIdCategorie((int)idCat);
                dmnd.setIdUser((int)idUser);
                dmnd.setTitre(demandeObj.get("titre").toString());
                dmnd.setDescription(demandeObj.get("description").toString());
                dmnd.setPhoto(demandeObj.get("photo").toString());
                //dmnd.setPhoto(dmnd.getPhoto());
                //System.out.println(obj.get("photo").toString());
                dmnd.setEtat(demandeObj.get("etat").toString());
               // dmnd.setIdCategorie( categorie.);
                dmnd.setStatus(obj.get("status").toString());
                dmnd.setNbReaction((int)nbReaction);
                
                /*
                t.setId((int)id);
                t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                t.setName(obj.get("name").toString());
                */
                //Ajouter la tâche extraite de la réponse Json à la liste
                demandes.add(dmnd);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return demandes;
    }
        
        
                public ArrayList<DemandeAide> parseMesDemandes(String jsonText){
        try {
            
            
         
            // a afficher
            demandes =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> demandesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            //List<Map<String,Object>> list = (List<Map<String,Object>>)demandesListJson.get("demandeAides");
            List<Map<String,Object>> list = (List<Map<String,Object>>)demandesListJson.get("demandeAides");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                DemandeAide dmnd = new DemandeAide();
                //float id = Float.parseFloat(obj.get("id").toString());
                
                //les obj JSON tnm ta9rahom java ken fi map 
                
                 Map<String,Object> demandeObj = (Map<String,Object>)obj.get("demande");
                 float id = Float.parseFloat(demandeObj.get("id").toString());
                 
                 
                
                 Map<String,Object> categorieObj = (Map<String,Object>)demandeObj.get("idCategorie");
                 float idCat = Float.parseFloat(categorieObj.get("id").toString());
                
                 Map<String,Object> userObj = (Map<String,Object>)demandeObj.get("idUser");
                 float idUser = Float.parseFloat(userObj.get("id").toString());
                
                float nbReaction = Float.parseFloat(obj.get("nbReaction").toString());
                
                dmnd.setId((int)id);
                dmnd.setIdCategorie((int)idCat);
                dmnd.setIdUser((int)idUser);
                dmnd.setTitre(demandeObj.get("titre").toString());
                dmnd.setDescription(demandeObj.get("description").toString());
                dmnd.setPhoto(demandeObj.get("photo").toString());
                //dmnd.setPhoto(dmnd.getPhoto());
                //System.out.println(obj.get("photo").toString());
                dmnd.setEtat(demandeObj.get("etat").toString());
               // dmnd.setIdCategorie( categorie.);
                //dmnd.setStatus(obj.get("status").toString());
                dmnd.setNbReaction((int)nbReaction);
                
                /*
                t.setId((int)id);
                t.setStatus(((int)Float.parseFloat(obj.get("status").toString())));
                t.setName(obj.get("name").toString());
                */
                //Ajouter la tâche extraite de la réponse Json à la liste
                demandes.add(dmnd);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return demandes;
    }
        
        

        
        public ArrayList<DemandeAide> getAllDemandes(){
        String url = Statics.BASE_URL+"/aide/demandeaide/all?user=" + FLogIns_gui.userCon.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                demandes = parseDemandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return demandes;
    }
        
        
        public ArrayList<DemandeAide> getMesDemandes(){
            User connectedUser = FLogIns_gui.userCon;
            int idUser = connectedUser.getId();
           // System.out.println(idUser);
           // System.out.println(idUser);
            //System.out.println(idUser);
        String url = Statics.BASE_URL+"/aide/demandeaide/findmine/"+idUser;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                demandes = parseMesDemandes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return demandes;
    }
        
        
    
}
