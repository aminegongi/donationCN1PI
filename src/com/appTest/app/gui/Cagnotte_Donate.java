/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

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
public class Cagnotte_Donate extends SideMenuNov{
    Form current;
    public Cagnotte_Donate(int id) {
        addSideMenu();
        current = this;
        current.setTitle("Donate Cagnotte");
        current.setLayout(BoxLayout.y());
        Toolbar tb = current.getToolbar();
        tb.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new com.codename1.ui.events.ActionListener() {
            @Override
            public void actionPerformed(com.codename1.ui.events.ActionEvent evt) {
                new Cagnotte_Home().show();
            }
        });
        
        TextField tfMontant = new TextField();
        tfMontant.setHint("Montant à donner");
        Button btnDonate = new Button("Valider");
        btnDonate.addActionListener((evt) -> {
            Cagnotte_Service.getInstance().donate(id, Float.parseFloat(tfMontant.getText().toString()));
            new Cagnotte_Home().show();
        });
        
        current.add(tfMontant);
        current.add(btnDonate);
    }
}
