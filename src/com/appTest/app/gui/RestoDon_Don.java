/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.appTest.app.entities.DonRestaurant;
import com.appTest.app.entities.RepasServi;
import com.appTest.app.entities.TarifResto;
import com.appTest.app.services.ServiceDonRestaurant;
import com.appTest.app.services.ServiceRepasServi;
import com.appTest.app.services.ServiceTarifResto;
import com.appTest.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import java.io.IOException;

/**
 *
 * @author bhk
 */
public class RestoDon_Don extends SideMenuNov{

    EncodedImage enc;
    ImageViewer imgv;
    Image img;
    public RestoDon_Don(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        getToolbar().setUIID("RestoDon-Toolbar");
        setTitle("Nouvelle donation");
        setLayout(BoxLayout.y());
        
        
        
//        try {
//            enc = EncodedImage.create("/giphy.gif");
//        } catch (IOException ex) {
//            System.out.println("enc ERR");
//        }
//        String urlImg = "http://localhost/donationWebPIDEV/web/front/images/giftsBg.png" ;
//        img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);
//        
//        Image imgV;
//        imgV = img.scaled(200, 200);
//        imgv = new ImageViewer(imgV);
//        imgv.setWidth(200);
//        imgv.setHeight(200);
//        add(imgv);
        
        TextField tfDonator = new TextField("","Username");
        tfDonator.setAlignment(LEFT);
        tfDonator.setUIID("RestoDon-TextField");
        
        
        TextField tfMontant= new TextField("", "Montant");
        tfMontant.setAlignment(LEFT);
        tfMontant.setUIID("RestoDon-TextField");
        
        
        Button btnValider = new Button("Donate");
        btnValider.setUIID("RestoDon-btn");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDonator.getText().length()==0)||(tfMontant.getText().length()==0))
                    Dialog.show("Alert", "Veuillez remplir tous les champs s'il vous plait", new Command("OK"));
                else{
                    try {
                        DonRestaurant don = new DonRestaurant(FLogIns_gui.userCon.getId(), tfDonator.getText(), Float.parseFloat(tfMontant.getText()));
                        if( ServiceDonRestaurant.getInstance().newMobileDon(don) ){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                            tfDonator.setText("");
                            tfMontant.setText("");
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
               
                }   
            }});
        

        addAll(tfDonator,tfMontant,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new RestoDon_HomeResto().showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
