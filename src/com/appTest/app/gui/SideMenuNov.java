/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.services.Ges_User;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.Layout;

/**
 *
 * @author Amine Gongi
 */
public class SideMenuNov extends Form {

    public SideMenuNov() {
    }

    public SideMenuNov(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public SideMenuNov(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    protected void addSideMenu() {
        Toolbar tb = getToolbar();
        
        /*Image img = res.getImage("profile-background.jpg");
         if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
         img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
         }
         ScaleImageLabel sl = new ScaleImageLabel(img);
         sl.setUIID("BottomPad");
         sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
         tb.addComponentToSideMenu(LayeredLayout.encloseIn(
         sl,
         FlowLayout.encloseCenterBottom(
         new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond"))
         ));*/

        if (FLogIns_gui.userCon == null) {
            tb.removeAll();
            Container c = new Container(BoxLayout.x());
            Label Nom = new Label("Not Connected");
            c.add(Nom);

            tb.addComponentToSideMenu(c);
            tb.addMaterialCommandToSideMenu(" Inscription", FontImage.MATERIAL_HOME, (ActionListener) (ActionEvent evt) -> {
                new Inscrition_gui().show();
            });
            tb.addMaterialCommandToSideMenu(" Login", FontImage.MATERIAL_KEYBOARD, (ActionListener) (ActionEvent evt) -> {
                new Login_gui().show();
            });
        } else {
            tb.removeAll();
            Container c = new Container(BoxLayout.x());
            Label Nom = new Label("Username");
            c.add(Nom);

            tb.addComponentToSideMenu(c);
            tb.addMaterialCommandToSideMenu(" Home", FontImage.MATERIAL_HOME, (ActionListener) (ActionEvent evt) -> {
                new Home_gui().show();
            });
            tb.addMaterialCommandToSideMenu(" Profil", FontImage.MATERIAL_PEOPLE, (ActionListener) (ActionEvent evt) -> {
                new ProfilUser_gui().show();
            });
            tb.addMaterialCommandToSideMenu(" Cagnotte", FontImage.MATERIAL_ALARM, (ActionListener) (ActionEvent evt) -> {
                new Home_gui().show();
            });
            tb.addMaterialCommandToSideMenu(" Aide", FontImage.MATERIAL_KEYBOARD, (ActionListener) (ActionEvent evt) -> {
                new Home_gui().show();
            });
            tb.addMaterialCommandToSideMenu(" Emploi", FontImage.MATERIAL_ANDROID, (ActionListener) (ActionEvent evt) -> {
                new Home_gui().show();
            });
            tb.addMaterialCommandToSideMenu(" Resto Don", FontImage.MATERIAL_MAIL, (ActionListener) (ActionEvent evt) -> {
                new Home_gui().show();
            });
            tb.addMaterialCommandToSideMenu(" Resto Organisation", FontImage.MATERIAL_HELP, (ActionListener) (ActionEvent evt) -> {
                new ListPublicationsForm().show();
            });
            tb.addMaterialCommandToSideMenu(" LogOut", FontImage.MATERIAL_LOGOUT, (ActionListener) (ActionEvent evt) -> {
                FLogIns_gui.userCon = null;
                Ges_User.getInstance().deleteDb();
                new FLogIns_gui().show();
            });
        }
        

    }

}
