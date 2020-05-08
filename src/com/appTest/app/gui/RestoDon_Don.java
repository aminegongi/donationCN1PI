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
import com.codename1.ext.codescan.CodeScanner;
import com.codename1.ext.codescan.ScanResult;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author bhk
 */
public class RestoDon_Don extends SideMenuNov{

    EncodedImage enc;
    ImageViewer imgv;
    Image img;
    ArrayList<String> list;
    String[] scannedQR = new String[2];
    
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
                  Command  dg = Dialog.show("Vérification", "Veuillez confirmer le pseudoname '" + tfDonator.getText() + "' et le montant '" + tfMontant.getText() + " dinars'", new Command("Annuler"), new Command("Confirmer"));
                    if (dg.getCommandName().equalsIgnoreCase("Confirmer")){
                    try {
                        DonRestaurant don = new DonRestaurant(FLogIns_gui.userCon.getId(), tfDonator.getText(), Float.parseFloat(tfMontant.getText()));
                        list = ServiceDonRestaurant.getInstance().newMobileDon(don);
                        if( list.get(0).contains("true") ){
                            if (list.get(1).contains("null")){
                                Dialog.show("Bravo !","Un nouveau don de "+ tfMontant.getText() + " dinars par "+ tfDonator.getText() +" a été enregistrer avec success",new Command("D'accord"));
                            }else{
                             if(list.get(1).contains("user")){Dialog.show("Malheureusement","Le pseudoname " + tfDonator.getText() +" n'existe pas !",new Command("D'accord"));}
                            }
                            tfDonator.setText("");
                            tfMontant.setText("");
                        }else
                            Dialog.show("ERREUR", "Problème de connexion", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERREUR", "Le montant doit être numérique", new Command("OK"));
                    }
                    }else{}
                }   
            }});
        
        /*Button btnTest = new Button("test");
        btnTest.addActionListener(e->{
        String content = "simple-2.400";
                String delimiter = "-";
                StringTokenizer contentTokenizer = new StringTokenizer(content, delimiter);
                int i = 0;
                while (contentTokenizer.hasMoreTokens()) {
                String element = contentTokenizer.nextToken();
                scannedQR[i]= element; 
                i++;
                }
                tfDonator.setText(scannedQR[0]);
                tfMontant.setText(scannedQR[1]);
        });*/
        

        addAll(tfDonator,tfMontant,btnValider);
        //add(btnTest);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new RestoDon_HomeResto().showBack()); // Revenir vers l'interface précédente
        
         getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_CAMERA, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                CodeScanner.getInstance().scanQRCode(new ScanResult() {

        public void scanCompleted(String contents, String formatName, byte[] rawBytes) {
            String content = contents;
                String delimiter = "-";
                StringTokenizer contentTokenizer = new StringTokenizer(content, delimiter);
                int i = 0;
                while (contentTokenizer.hasMoreTokens()) {
                String element = contentTokenizer.nextToken();
                scannedQR[i]= element; 
                i++;
                }
                tfDonator.setText(scannedQR[0]);
                tfMontant.setText(scannedQR[1]);
            
            
        }

        public void scanCanceled() {
            System.out.println("cancelled");
        }

        public void scanError(int errorCode, String message) {
            System.out.println("err " + message);
        }
    });
                
               
                
            }
        });
                
    }
    
    
}
