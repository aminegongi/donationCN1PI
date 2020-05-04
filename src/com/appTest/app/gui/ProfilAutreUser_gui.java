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
public class ProfilAutreUser_gui extends SideMenuNov {

    EncodedImage enc;
    ImageViewer imgv;
    Image img;

    public ProfilAutreUser_gui(User tu) {
        addSideMenu();
        setTitle("Votre Profil");
        setLayout(BoxLayout.y());
        User u = tu;

        try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            System.out.println("enc ERR");
        }

        String urlImg = Statics.BASE_URL_Image_User + u.getImage();

        img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);

        imgv = new ImageViewer(img);
        Button btModifier = new Button(FontImage.MATERIAL_EDIT);
        add(BorderLayout.east(btModifier).add(BorderLayout.CENTER, imgv));
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
        Label lRole = new Label(sub);
        lRole.setUIID("TextFieldBlack");
        addStringValue("Role", lRole);

        TextField tfusername = new TextField(u.getUsername(), "Username");
        tfusername.setEditable(false);
        tfusername.setUIID("TextFieldBlack");
        addStringValue("Username", tfusername);

        TextField tfemail = new TextField(u.getEmail(), "E-Mail", 50, TextField.EMAILADDR);
        tfemail.setEditable(false);
        tfemail.setUIID("TextFieldBlack");
        addStringValue("E-Mail", tfemail);

        TextField tfnumTel = new TextField(u.getNumTel(), "Téléphone");
        tfnumTel.setEditable(false);
        tfnumTel.setUIID("TextFieldBlack");
        addStringValue("Téléphone", tfnumTel);

        TextField tfAdresse = new TextField(u.getAdresse().getPays()+","+u.getAdresse().getVille(), "Adresse");
        tfAdresse.setUIID("TextFieldBlack");
        tfAdresse.setEditable(false);
        if (t == 1) {
            TextField tfnom = new TextField(u.getNom(), "Nom");
            tfnom.setUIID("TextFieldBlack");
            TextField tfprenom = new TextField(u.getPrenom(), "Prénom");
            tfprenom.setUIID("TextFieldBlack");
            //TextField tfdateNaissance = new TextField(u.getDateNaissance(), "Date Naissance");
            Switch sGenre = new Switch();
            
            tfnom.setEditable(false);
            tfprenom.setEditable(false);
            //tfdateNaissance.setEditable(false);
            sGenre.setEnabled(false);
            
            Container x = new Container(BoxLayout.xCenter());

            x.addAll(new Label("Homme"), sGenre, new Label("Femme"));

            if (u.getNom() != null) {
                addStringValue("Nom", tfnom);
            }
            if (u.getPrenom() != null) {
                addStringValue("Prénom", tfprenom);
            }
            /*if (u.getDateNaissance() != null) {
                addStringValue("date Naissance", tfdateNaissance);
            }*/
            if(u.getAdresse().getPays() != null){
                addStringValue("Adresse", tfAdresse);
            }
            if (u.getGenre() != null) {
                if (u.getGenre().contains("Homme")) {
                    sGenre.setOff();
                } else if (u.getGenre().contains("Femme")) {
                    sGenre.setOn();
                }
                add(x);
            }

        } else if (t == 2) {
            TextField tfPageFb = new TextField(u.getPageFB(), "Facebook");
            TextField tfsWeb = new TextField(u.getSiteWeb(), "Site Web");
            TextField tfDesc = new TextField(u.getDescription(), "Déscription");
            
            TextField tfLong = new TextField(Float.toString(u.getLongitude()), "Longitude");
            TextField tfLat = new TextField(Float.toString(u.getLatitude()), "Latitude");
            
            tfPageFb.setEditable(false);
            tfsWeb.setEditable(false);
            tfDesc.setEditable(false);
            tfLong.setEditable(false);
            tfLat.setEditable(false);
            
            if(u.getAdresse().getPays() != null){
                addStringValue("Adresse", tfAdresse);
            }
            if (u.getPrenom() != null) {
                addStringValue("Prénom", tfPageFb);
            }
            if (u.getPrenom() != null) {
                addStringValue("Prénom", tfsWeb);
            }
            if (u.getPrenom() != null) {
                addStringValue("Prénom", tfDesc);
            }
            if (u.getPrenom() != null) {
                addStringValue("Prénom", tfLong);
            }
            if (u.getPrenom() != null) {
                addStringValue("Prénom", tfLat);
            }

        }
        
        btModifier.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new ProfilModifUser_gui().show();
            }
        });

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

}
