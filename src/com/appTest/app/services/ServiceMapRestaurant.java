/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.services;

import com.appTest.app.entities.RestoMapCoord;
import com.appTest.app.entities.TarifResto;
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
import java.util.Map;

/**
 *
 * @author safratix
 */
public class ServiceMapRestaurant {
    
    public ArrayList<RestoMapCoord> listeRestoMapCoord;
    
    public static ServiceMapRestaurant instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private ServiceMapRestaurant(){
        req = new ConnectionRequest();
    }
    
    public static ServiceMapRestaurant getInstance() {
        if (instance == null) {
            instance = new ServiceMapRestaurant();
        }
        return instance;
    }
    
    
    
    
    
    
    
    public ArrayList<RestoMapCoord> parseTasks(String jsonText){
        try {
            listeRestoMapCoord=new ArrayList<>(); // celle que vas etre afficher
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> RestoMapCoordJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            
//            List<Map<String,Object>> list = (List<Map<String,Object>>)tarifRestoListJson.get("tarifResto");
//              System.out.println(tarifRestoListJson.size());
//            list.get(0).get("idResto");
            
            

                ArrayList<Map<String,Object>> features = (ArrayList<Map<String,Object>>) RestoMapCoordJson.get("features");
                for(Map<String,Object> feature : features){
                    Map<String,Object> properties = (Map<String,Object>)feature.get("properties");
                    RestoMapCoord resto = new RestoMapCoord();
                    String username = properties.get("username").toString();
                    System.out.println(username);
                    String fb = properties.get("fb").toString();
                    String web = properties.get("web").toString();
                    String icon = properties.get("icon").toString();
                    Map<String,Object> geometry = (Map<String,Object>)feature.get("geometry");
//                    String longitude = geometry.get("longitude").toString();
//                    String latitude = geometry.get("latitude").toString();
                    
                    try{resto.setUsername(username);}catch(Exception ex){resto.setUsername("username non définie");}
                    try{resto.setFb(fb);}catch(Exception ex){resto.setUsername("fb non définie");}
                    try{resto.setWeb(web);}catch(Exception ex){resto.setUsername("web non définie");}
                    try{resto.setIcon(icon);}catch(Exception ex){resto.setUsername("icon non définie");}
                    try{resto.setLongitude(Float.parseFloat(geometry.get("longitude").toString())); 
                    resto.setActiveLongitude(true);}catch(Exception ex){resto.setActiveLongitude(false);}
                    try{resto.setLatitude(Float.parseFloat(geometry.get("latitude").toString())); 
                    resto.setActiveLatitude(true);}catch(Exception ex){resto.setActiveLatitude(false);}
                    
                    
                    listeRestoMapCoord.add(resto);
                    
                }
                
            //Parcourir la liste des tâches Json
//            for(Map<String,Object> obj : list){
//                //Création des tâches et récupération de leurs données
//                TarifResto tarif_resto = new TarifResto();
//                System.out.println(obj.get("erreur").toString());
//                Map<String,Object> Resto = (Map<String,Object>) obj.get("idResto");
//                int idResto = Integer.parseInt(Resto.get("id").toString());
//                float tarif = Float.parseFloat(obj.get("tarif").toString());
//                tarif_resto.setIdResto(idResto);
//                tarif_resto.setTarif((Float.parseFloat(obj.get("tarif").toString())));
//                tarif_resto.setPortefeuilleVirtuel(Float.parseFloat(obj.get("portefeuilleVirtue").toString()));
//                //Ajouter la tâche extraite de la réponse Json à la liste
//                tarifResto.add(tarif_resto);
//            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return listeRestoMapCoord;
    }
    
    public ArrayList<RestoMapCoord> getRestoMap(){
        
        String url = Statics.BASE_URL+"/RestoDon/ListMapRestoMobile";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            listeRestoMapCoord = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
        return listeRestoMapCoord;
        
    }
}
