/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.Pub;
import com.appTest.app.services.ServicePub;
import com.appTest.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ahmed Fourati
 */
public class PubForm extends Form  {
    private EncodedImage enc ;
    String urlImg ; 
    ImageViewer imgv;
    Image img;
    Toolbar tb ; 
    
    public PubForm(Form previousOne){
        setUIID("bgPub");
        tb= getToolbar();
        setTitle("Espace Publicité");
        tb.setUIID("ToolbarFourati");
        setLayout(new BorderLayout());
        ArrayList<Pub> tab=ServicePub.getInstance().getPub(3);
        System.out.println(tab.get(0));
                try {
            enc = EncodedImage.create("/bgBBP.png");
        } catch (IOException ex) {
            System.out.println("enc ERR");
        }
         urlImg = Statics.BASE_URL_Upload_Image_Pub+ tab.get(0).getImage();
         System.out.println(urlImg);
        img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);
        
        imgv = new ImageViewer(img);
        Container c = new Container(BoxLayout.y());
        Label titreLabel = new Label(tab.get(0).getTitre());
        titreLabel.setUIID("titrePub");
        SpanLabel titreDesc = new SpanLabel("Pour plus d'information visitez "+tab.get(0).getDescription());
        titreDesc.setUIID("titreDesc");
        c.add(titreLabel);
        c.add(titreDesc);
        add(BorderLayout.NORTH,c);
        add(BorderLayout.CENTER,imgv);
        tb.addMaterialCommandToRightBar("", FontImage.MATERIAL_CLOSE, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                previousOne.show();
            }
        });
    }
    
}
