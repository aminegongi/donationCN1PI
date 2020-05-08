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
import com.appTest.app.gui.FLogIns_gui;
import com.appTest.app.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.DateFormatPatterns;
import com.codename1.l10n.DateFormatSymbols;
import com.codename1.l10n.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author safratix
 */
public class ServiceDonRestaurant {
    
    public ArrayList<DonRestaurant> DonRestaurant;
    
    ArrayList<Map<String,Object>> listResultat;
    
    
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
    
    public ArrayList<Map<String,Object>> parseListDon(String jsonText){
        ArrayList<Map<String,Object>> listDonation = new ArrayList<Map<String,Object>>();
         
        try {
             // celle que vas etre afficher
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String,Object> reponseServeur = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
            

//                String error = reponseServeur.get("erreur").toString();
//                System.out.println(error);
//                Map<String,Object> erreurMap = new HashMap<String,Object>();
//                erreurMap.put("erreur", error);
//                System.out.println(erreurMap);
//                listDonation.add(erreurMap);
                
                ArrayList<Map<String,Object>> listDon = (ArrayList<Map<String,Object>>) reponseServeur.get("listDon");
                
                for( Map<String,Object> don : listDon){
//                    System.out.println(don.toString());
                    Map<String,Object> donObj = new HashMap<String,Object>();
                     try{
                        Map<String,Object> donator = (Map<String,Object>) don.get("idUser");
                        String username = donator.get("username").toString();
//                        System.out.println(username);
                        donObj.put("username", username);
                    }catch(Exception ex){
                        String username = "anonymous";
//                        System.out.println(username);
                        donObj.put("username", username);
                    }
                   float montant = Float.parseFloat(don.get("montant").toString());
                   Map<String,Object> dateObj = (Map<String,Object>) don.get("date");
                   long timestamp = Double.valueOf(dateObj.get("timestamp").toString()).longValue();
//                   System.out.println(timestamp);
                   Date date = new Date(timestamp*1000L); 
//String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (timestamp*1000));
                   SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   jdf.setDateFormatSymbols(new DateFormatSymbols());
                   String finalDate = jdf.format(date);
//                    System.out.println(finalDate);
                   donObj.put("montant", montant);
                   donObj.put("date", finalDate);
                   listDonation.add(donObj);
                }
           
                 

            
            
        } catch (IOException ex) {
            
        }
       
        return listDonation;
    }
    
    public ArrayList<Map<String,Object>> getListDon(){
        
        String url = Statics.BASE_URL+"/RestoDon/listDonMobile?resto="+ FLogIns_gui.userCon.getId();
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listResultat = parseListDon(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listResultat;
    }
}
