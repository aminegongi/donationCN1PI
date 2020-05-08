/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.DonRestaurant;
import com.appTest.app.services.ServiceDonRestaurant;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author safratix
 */
public class RestoDon_DonUserSimple extends SideMenuNov {
    public RestoDon_DonUserSimple(){
        getToolbar().setUIID("RestoDon-Toolbar");
        setTitle("Nouvelle donation");
        setLayout(BoxLayout.y());
        
        TextField tfMontant= new TextField("", "Montant");
        tfMontant.setAlignment(LEFT);
        tfMontant.setUIID("RestoDon-TextField");
        
        
        Button btnValider = new Button("Donate");
        btnValider.setUIID("RestoDon-btn");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfMontant.getText().length()==0))
                    Dialog.show("Alert", "Veuillez remplir tous les champs s'il vous plait", new Command("OK"));
                else{
                  Command  dg = Dialog.show("Vérification", "Veuillez confirmer le montant '" + tfMontant.getText() + " dinars'", new Command("Annuler"), new Command("Confirmer"));
                    if (dg.getCommandName().equalsIgnoreCase("Confirmer")){
                    try {
                        
                       new RestoDon_QRGenerate(tfMontant.getText()).show();
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERREUR", "Le montant doit être numérique", new Command("OK"));
                    }
                    }else{}
                }   
            }});
        

        addAll(tfMontant,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new RestoDon_MapResto().showBack()); // Revenir vers l'interface précédente
                
    }
    
}
