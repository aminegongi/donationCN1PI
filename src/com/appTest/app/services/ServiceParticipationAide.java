/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.services;

import com.appTest.app.entities.DemandeAide;
import com.appTest.app.entities.ParticipationAide;
import com.appTest.app.entities.User;
import com.appTest.app.gui.FLogIns_gui;
import com.appTest.app.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hedi
 */
public class ServiceParticipationAide {
    
    public ArrayList<ParticipationAide> participations;
    
    public ArrayList<User> participants;
    
    
    public static ServiceParticipationAide instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceParticipationAide() {
         req = new ConnectionRequest();
    }

    public static ServiceParticipationAide getInstance() {
        if (instance == null) {
            instance = new ServiceParticipationAide();
        }
        return instance;
    }
    
    
    
        public boolean addParticipationAide(ParticipationAide participationAide) {
        String url = Statics.BASE_URL + "/aide/participationaide/add?" + "user="+participationAide.getIdUser()+"&demande="+participationAide.getIdDemande(); //création de l'URL
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
        
        
        
        public boolean deleteParticipationAide(ParticipationAide participationAide) {
        String url = Statics.BASE_URL + "/aide/participationaide/delete?" + "user="+participationAide.getIdUser()+"&demande="+participationAide.getIdDemande(); //création de l'URL
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
        
        
        
                public ArrayList<User> parseParicipations(String jsonText){
        try {
            
            
         
            // a afficher
            participations =new ArrayList<>();
            participants =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> participationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            List<Map<String,Object>> list = (List<Map<String,Object>>)participationsListJson.get("participationAides");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                ParticipationAide part = new ParticipationAide();
                float id = Float.parseFloat(obj.get("id").toString());
                
                //les obj JSON tnm ta9rahom java ken fi map 
                 Map<String,Object> demandeObj = (Map<String,Object>)obj.get("idDemande");
                 float idDmnd = Float.parseFloat(demandeObj.get("id").toString());
                 Map<String,Object> userObj = (Map<String,Object>)obj.get("idUser");
                 float idUser = Float.parseFloat(userObj.get("id").toString());
                
                
                part.setId((int)id);
                part.setIdDemande((int)idDmnd);
                part.setIdUser((int)idUser);
                part.setEmailUser(userObj.get("email").toString());
                
                
                
                
                //Ajouter la tâche extraite de la réponse Json à la liste
               // participations.add(part);
                
                //Ajouter lobjet user au tableau participants
                //Collection<Object> value = userObj.values();
                
                ArrayList<User> userInList = Ges_User.getInstance().getUserbyId((int) idUser);
                User user = userInList.get(0);
                //System.out.println("user ID : "+ (int) idUser);
                //System.out.println(userInList);
                
                      
                participants.add(user);
               // System.out.println(participants);
            }
            
            
        } catch (IOException ex) {
            System.out.println("erreur");
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        //return participations;
        return participants;
    }
                
                
                
                
              //all participations for specific dmnd  
           public ArrayList<User> getAllParticipations(int id){
        String url = Statics.BASE_URL+"/aide/participationaide/find/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //participations = parseParicipations(new String(req.getResponseData()));
                participants = parseParicipations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        //return participations;
        return participants;
    }
           
        
        public boolean alreadyParticipated(int idDmnd){
            
            User ConnectedUser = FLogIns_gui.userCon;
            ArrayList<User> participatedUsers =  getAllParticipations(idDmnd);
            
            if ( participatedUsers.contains(ConnectedUser) ) return true;
                    
            else      return false;
        }
}
