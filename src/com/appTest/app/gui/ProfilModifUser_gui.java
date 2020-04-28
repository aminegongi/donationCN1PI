/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.User;
import com.appTest.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.Switch;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;

/**
 *
 * @author Amine Gongi
 */
public class ProfilModifUser_gui extends SideMenuNov {

    EncodedImage enc;
    ImageViewer imgv;
    Image img;
    int save = 0;

    public ProfilModifUser_gui() {
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (save == 0) {
                    if (Dialog.show("Attention", "Vous n'avez pas enregistrer vos changements", "Sortir", "Ah Oui")) {
                        new ProfilUser_gui().showBack();
                    }
                }
            }
        });
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SAVE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //el traitement t3awed ta3mel user + getUser FLoginC
                new ProfilUser_gui().show();
            }
        });

        setTitle("Modification Profil");
        setLayout(BoxLayout.y());
        User u = FLogIns_gui.userCon;

        try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            System.out.println("enc ERR");
        }

        String urlImg = Statics.BASE_URL_Image_User + u.getImage();

        img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);

        imgv = new ImageViewer(img);
        Container y = new Container(BoxLayout.yCenter());
        Container x = new Container(BoxLayout.xCenter());

        Button btCam = new Button(FontImage.MATERIAL_CAMERA_ALT);
        Button btGal = new Button(FontImage.MATERIAL_PHOTO);
        btCam.setUIID("SkipButton");
        btGal.setUIID("SkipButton");

        y.addAll(btGal, btCam);
        x.addAll(imgv,y);
        add(BorderLayout.center(x));
        add(createLineSeparator(0xeeeeee));

        String sub = u.getRoles().substring(1, u.getRoles().indexOf(","));
        int t = -1;
        if (sub.equals("ROLE_RES")) {
            sub = "Restaurant";
            t = 2;
        } else if (sub.equals("ROLE_US")) {
            sub = "Utilisateur Simple";
            t = 1;
        } else if (sub.equals("ROLE_ORG")) {
            sub = "Organisation";
            t = 2;
        } else if (sub.equals("ROLE_ENT")) {
            sub = "Entreprise";
            t = 2;
        } else if (sub.equals("ROLE_ADMIN")) {
            sub = "Le Boss";
            t = 3;
        }

        if (t == 1) {
            TextField tfnom = new TextField(u.getNom(), "Nom");
            tfnom.setUIID("TextFieldBlack");
            TextField tfprenom = new TextField(u.getPrenom(), "Prénom");
            tfprenom.setUIID("TextFieldBlack");
            //TextField tfdateNaissance = new TextField(u.getDateNaissance(), "Date Naissance");
            Switch sGenre = new Switch();

            Container xx = new Container(BoxLayout.xCenter());

            xx.addAll(new Label("Homme"), sGenre, new Label("Femme"));

            addStringValue("Nom", tfnom);

            addStringValue("Prénom", tfprenom);

            /*if (u.getDateNaissance() != null) {
             addStringValue("date Naissance", tfdateNaissance);
             }*/
            if (u.getGenre().contains("Homme")) {
                sGenre.setOff();
            } else if (u.getGenre().contains("Femme")) {
                sGenre.setOn();
            }
            add(xx);

        } else if (t == 2) {
            TextField tfPageFb = new TextField();
            TextField tfsWeb = new TextField();
            TextArea taDesc = new TextArea();
            TextField tfLong = new TextField();
            TextField tfLat = new TextField();

            addStringValue("Facebook", tfPageFb);
            addStringValue("Site Web", tfsWeb);
            addStringValue("Déscription", taDesc);
            addStringValue("Longitude", tfLong);
            addStringValue("latitude", tfLat);

        }

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    private Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }
}
