/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author Amine Gongi
 */
public class ProfilUser_gui extends SideMenuNov{

    EncodedImage enc;
    ImageViewer imgv;
    Image img;
    public ProfilUser_gui() {
        addSideMenu();
        setLayout(BoxLayout.y());
        try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            System.out.println("enc ERR");
        }
        String urlImg = Statics.BASE_URL_Image_User+ FLogIns_gui.userCon.getImage() ;
        img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);
        
        imgv = new ImageViewer(img);
        
        addAll(imgv,new Label(FLogIns_gui.userCon.getUsername()));
    }
    
}
