/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author safratix
 */
public class RestoDon_QRGenerate extends SideMenuNov {
     EncodedImage enc;
    ImageViewer imgv;
    Image img;
    
    public RestoDon_QRGenerate(String montant){
        setLayout(BoxLayout.y());
        getToolbar().setUIID("RestoDon-Toolbar");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               
                   
                        new RestoDon_MapResto().showBack();
                    
            }
        });
        
        try {
            enc = EncodedImage.create("/giphy.gif").scaledEncoded(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayWidth());
        } catch (IOException ex) {
            System.out.println("enc ERR");
        }

        String urlImg = Statics.BASE_URL + "/qr-code/"+ FLogIns_gui.userCon.getUsername() + "-" + montant + ".png";

        img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);
        img.scaledWidth(800);
        img.scaledHeight(800);
        
        imgv = new ImageViewer(img);
        imgv.setSize(new Dimension(700,700));
        Container cImg = new Container();
        cImg.setSize(new Dimension(700,700));
        cImg.add(BorderLayout.east( imgv));
        add(cImg);
        
        
        
    }
}
