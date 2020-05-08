/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.RepasServi;
import com.appTest.app.entities.TarifResto;
import com.appTest.app.services.ServiceRepasServi;
import com.appTest.app.services.ServiceTarifResto;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bhk
 */
public class RestoDon_HomeResto extends SideMenuNov {

    Form current;
    ArrayList<String> list;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public RestoDon_HomeResto() {
        addSideMenu();
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_DASHBOARD, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //el traitement t3awed ta3mel user + getUser FLoginC
                new RestoDon_Dashboard().show();
            }
        });
        getToolbar().setUIID("RestoDon-Toolbar");
        ArrayList<TarifResto> tarifResto;
        
//        MultiButton mbTarif = new MultiButton();
//        mbTarif.setTextLine1("Votre tarif est :");
//        try{
//        Image imgTarif = Image.createImage("/src/com/appTest/app/gui/firstplot.png");
//        mbTarif.setIcon(imgTarif);
//        }catch(IOException ex){
//            System.out.println(ex.getMessage());
//        }
        
        
        
        Label labelTarif = new Label("Tarif :");
        labelTarif.setUIID("RestoDon-Label");
        TextField tarif = new TextField();
        tarif.setUIID("RestoDon-TextField");
        tarif.setActAsLabel(true);
        tarif.setEditable(false);
        tarif.setFocusable(false);
        tarif.setAlignment(LEFT);
        tarif.setWidth(70);
        Container cntTarif = new Container(BoxLayout.x());
        cntTarif.addAll(labelTarif, tarif);
         
        
        Label labelPorteF = new Label("Portefeuille virtuel :");
        labelPorteF.setUIID("RestoDon-Label");
        TextField porteFeuille = new TextField();
        porteFeuille.setUIID("RestoDon-TextField");
        porteFeuille.setActAsLabel(true);
        porteFeuille.setEditable(false);
        porteFeuille.setFocusable(false);
        porteFeuille.setAlignment(LEFT);
        porteFeuille.setWidth(70);
        Container cntPorteF = new Container(BoxLayout.x());
        cntPorteF.addAll(labelPorteF, porteFeuille);
        
        TarifResto t = new TarifResto(FLogIns_gui.userCon.getId());
        tarifResto= ServiceTarifResto.getInstance().getTarifResto(t);//cette ligne s'enleve
          
//        float value_tarif = tarifResto.getTarif();
//        float value_portefeuille = tarifResto.getPortefeuilleVirtuel();

        float value_tarif = tarifResto.get(0).getTarif();
        float value_portefeuille = tarifResto.get(0).getPortefeuilleVirtuel();

//        float value_tarif = tarifInit;
//        float value_portefeuille = PortefeuilleInit;
        tarif.setText(Float.toString(value_tarif));
//        mbTarif.setTextLine2(Float.toString(value_tarif));
        porteFeuille.setText(Float.toString(value_portefeuille));
        
//        Button btnGenerate = new Button("generate");
//        btnGenerate.addActionListener(e->{
//        new RestoDon_QRGenerate().show();
//        });
//        
        
        current = this; //Récupération de l'interface(Form) en cours
        
        setTitle("RestoDon");
        setLayout(BoxLayout.y());
        
        Container cnt = new Container(BoxLayout.y());
        cnt.addAll(cntTarif, cntPorteF);
        
        add(cnt);
        
        
