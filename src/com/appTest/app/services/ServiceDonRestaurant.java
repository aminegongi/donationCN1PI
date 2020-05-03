/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.services;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.appTest.app.entities.DonRestaurant;
import com.appTest.app.entities.RepasServi;
import com.appTest.app.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author safratix
 */
public class ServiceDonRestaurant {
    
    public ArrayList<DonRestaurant> DonRestaurant;
    
    
    String erreur;
    
    public static ServiceDonRestaurant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private ServiceDonRestaurant(){
        req = new ConnectionRequest();
    }
    
    public static ServiceDonRestaurant getInstance() {
        if (instance == null) {
            instance = new ServiceDonRestaurant();
        }
        return instance;
    }
    
    public ArrayList<String> newMobileDon(DonRestaurant don) {
        ArrayList<String> erreurList = new ArrayList<String>();
        String url = Statics.BASE_URL + "/RestoDon/newDonMobile?resto=" + don.getIdResto() + "&username=" + don.getDonator() + "&montant=" + don.getMontant(); //création de l'URL
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                erreur = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        if ( resultOK == true ){
            erreurList.add("true");
        }else{
            erreurList.add("false");
        }
                erreurList.add(erreur);
        return erreurList;
    }
    
    public String parseTasks(String jsonText){
         
        try {
             // celle que vas etre afficher
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> reponseServeur = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            

                erreur = reponseServeur.get("erreur").toString();
                

            
            
        } catch (IOException ex) {
            
        }
        
        return erreur;
    }
}
