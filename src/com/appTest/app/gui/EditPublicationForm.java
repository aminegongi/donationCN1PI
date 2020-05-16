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
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author Ahmed Fourati
 */
public class EditPublicationForm extends SideMenuNov{
    String roleUser;
    Toolbar TB;
    Button supprimerButton; 
//    String roleUser="ROLE_RES";
    //int connectedUserId=4; // 3=res ;4=org 
    
    public EditPublicationForm(Publication p) {
        setLayout(new BorderLayout());
        setTitle("Modifier Votre publication");
        supprimerButton = new Button("supprimer la publication");
        supprimerButton.setUIID("ButtonResto");
                CKeditor editor = new CKeditor();
        editor.setUIID("editor");
        editor.initAndWait();
        TB = getToolbar();
        TB.setUIID("ToolbarFourati");
                TB.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (ActionListener) (ActionEvent evt) -> {
            new ListPublicationsForm().showBack();
        });
                supprimerButton.addActionListener((ActionListener) (ActionEvent evt) -> {
                    if(Dialog.show("Supprimer publication", "Cette publication sera supprimer completement .", "Supprimer", "Annuler"))
                    {
                        //ssupression
                            if( ServicePublication.getInstance().deletePublication(p)){
                            Dialog.show("Suppression effectué avec succées ","Votre publication est supprimée de la plateforme",new Command("OK"));
                            new ListPublicationsForm().showBack();
                            }
                        else{
                            Dialog.show("Problème de suppresion", "Votre publication n'est pas supprimée correctement , essayez de nouveau.", new Command("OK"));
                            new ListPublicationsForm().showBack();
                            }
                    }
                    
        });
        roleUser=FLogIns_gui.userCon.getRoles();
        TextField titre = new TextField(p.getTitre(),"Titre de publication");
//        TextField description = new TextField(p.getDescription(),"Décrivez votre publication");
        editor.setData(p.getDescription());
        TextField nbrePlat = new TextField("", "nombre de plat demandée",4,TextField.NUMERIC);
System.out.println(roleUser);
        if(roleUser.contains("ROLE_ORG")){
            nbrePlat.setText(String.valueOf(p.getNbrePlat()));

            add(BorderLayout.NORTH,titre);
            add(BorderLayout.SOUTH,editor);
            Container c = new Container(BoxLayout.y());
            c.addAll(editor,supprimerButton);
            
            add(BorderLayout.CENTER,nbrePlat);
            add(BorderLayout.SOUTH,c);
            System.out.println("Ceci ModifierORG");
        }
        else if(roleUser.contains("ROLE_RES")){
                            add(BorderLayout.NORTH,titre);
                    add(BorderLayout.CENTER,editor);
                    add(BorderLayout.SOUTH,supprimerButton);
        System.out.println("Ceci ModifierRes");
        } 
        TB.addMaterialCommandToRightBar("",FontImage.MATERIAL_SAVE,(ActionListener) (ActionEvent evt) -> {
            if(nbrePlat.getText().equals(""))
                nbrePlat.setText("-1");


                        Publication t = new Publication(p.getId(),null, titre.getText(),Util.encodeUrl(editor.getData()), 0, Integer.parseInt(nbrePlat.getText()),1 ,FLogIns_gui.userCon);
                        if( ServicePublication.getInstance().editPublication(t)){
                            Dialog.show("Modification effectué avec succées ","Votre publication est bien modifé",new Command("OK"));
                            new ListPublicationsForm().showBack();
                            }
                        else{
                            Dialog.show("Problème de mofification", "Votre publication n'est pas modifié , essayez de nouveau.", new Command("OK"));
                            new ListPublicationsForm().showBack();
                            }

            
        });
        
        
    }
    
    
}
