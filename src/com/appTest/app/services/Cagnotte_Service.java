/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.services;

import com.appTest.app.entities.Cagnotte;
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
import java.util.List;
import java.util.Map;
import com.codename1.payment.Purchase;
import com.codename1.payment.PurchaseCallback;

/**
 *
 * @author Ahmed Zeghibi
 */
public class Cagnotte_Service implements PurchaseCallback {
    public ArrayList<Cagnotte> cagnottes;
    public Cagnotte cgnte = null;
    public static Cagnotte_Service instance = null;
    public boolean resultOK;
    private final ConnectionRequest req;

    private Cagnotte_Service() {
        req = new ConnectionRequest();
    }
    
    public static Cagnotte_Service getInstance() {
        if (instance == null) {
            instance = new Cagnotte_Service();
        }
        return instance;
    }
    //Cagnotte/newCagnotteMobile?user=x&nom=x&montant_demande=x&id_categorie=x
    public boolean addCagnotte(Cagnotte cagnotte) {
        String url = Statics.BASE_URL + "/Cagnotte/newCagnotteMobile?" + "user="+cagnotte.getUser()+"&nom="+cagnotte.getNom()+"&montant_demande="+cagnotte.getMontant_demande()+"&id_categorie="+cagnotte.getCategorie(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            req.removeResponseListener(this); //Supprimer cet actionListener   
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    } 
    
    public ArrayList<Cagnotte> parseCagnottes(String jsonText){
        try {
            cagnottes =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> cagnottesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)cagnottesListJson.get("root");
            for (Map<String,Object> obj : list){
                Cagnotte cagnotte = new Cagnotte();                
                float id = Float.parseFloat(obj.get("id").toString());
                float idUser = Float.parseFloat(obj.get("idProprietaire").toString());
                String Nom = obj.get("nom").toString();
                float montant_demande = Float.parseFloat(obj.get("montantDemande").toString());
                float montant_actuel = Float.parseFloat(obj.get("montantActuel").toString());
                float etat = Float.parseFloat(obj.get("etat").toString());
                        
                cagnotte.setId((int)id);
                cagnotte.setUser((int) idUser);
                cagnotte.setNom(Nom);
                cagnotte.setMontant_demande(montant_demande);
                cagnotte.setMontant_actuel(montant_actuel);
                cagnotte.setEtat((int) etat);
                System.out.println("ROLE: " + FLogIns_gui.userCon.getRoles());
                System.out.println(FLogIns_gui.userCon.getRoles().contains("ROLE_US"));
                if (FLogIns_gui.userCon.getRoles().contains("ROLE_US")){
                    if (FLogIns_gui.userCon.getRoles().contains("ROLE_ORG")){
                        cagnottes.add(cagnotte);
                    }
                    else if ((etat == 1) || (etat == 2)){
                        cagnottes.add(cagnotte);
                    }
                }
                else {
                    cagnottes.add(cagnotte);
                }
            }  
        } catch (IOException ex) {   
        }
        return cagnottes;
    }
    //Cagnotte/getCagnottesMobile?user=x
    public ArrayList<Cagnotte> getAllCagnottes(){
        String url = Statics.BASE_URL+"/Cagnotte/getCagnottesMobile?user=" + FLogIns_gui.userCon.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cagnottes = parseCagnottes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cagnottes;
    }
    
    public Cagnotte parseCagnotte(String jsonText){
        Cagnotte cagnotte = new Cagnotte();
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> cagnotteJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            //List<Map<String,Object>> list = (List<Map<String,Object>>)cagnottesListJson.get("root");
            //for (Map<String,Object> obj : cagnottesListJson){              
                float id = Float.parseFloat(cagnotteJson.get("id").toString());
                float idUser = Float.parseFloat(cagnotteJson.get("idProprietaire").toString());
                String Nom = cagnotteJson.get("nom").toString();
                float montant_demande = Float.parseFloat(cagnotteJson.get("montantDemande").toString());
                float montant_actuel = Float.parseFloat(cagnotteJson.get("montantActuel").toString());
                float etat = Float.parseFloat(cagnotteJson.get("etat").toString());
                
                cagnotte.setId((int)id);
                cagnotte.setUser((int) idUser);
                cagnotte.setNom(Nom);
                cagnotte.setMontant_demande(montant_demande);
                cagnotte.setMontant_actuel(montant_actuel);
                cagnotte.setEtat((int) etat);
            //}  
        } catch (IOException ex) {   
        }
        return cagnotte;
    }
    //Cagnotte/showCagnotteMobile?user=x&id=x
    public Cagnotte getCagnotte(int id){
        
        String url = Statics.BASE_URL+"/Cagnotte/showCagnotteMobile?user=" + FLogIns_gui.userCon.getId() + "&id=" + id;
        System.out.println("URL: " + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cgnte = parseCagnotte(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cgnte;
    }
    //Cagnotte/prendreCagnotteMobile?user=x&id=x
    public boolean prendreEnCharge(int id) {
        String url = Statics.BASE_URL + "/Cagnotte/prendreCagnotteMobile?" + "user=" + FLogIns_gui.userCon.getId() + "&id=" + id;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            req.removeResponseListener(this); //Supprimer cet actionListener    
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    } 
    
    public boolean sendViaJson(int id, float montant){
        String url = Statics.BASE_URL + "/Cagnotte/donateCagnotteMobile?" + "user=" + FLogIns_gui.userCon.getId() + "&id=" + id + "&montant=" + montant;
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            req.removeResponseListener(this); //Supprimer cet actionListener   
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public void donate(int id, float montant){
        Purchase.getInAppPurchase().pay(montant, "EUR");
        sendViaJson(id, montant);
    }

    @Override
    public void itemPurchased(String sku) {
        System.out.println("Not supported yet");
    }

    @Override
    public void itemPurchaseError(String sku, String errorMessage) {
        System.out.println("Not supported yet");
    }

    @Override
    public void itemRefunded(String sku) {
        System.out.println("Not supported yet");
    }

    @Override
    public void subscriptionStarted(String sku) {
        System.out.println("Not supported yet");
    }

    @Override
    public void subscriptionCanceled(String sku) {
        System.out.println("Not supported yet");
    }

    @Override
    public void paymentFailed(String paymentCode, String failureReason) {
        System.out.println("Not supported yet");
    }

    @Override
    public void paymentSucceeded(String paymentCode, double amount, String currency) {
        System.out.println("Not supported yet");
    }
}
