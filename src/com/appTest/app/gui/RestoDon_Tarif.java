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
import static com.codename1.ui.Dialog.TYPE_INFO;

/**
 *
 * @author bhk
 */
public class RestoDon_Tarif extends SideMenuNov {

    public RestoDon_Tarif(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        
        getToolbar().setUIID("RestoDon-Toolbar");
        setTitle("Modifier votre tarif");
        setLayout(BoxLayout.y());
        
        TextField tfTarif = new TextField("","Tarif");
        tfTarif.setAlignment(LEFT);
        tfTarif.setUIID("RestoDon-TextField");
        
        
        Button btnValider = new Button("Modifier");
        btnValider.setUIID("RestoDon-btn");
       
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    if ((tfTarif.getText().length()==0))
                        Dialog.show("Alert", "Veuillez remplir le champ s'il vous plait", new Command("OK"));
                    else{
                        try {
                            if(Float.parseFloat(tfTarif.getText()) <= 0){
                            Dialog.show("ERROR", "Le tarif ne peut pas être négatif", new Command("OK"));
                            }else{
                                Command  dg = Dialog.show("Vérification", "Veuillez confirmer le nouveau tarif : '" + tfTarif.getText() + " dinars'", new Command("Annuler"), new Command("Confirmer"));
                                if (dg.getCommandName().equalsIgnoreCase("Confirmer")){
                            TarifResto tarifResto = new TarifResto(FLogIns_gui.userCon.getId(), Float.parseFloat(tfTarif.getText()));
                            if( ServiceTarifResto.getInstance().newMobileTarif(tarifResto) ){
                                Dialog.show("Success","Votre tarif a été modifier avec success",new Command("D'accord"));
                                new RestoDon_HomeResto().showBack();
                                } else{
                            Dialog.show("ERREUR", "Il ya un problème de connexion", new Command("D'accord"));
                            }
                                }else{}
                            }} catch (NumberFormatException e) {
                            Dialog.show("ERREUR", "Le tarif ne peut contenir que des chiffres", new Command("D'accord"));
                        }
                        }
                    }
             
            });
        
        addAll(tfTarif,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new RestoDon_HomeResto().showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
