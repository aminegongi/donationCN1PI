/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

//import ca.weblite.codename1.components.ckeditor.CKeditor;
import ca.weblite.codename1.components.ckeditor.CKeditor;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.appTest.app.entities.Publication;
import com.appTest.app.gui.FLogIns_gui;
import com.appTest.app.services.ServicePublication;
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author Ahmed Fourati
 */
public class AddPublicationForm extends SideMenuNov{
    String roleUser=FLogIns_gui.userCon.getRoles();
    Toolbar TB;
    //String roleUser="ROLE_RES";
    //int connectedUserId=4; // 3=res ;4=org 
    
    public AddPublicationForm() {
        setLayout(new BorderLayout());
        setTitle("Ajouter une publication");
        TB = getToolbar();
        TB.setUIID("ToolbarFourati");
        TB.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (ActionListener) (ActionEvent evt) -> {
            new ListPublicationsForm().showBack();
        });
        TextField titre = new TextField("","Titre de publication");
        TextField description = new TextField("","Décrivez votre publication");
        TextField nbrePlat = new TextField("", "nombre de plat demandée",4,TextField.NUMERIC);
        CKeditor editor = new CKeditor();
        editor.setUIID("editor");

        
        
        
        editor.initAndWait();
        if(roleUser.contains("ROLE_ORG")){
            
            add(BorderLayout.NORTH,titre);
            add(BorderLayout.SOUTH,editor);
            add(BorderLayout.CENTER,nbrePlat);
            
        }
        else if(roleUser.contains("ROLE_RES")){
                    add(BorderLayout.NORTH,titre);
                    add(BorderLayout.CENTER,editor);


        } 
        TB.addMaterialCommandToRightBar("",FontImage.MATERIAL_SAVE,(ActionListener) (ActionEvent evt) -> {
            if(nbrePlat.getText().equals(""))
                nbrePlat.setText("-1");
            System.out.println("Usercncr"+FLogIns_gui.userCon);

                        Publication t = new Publication(3,null, titre.getText(),Util.encodeUrl(editor.getData()), 0, Integer.parseInt(nbrePlat.getText()),1 ,FLogIns_gui.userCon);
                        if( ServicePublication.getInstance().addPublication(t)){
                            Dialog.show("Ajout avec succées Publication","Votre publication est bien ajouté dans la plateforme .",new Command("OK"));
                            new ListPublicationsForm().showBack();
                            }
                        else{
                            Dialog.show("Problème d'ajout", "Votre publication n'est pas ajouté , essayez de nouveau.", new Command("OK"));
                            new ListPublicationsForm().showBack();
                            }

            
        });
        
        
    }
    
    
}
