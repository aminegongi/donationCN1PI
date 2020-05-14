/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.services;

import com.appTest.app.entities.CategorieAide;
import com.appTest.app.entities.DemandeAide;
import com.appTest.app.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hedi
 */
public class ServiceCategorieAide {
    
    public ArrayList<CategorieAide> categories;
    
    public static ServiceCategorieAide instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceCategorieAide() {
         req = new ConnectionRequest();
    }

    public static ServiceCategorieAide getInstance() {
        if (instance == null) {
            instance = new ServiceCategorieAide();
        }
        return instance;
    }
    
    
        public ArrayList<CategorieAide> parseCategories(String jsonText){
        try {
            
            
         
            // a afficher
            categories =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            
            Map<String,Object> categoriesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              
            List<Map<String,Object>> list = (List<Map<String,Object>>)categoriesListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                CategorieAide cat = new CategorieAide();
                float id = Float.parseFloat(obj.get("id").toString());
                
                
                
                
                cat.setId((int)id);
                cat.setNom(obj.get("nom").toString());
                cat.setIcone(obj.get("icone").toString());
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                categories.add(cat);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return categories;
    }
        
        
    
        
    public ArrayList<CategorieAide> getAllCategories(){
        String url = Statics.BASE_URL+"/aide/categorieaide/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                categories = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return categories;
    }
    
    public String[] getAllCatNames(){
        List<String> list = new ArrayList<>();
        String[] res;
    
        ArrayList<CategorieAide> categories = this.getAllCategories();
              for (CategorieAide cat : categories) { 		      
           		list.add(cat.getNom());
      }
               res = list.toArray(new String[0]);
               return res;
    }
    
    public Integer[] getAllCatIds(){
        List<Integer> list = new ArrayList<>();
        Integer[] res;
    
        ArrayList<CategorieAide> categories = this.getAllCategories();
              for (CategorieAide cat : categories) { 		      
           		list.add(cat.getId());
      }
               res = list.toArray(new Integer[0]);
               return res;
    }
    
}
