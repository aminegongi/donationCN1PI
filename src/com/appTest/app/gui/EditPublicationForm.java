/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

//import ca.weblite.codename1.components.ckeditor.CKeditor;
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


/**
 *
 * @author Ahmed Fourati
 */
public class EditPublicationForm extends SideMenuNov{
    String roleUser;
    Toolbar TB;
//    String roleUser="ROLE_RES";
    //int connectedUserId=4; // 3=res ;4=org 
    
    public EditPublicationForm(Publication p) {
        TB = getToolbar();
                TB.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (ActionListener) (ActionEvent evt) -> {
            new ListPublicationsForm().showBack();
        });
        roleUser=FLogIns_gui.userCon.getRoles();
        TextField titre = new TextField(p.getTitre(),"Titre de publication");
        TextField description = new TextField(p.getDescription(),"Décrivez votre publication");
        TextField nbrePlat = new TextField("", "nombre de plat demandée",4,TextField.NUMERIC);
        //CKeditor editor = new CKeditor();
System.out.println(roleUser);
        //editor.initAndWait();
        if(roleUser.contains("ROLE_ORG")){
            nbrePlat.setText(String.valueOf(p.getNbrePlat()));
            addAll(titre,description,nbrePlat);
            System.out.println("Ceci ModifierORG");
        }
        else if(roleUser.contains("ROLE_RES")){
        addAll(titre,description);
        System.out.println("Ceci ModifierRes");
        } 
        TB.addMaterialCommandToRightBar("",FontImage.MATERIAL_SAVE,(ActionListener) (ActionEvent evt) -> {
            if(nbrePlat.getText().equals(""))
                nbrePlat.setText("-1");

//Util.encodeUrl(editor.getData())
                        Publication t = new Publication(p.getId(),null, titre.getText(),description.getText(), 0, Integer.parseInt(nbrePlat.getText()),1 ,FLogIns_gui.userCon);
                        if( ServicePublication.getInstance().editPublication(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));

            
        });
        
        
    }
    
    
}
