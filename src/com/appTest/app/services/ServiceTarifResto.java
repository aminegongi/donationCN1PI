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
import com.appTest.app.entities.TarifResto;
import com.appTest.app.gui.FLogIns_gui;
import com.appTest.app.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author safratix
 */
public class ServiceTarifResto {
    public ArrayList<TarifResto> tarifResto;
    
    public static ServiceTarifResto instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private ServiceTarifResto(){
        req = new ConnectionRequest();
    }
    
    public static ServiceTarifResto getInstance() {
        if (instance == null) {
            instance = new ServiceTarifResto();
        }
        return instance;
    }
    
    public boolean newMobileTarif(TarifResto tarifResto) {
        String url = Statics.BASE_URL + "/RestoDon/newTarifMobile?resto=" + tarifResto.getIdResto() + "&tarif=" + tarifResto.getTarif(); //création de l'URL
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
    
    public ArrayList<TarifResto> parseTasks(String jsonText){
        try {
            tarifResto=new ArrayList<>(); // celle que vas etre afficher
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
            Map<String,Object> tarifRestoListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
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
            
            

                String error = (String) tarifRestoListJson.get("erreur");
                Map<String,Object> tarifRestoObj = (Map<String,Object>)tarifRestoListJson.get("tarifResto");
                Map<String,Object> RestoObj = (Map<String,Object>)tarifRestoObj.get("idResto");
                TarifResto tarif_resto = new TarifResto();
                System.out.println(error);
//                Map<String,Object> Resto = (Map<String,Object>) obj.get("idResto");
                float idResto = Float.parseFloat(RestoObj.get("id").toString());
                float tarif = Float.parseFloat(tarifRestoObj.get("tarif").toString());
                tarif_resto.setIdResto((int)idResto);
                tarif_resto.setTarif((Float.parseFloat(tarifRestoObj.get("tarif").toString())));
                tarif_resto.setPortefeuilleVirtuel(Float.parseFloat(tarifRestoObj.get("portefeuilleVirtuel").toString()));
                //Ajouter la tâche extraite de la réponse Json à la liste
                tarifResto.add(tarif_resto);


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
        return tarifResto;
    }
    
    public ArrayList<TarifResto> getTarifResto(TarifResto tarif_resto){
        String url = Statics.BASE_URL+"/RestoDon/getTarifMobile?resto="+ FLogIns_gui.userCon.getId();
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tarifResto = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tarifResto;
    }
}