//        Dialog repasDialog = new Dialog();
//        repasDialog.setTitle("Repas Servi");
//        repasDialog.add("Un repas a été servis avec succés!");
//        repasDialog.setBackCommand(Command.createMaterial("bravo!", FontImage.MATERIAL_APPROVAL, (evt) -> {
//           new RestoDon_HomeResto();
//        }));
//        

        FloatingActionButton restoDonFAB = new FloatingActionButton(FontImage.MATERIAL_KITCHEN, "hello", "RestoDon-FAB", 5) {
       
        };
        
        
        
        FloatingActionButton restoDon_RepasFAB = restoDonFAB.createSubFAB(FontImage.MATERIAL_EMOJI_FOOD_BEVERAGE, "Servir repas");
        restoDon_RepasFAB.setUIID("RestoDon-FAB");
        restoDon_RepasFAB.setPreferredSize(new Dimension(200,200));
        restoDon_RepasFAB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                       Command  dg = Dialog.show("Vérification", "Veuillez confirmer pour servir un repas", new Command("Annuler"), new Command("Confirmer"));
                    if (dg.getCommandName().equalsIgnoreCase("Confirmer")){
                        RepasServi repasServi = new RepasServi(FLogIns_gui.userCon.getId());
                        list = ServiceRepasServi.getInstance().newMobileRepas(FLogIns_gui.userCon.getId());
                        if( list.get(0).contains("true") ){
                            TarifResto t = new TarifResto(FLogIns_gui.userCon.getId());
//                            TarifResto tarif_resto = (TarifResto) ServiceTarifResto.getInstance().getTarifResto(t).get("tarifResto");
//                            String newPorteF = Float.toString(tarif_resto.getPortefeuilleVirtuel());
                            String newPorteF = Float.toString(ServiceTarifResto.getInstance().getTarifResto(t).get(0).getPortefeuilleVirtuel());
                            porteFeuille.setText(newPorteF);
                             if(Float.parseFloat(porteFeuille.getText()) > Float.parseFloat(tarif.getText())){
                                int nbrPlat = (int) (Float.parseFloat(porteFeuille.getText()) / Float.parseFloat(tarif.getText()));
                                    restoDon_RepasFAB.setBadgeText(Integer.toString(nbrPlat));
                            }else{
                                restoDon_RepasFAB.setBadgeText("");
                            }
                             if (list.get(1).contains("null")){Dialog.show("Bravo !","Vous avez servis un repas",new Command("D'accord"));}
                             if(list.get(1).contains("solde")){Dialog.show("Malheureusement","Votre solde est insuffisant",new Command("D'accord"));}
                        }else
                            Dialog.show("ERROR", "problème de connexion", new Command("D'accord"));
                    }
               
             
            }});
        
        FloatingActionButton restoDon_TarifFAB = restoDonFAB.createSubFAB(FontImage.MATERIAL_CREDIT_CARD, "Modifier tarif");
        restoDon_TarifFAB.setUIID("RestoDon-FAB");
        restoDon_TarifFAB.setPreferredSize(new Dimension(200,200));
        restoDon_TarifFAB.addActionListener(e -> new RestoDon_Tarif(current).show());
        
        FloatingActionButton restoDon_DonationFAB = restoDonFAB.createSubFAB(FontImage.MATERIAL_MONEY, "Donation");
        restoDon_DonationFAB.setUIID("RestoDon-FAB");
        restoDon_DonationFAB.setPreferredSize(new Dimension(200,200));
        restoDon_DonationFAB.addActionListener(e -> new RestoDon_Don(current).show());
        
        
        if(Float.parseFloat(porteFeuille.getText()) > Float.parseFloat(tarif.getText())){
            int nbrPlat = (int) (Float.parseFloat(porteFeuille.getText()) / Float.parseFloat(tarif.getText()));
        restoDon_RepasFAB.setBadgeText(Integer.toString(nbrPlat));
        }else{
            restoDon_RepasFAB.setBadgeText("");
        }
//        restoDonFAB.createSubFAB(FontImage.MATERIAL_MONEY, "Donation");
        restoDonFAB.bindFabToContainer(getContentPane());
        
        
        
        
//        Button btnAddTask = new Button("Add Task");
//        Button btnListTasks = new Button("List Tasks");
//        btnAddTask.setUIID("RestoDon-btn");
//        btnListTasks.setUIID("RestoDon-btn");
//        
//        btnAddTask.addActionListener(e -> new RestoDon_Don(current).show());
//        btnListTasks.addActionListener(e -> new ListRestoDon(current).show());
//        addAll(btnAddTask, btnListTasks);
       

    }

}
