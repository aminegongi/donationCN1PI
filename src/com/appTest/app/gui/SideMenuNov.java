/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.services.Ges_User;
import com.appTest.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;

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
            EncodedImage enc = null;
            ImageViewer imgv;
            ImageViewer bv;
            Image img;
            try {
                enc = EncodedImage.create("/giphy.gif");
            } catch (IOException ex) {
                System.out.println("enc ERR");
            }

            String urlImg = Statics.BASE_URL_Image_User + FLogIns_gui.userCon.getImage();
            Image i = null;
            try {
                 i =Image.createImage("/bgBBP.png");
            } catch (IOException ex) {
            }

            img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);
            if (i.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
                i = i.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
            }
            ScaleImageLabel sl = new ScaleImageLabel(i);
            sl.setUIID("BottomPad");
            sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                    sl,
                    FlowLayout.encloseBottom(
                            new Label(img, "PictureWhiteBackgrond")),
                    FlowLayout.encloseCenterBottom(
                             new Label(FLogIns_gui.userCon.getUsername(),"labelSidePictureAmine")
                    )
                   
            ));
            tb.addComponentToSideMenu(c);
            tb.addMaterialCommandToSideMenu(" Home", FontImage.MATERIAL_HOME, (ActionListener) (ActionEvent evt) -> {
                new Home_gui().show();
            });
            tb.addMaterialCommandToSideMenu(" Profil", FontImage.MATERIAL_PEOPLE, (ActionListener) (ActionEvent evt) -> {
                new ProfilUser_gui().show();
            });
            tb.addMaterialCommandToSideMenu(" Cagnotte", FontImage.MATERIAL_PAYMENT, (ActionListener) (ActionEvent evt) -> {
                new Cagnotte_Home().show();
            });
            tb.addMaterialCommandToSideMenu(" Aide", FontImage.MATERIAL_HEARING, (ActionListener) (ActionEvent evt) -> {
                new ListeDmndAide_gui().show();
            });
            tb.addMaterialCommandToSideMenu(" Emploi", FontImage.MATERIAL_WORK, (ActionListener) (ActionEvent evt) -> {
                new h_listeEmploi_emploi().show();
            });
            tb.addMaterialCommandToSideMenu(" Resto Don", FontImage.MATERIAL_CARD_GIFTCARD, (ActionListener) (ActionEvent evt) -> {
                if(FLogIns_gui.userCon.getRoles().contains("RES")){
                    new RestoDon_HomeResto().show();
                }else{
                new RestoDon_MapResto().show();
                }
            });
            tb.addMaterialCommandToSideMenu(" Resto Organisation", FontImage.MATERIAL_RESTAURANT, (ActionListener) (ActionEvent evt) -> {
                new PubForm(new ListPublicationsForm()).show();
                //new ListPublicationsForm().show();
            });
            tb.addMaterialCommandToSideMenu(" LogOut", FontImage.MATERIAL_LOGOUT, (ActionListener) (ActionEvent evt) -> {
                FLogIns_gui.userCon = null;
                Ges_User.getInstance().deleteDb();
                new FLogIns_gui().show();
            });
        }

        

    }
    
    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

}
