/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.appTest.app.entities.Publication;
import com.appTest.app.services.ServicePublication;
import com.appTest.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.shape.Circle;

/**
 *
 * @author Ahmed Fourati
 */
public class ListPublicationsForm extends Form{
    EncodedImage enc;
    ImageViewer imgv;
    Image img;
    
    public Container constructPublication(Publication p  )
    {
        Container publicationContainer= new Container(BoxLayout.y(), null);
        Container line1Container = new Container (new BorderLayout(),null);
        Container imageUserNameContainer = new Container (BoxLayout.x(),null);
        Container line2Container = new Container (BoxLayout.y(),null);
        Container line3Container = new Container (BoxLayout.x(),null);
        
        try {
        enc = EncodedImage.create("/enc.png");
        } catch (IOException ex) {
            System.out.println("enc ERR");
        }
        String urlImg = Statics.BASE_URL_Image_User+ p.getAjoutePar().getImage();
        img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);
        imgv = new ImageViewer(img);
//        imgv.setWidth(TOP);
        
        Label userNameLabel = new Label(p.getAjoutePar().getUsername());
        Label phoneNumberLabel =new Label(p.getAjoutePar().getNumTel());
        Label titreLabel =new Label(p.getTitre());
        Label desLabel =new Label(p.getTitre());
        Label besoinLabel = new Label();
        System.out.println("X"+p.getType());
        if (p.getType().equals("AppelAuDon")){
        besoinLabel.setText("Nous avons besoin de "+ p.getNbrUp() +"pour nourrir "+p.getNbrePlat());
        }
        Label dateLabel = new Label(p.getDatePublication());
       imageUserNameContainer.addAll(imgv,userNameLabel);
       line1Container.add(BorderLayout.WEST,imageUserNameContainer);

       line1Container.add(BorderLayout.EAST,phoneNumberLabel);
       line2Container.addAll(titreLabel,desLabel);
       if (p.getType().equals("AppelAuDon")){
       line2Container.add(besoinLabel);
       }
       line3Container.addAll(dateLabel);
       publicationContainer.addAll(line1Container,line2Container,line3Container);
        return publicationContainer;
    }
    
    public ListPublicationsForm() {
        setTitle("List pub");
        
        SpanLabel sp = new SpanLabel();
//        sp.setText(ServicePublication.getInstance().getAllPublications().toString());
//        add(sp);
        setLayout(BoxLayout.y());
        
        
        ArrayList<Publication> tab = ServicePublication.getInstance().getAllPublications() ; 
        for(int i=0 ; i<tab.size();i++){
        
            add(constructPublication(tab.get(i)));
        }
        
        
        

    }
}
