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
import com.appTest.app.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author safratix
 */
public class ServiceDonRestaurant {
    
    public ArrayList<DonRestaurant> DonRestaurant;
    
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
    
    public boolean newMobileDon(DonRestaurant don) {
        String url = Statics.BASE_URL + "/RestoDon/newDonMobile?resto=" + don.getIdResto() + "&username=" + don.getDonator() + "&montant=" + don.getMontant(); //création de l'URL
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.setPost(true);
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
