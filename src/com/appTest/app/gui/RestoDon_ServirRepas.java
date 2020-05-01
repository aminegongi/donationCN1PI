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

/**
 *
 * @author bhk
 */
public class RestoDon_ServirRepas extends SideMenuNov{

    public RestoDon_ServirRepas(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new task");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","TaskName");
        TextField tfStatus= new TextField("", "Status: 0 - 1");
        Button btnValider = new Button("Add task");
        
      
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                    
                        RepasServi repasServi = new RepasServi(FLogIns_gui.userCon.getId());
                        if( ServiceRepasServi.getInstance().newMobileRepas(repasServi) )
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    
               
             
            }});
        
        
        addAll(tfName,tfStatus,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new RestoDon_HomeResto().showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
