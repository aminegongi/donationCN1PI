/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.MyApplication;
import com.appTest.app.entities.User;
import com.appTest.app.entities.UserConversation;
import com.appTest.app.services.Ges_Conversation;
import com.appTest.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.Switch;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
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
import java.util.ArrayList;

/**
 *
 * @author Amine Gongi
 */
public class Profil_Chat extends SideMenuNov {

    TextField tf ;
    
    public Profil_Chat(User u) {
        getToolbar().setUIID("ToolbarAmine");
        
        setTitle(u.getUsername());
        setLayout(new BorderLayout());
        tf = new TextField();
        int idMe =FLogIns_gui.userCon.getId();
        int idAutre = u.getId();
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new Profil_ListConv().showBack();
            }
        });
        
        BrowserComponent browser = new BrowserComponent();
        browser.setURL(Statics.BASE_URL+"/user/11chat?idAutre="+idAutre+"&idMe="+idMe);
        
        Button bt = new Button(FontImage.MATERIAL_SEND);
        
        tf.setUIID("TexteFieldPassAmine");
        bt.setUIID("ButtonProfilAmine");
        tf.setHint("Votre message:");
        
        bt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Ges_Conversation.getInstance().sendChat(idMe, idAutre, tf.getText());
                tf.clear();
            }
        });
        
        Container tfbt = new Container(new BorderLayout());
        tfbt.add(BorderLayout.CENTER, tf).
                add(BorderLayout.EAST, bt);
        
        add(BorderLayout.CENTER, browser).
                add(BorderLayout.SOUTH, tfbt);
        

        
    }

}
