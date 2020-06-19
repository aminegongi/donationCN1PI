/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.Cagnotte;
import com.appTest.app.services.Cagnotte_Service;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Ahmed Zeghibi
 */
public class Cagnotte_Add extends SideMenuNov{
    Form current;
    Button btnAjouter = new Button("Ajouter");
    TextField tfTitre = new TextField();
    TextField tfMontantDmnd = new TextField();

    public Cagnotte_Add() {
        addSideMenu();
        current = this;
        current.setTitle("Ajouter Cagnotte");
        current.setLayout(BoxLayout.y());
        Toolbar tb = current.getToolbar();
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new com.codename1.ui.events.ActionListener() {
            @Override
            public void actionPerformed(com.codename1.ui.events.ActionEvent evt) {
                new Cagnotte_Home().show();
            }
        });
        
        tfTitre.setHint("Titre de la cagnotte");
        tfMontantDmnd.setHint("Montant demandé");
        current.addAll(tfTitre,tfMontantDmnd);
        current.add(btnAjouter);
        
        btnAjouter.addActionListener((evt) -> {
            System.out.println("Cagnotte ajoutée");
            
            Cagnotte c = new Cagnotte();
            c.setNom(tfTitre.getText());
            c.setMontant_demande(Float.parseFloat(tfMontantDmnd.getText()));
            
            c.setUser(FLogIns_gui.userCon.getId());

            Cagnotte_Service.getInstance().addCagnotte(c);            
            
            
            new Cagnotte_Home().show();
        });        
    }
    
}
