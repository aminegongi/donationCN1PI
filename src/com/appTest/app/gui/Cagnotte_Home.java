/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.Cagnotte;
import com.appTest.app.services.Cagnotte_Service;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author Ahmed Zeghibi
 */
public class Cagnotte_Home extends SideMenuNov{
    Form current;

    public Cagnotte_Home() {
        addSideMenu();
        current = this;
        current.setTitle("Plateforme Cagnotte");
        current.setLayout(BoxLayout.y());
        Toolbar tb = current.getToolbar();
        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, new com.codename1.ui.events.ActionListener() {
            @Override
            public void actionPerformed(com.codename1.ui.events.ActionEvent evt) {
                new Cagnotte_Add().show();
            }
        });
    
        ArrayList<Cagnotte> cagnottes = Cagnotte_Service.getInstance().getAllCagnottes();
        for (Cagnotte cagnotte : cagnottes) {   
            SpanLabel sp = new SpanLabel();
            sp.setText(cagnotte.toString());

            Container cagnotteBox = new Container();
            Container description = new Container();
            Container btnBox = new Container();

            cagnotteBox.setLayout(BoxLayout.y());
            description.setLayout(BoxLayout.y());
            TextArea Titre = new TextArea(cagnotte.getNom());
            Titre.setEditable(false);
            Titre.setFocusable(false);
            
            Label montant_demande = new Label("Montant Demandé: " + Float.toString(cagnotte.getMontant_demande()) + " DT");
            Label montant_actuel = new Label("Montant Actuel: " + Float.toString(cagnotte.getMontant_actuel()) + " DT");
            
            Button detailsBtn = new Button("Plus de details");
            detailsBtn.addActionListener((evt) -> {
                new Cagnotte_Show(cagnotte.getId()).show();
            });

            Label separator = new Label("", "WhiteSeparator");
            separator.getUnselectedStyle().setBgColor(0xeeeeee);
            separator.getUnselectedStyle().setBgTransparency(255);
            separator.setShowEvenIfBlank(true);

            description.add(montant_demande);
            description.add(montant_actuel);
            btnBox.add(detailsBtn);
            cagnotteBox.addAll(Titre);
            cagnotteBox.add(description);
            cagnotteBox.add(btnBox);
            cagnotteBox.add(separator);
            current.add(cagnotteBox);                
        }
    }  
}
