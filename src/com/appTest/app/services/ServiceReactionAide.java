/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.services;

import com.appTest.app.entities.ParticipationAide;
import com.appTest.app.entities.ReactionAide;
import com.appTest.app.entities.User;
import com.appTest.app.utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Hedi
 */
public class ServiceReactionAide {
    
    
    public ArrayList<ReactionAide> reactions;
    
    public ArrayList<User> reacteurs;
    
    
    public static ServiceReactionAide instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    
    private ServiceReactionAide() {
         req = new ConnectionRequest();
    }

    public static ServiceReactionAide getInstance() {
        if (instance == null) {
            instance = new ServiceReactionAide();
        }
        return instance;
    }

    
        public boolean addReactionAide(ReactionAide reactionAide) {
        String url = Statics.BASE_URL + "/aide/reactionaide/add?" + "user="+reactionAide.getIdUser()+"&demande="+reactionAide.getIdDemande(); //création de l'URL
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
        
        

        
        public boolean deleteReactionAide(ReactionAide reactionAide) {
        String url = Statics.BASE_URL + "/aide/reactionaide/delete?" + "user="+reactionAide.getIdUser()+"&demande="+reactionAide.getIdDemande(); //création de l'URL
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
        
        
        
    

}
