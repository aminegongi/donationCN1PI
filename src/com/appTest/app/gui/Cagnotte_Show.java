/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.Cagnotte;
import com.appTest.app.services.Cagnotte_Service;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Ahmed Zeghibi
 */
public class Cagnotte_Show extends SideMenuNov{
    Form current;

    public Cagnotte_Show(int id) {
        addSideMenu();
        current = this;
        current.setTitle("Affichage Cagnotte");
        current.setLayout(BoxLayout.y());
        Toolbar tb = current.getToolbar();
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new com.codename1.ui.events.ActionListener() {
            @Override
            public void actionPerformed(com.codename1.ui.events.ActionEvent evt) {
                new Cagnotte_Home().show();
            }
        });
        System.out.println("ID: " + id);
        Cagnotte cagnotte = Cagnotte_Service.getInstance().getCagnotte(id);
        
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
        
        Button detailsBtn = new Button();
        if (cagnotte.getEtat() == 1){
            detailsBtn.setText("Je me sens généreux");
            detailsBtn.addActionListener((evt) -> {
                new Cagnotte_Donate(cagnotte.getId()).show();
            });
        }
        else if (cagnotte.getEtat() == 2){
            detailsBtn.setText("La cagnotte a atteint le montant demandé.");
            detailsBtn.setEnabled(false);
            detailsBtn.setFocusable(false);
        }
        else if (cagnotte.getEtat() == 0){
            if (FLogIns_gui.userCon.getRoles().contains("ROLE_ORG")){
                detailsBtn.setText("Prendre en charge");
                detailsBtn.addActionListener((evt) -> {
                    System.out.println("Prendre en charge");
                    Cagnotte_Service.getInstance().prendreEnCharge(cagnotte.getId());
                    new Cagnotte_Show(cagnotte.getId()).show();
                });
            }
        }

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
