/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.User;
import com.appTest.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author Amine Gongi
 */
public class ProfilUser_gui extends SideMenuNov {

    EncodedImage enc;
    ImageViewer imgv;
    Image img;

    public ProfilUser_gui() {
        addSideMenu();
        setTitle("Votre Profil");
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
        Button btModifier = new Button(FontImage.MATERIAL_CREATE);

        add(BorderLayout.east(btModifier).add(BorderLayout.CENTER, imgv));
        add(createLineSeparator(0xeeeeee));

        TextField tfusername = new TextField(u.getUsername(), "Username");
        tfusername.setEditable(false);
        tfusername.setUIID("TextFieldBlack");
        addStringValue("Username", tfusername);

        TextField tfemail = new TextField(u.getEmail(), "E-Mail", 50, TextField.EMAILADDR);
        tfemail.setEditable(false);
        tfemail.setUIID("TextFieldBlack");
        addStringValue("E-Mail", tfemail);
        
        TextField tfnumTel = new TextField(u.getEmail(), "Téléphone", 50, TextField.NUMERIC);
        tfnumTel.setEditable(false);
        tfnumTel.setUIID("TextFieldBlack");
        addStringValue("Téléphone", tfnumTel);

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
