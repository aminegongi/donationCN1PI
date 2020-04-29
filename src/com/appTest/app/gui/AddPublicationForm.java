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


/**
 *
 * @author Ahmed Fourati
 */
public class AddPublicationForm extends Form{
    String roleUser=FLogIns_gui.userCon.getRoles();
    //String roleUser="ROLE_RES";
    //int connectedUserId=4; // 3=res ;4=org 
    
    public AddPublicationForm() {
        TextField titre = new TextField("","Titre de publication");
        TextField description = new TextField("","Décrivez votre publication");
        Button ajouterB = new Button("Ajouter la publication");
        TextField nbrePlat = new TextField("", "nombre de plat demandée",4,TextField.NUMERIC);
        //CKeditor editor = new CKeditor();
        
        
        //editor.initAndWait();
        if(roleUser.equals("ROLE_ORG")){
            addAll(titre,description,nbrePlat,ajouterB);
        }
        else if(roleUser.equals("ROLE_RES")){
        addAll(titre,description,ajouterB);
        } 
        ajouterB.addActionListener((ActionListener) (ActionEvent evt) -> {
            if(nbrePlat.getText().equals(""))
                nbrePlat.setText("-1");

//Util.encodeUrl(editor.getData())
                        Publication t = new Publication(3,null, titre.getText(),description.getText(), 0, Integer.parseInt(nbrePlat.getText()),1 ,FLogIns_gui.userCon);
                        if( ServicePublication.getInstance().addPublication(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));

            
        });
        
        
    }
    
    
}
