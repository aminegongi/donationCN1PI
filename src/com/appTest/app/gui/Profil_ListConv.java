/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.User;
import com.appTest.app.entities.UserConversation;
import com.appTest.app.services.Ges_Conversation;
import com.appTest.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.Switch;
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
public class Profil_ListConv extends SideMenuNov {

    EncodedImage enc;
    ImageViewer imgv;
    Image img;
    public Profil_ListConv() {
        setTitle(" Vos Conversations ");
        setLayout(BoxLayout.y());
        User u = FLogIns_gui.userCon;
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new ProfilUser_gui().show();
            }
        });
        
        int idsend=-1;
        ArrayList<UserConversation> convs = Ges_Conversation.getInstance().getConvs(u.getId());
        for(int i=0 ; i<convs.size();i++){
            if(convs.get(i).getSender().getId() != u.getId()){
                add(convContainer(convs.get(i)));
                idsend=convs.get(i).getSender().getId();
            }     
        }
        
    }

    private Container convContainer (UserConversation uc){
        Container border = new Container(new BorderLayout());
        
        Container y = new Container(BoxLayout.y());
        y.add(new Label(uc.getSender().getUsername()));
        y.add(new Label(uc.getMsg()));
        
        try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            System.out.println("enc ERR");
        }
        String urlImg = Statics.BASE_URL_Image_User + uc.getSender().getImage();
        img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);
        imgv = new ImageViewer(img);
        Button btn =new Button(FontImage.MATERIAL_ARROW_RIGHT);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Profil_Chat(uc.getSender()).show();
            }
        });
        
        border.add(BorderLayout.WEST, imgv)
                .add(BorderLayout.CENTER, y)
                .add(BorderLayout.EAST, btn);
        
        return border;
    }
}
