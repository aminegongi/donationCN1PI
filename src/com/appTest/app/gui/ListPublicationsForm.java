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
import com.appTest.app.entities.User;
import com.appTest.app.services.Ges_User;
import com.appTest.app.services.ServicePublication;
import com.appTest.app.utils.Statics;
import com.codename1.admob.AdMobManager;
import com.codename1.components.ImageViewer;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.List;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javafx.scene.control.ToolBar;
import javafx.scene.shape.Circle;
import org.jsoup.Jsoup;
import org.ocpsoft.prettytime.PrettyTime;


/**
 *
 * @author Ahmed Fourati
 */
public class ListPublicationsForm extends SideMenuNov{
    EncodedImage enc;
    ImageViewer imgv;
    Image img;
    public Button modifierButton ; 
    Toolbar ajouter ;
    PrettyTime ptime;
    SimpleDateFormat dateformat3;
    private AdMobManager admob;
    
    public Container constructPublication(Publication p  )
    {
        Container publicationContainer= new Container(BoxLayout.y(), null);
        publicationContainer.setUIID("containerPublication1");
        Container line1Container = new Container (new BorderLayout(),null);
        Container imageUserNameContainer = new Container (BoxLayout.x(),null);
        Container line2Container = new Container (BoxLayout.y(),null);
        Container line3Container = new Container (new BorderLayout(),null);
        
        try {
        enc = EncodedImage.create("/enc.png");
        } catch (IOException ex) {
            System.out.println("enc ERR");
        }
        String urlImg = Statics.BASE_URL_Image_User+ p.getAjoutePar().getImage();
        img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);
        imgv = new ImageViewer(img);
//        imgv.setWidth(TOP);
        line1Container.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
            System.out.println("Clicked to profile");
            //uncommenti code hetha bech yemshhi lle profile autre mais fama null pointer exception fel parser :(
//           ArrayList<User> tab = Ges_User.getInstance().getUserbyId(p.getId());
//            System.out.println(tab.get(0));
//           new ProfilAutreUser_gui(tab.get(0)).show();
        });
        
        Label userNameLabel = new Label(p.getAjoutePar().getUsername());
        Label phoneNumberLabel =new Label(p.getAjoutePar().getNumTel());
        Label titreLabel =new Label(p.getTitre());
        SpanLabel desLabel =new SpanLabel(Jsoup.parse(p.getDescription()).text());
        Label besoinLabel = new Label();
        System.out.println("X"+p.getType());
        if (p.getType().equals("AppelAuDon")){
        besoinLabel.setText("Nous avons besoin de "+ p.getNbrUp() +"pour nourrir "+p.getNbrePlat());
        }
         Date date1 = new Date();
        
        try {
            date1 = dateformat3.parse(p.getDatePublication());
//            date1 = dateformat3.parse("2020-04-30 13:41");
        } catch (ParseException ex) {
            
        }
//            System.out.println(ptime.format(date1));
            Label dateLabel = new Label(ptime.format(date1));
      
       
       imageUserNameContainer.addAll(imgv,userNameLabel);
       line1Container.add(BorderLayout.WEST,imageUserNameContainer);

       line1Container.add(BorderLayout.EAST,phoneNumberLabel);
       line2Container.addAll(titreLabel,desLabel);
       if (p.getType().equals("AppelAuDon")){
       line2Container.add(besoinLabel);
       }
       line3Container.add(BorderLayout.WEST,dateLabel);
       if(p.getAjoutePar().getId()==FLogIns_gui.userCon.getId())
       {
            modifierButton = new Button("Modifier");
            modifierButton.setUIID("ButtonResto");
           line3Container.add(BorderLayout.EAST,modifierButton);
           modifierButton.addActionListener((ActionListener) (ActionEvent evt) -> {
 new EditPublicationForm(p).show();

 

            });
           
       }
       publicationContainer.addAll(line1Container,line2Container,line3Container);
        return publicationContainer;
    }
    
    public ListPublicationsForm() {

        ptime = new PrettyTime();
        dateformat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
       
        
        
        
        addSideMenu();
        ajouter = getToolbar();
        ajouter.addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD , (ActionListener) (ActionEvent evt) -> {
            new AddPublicationForm().show();
        });
        
        setTitle("Les dernières publications !");
        
        SpanLabel sp = new SpanLabel();
//        sp.setText(ServicePublication.getInstance().getAllPublications().toString());
//        add(sp);
        setLayout(BoxLayout.y());
        
        
        ArrayList<Publication> tab = ServicePublication.getInstance().getAllPublications() ; 
        Collections.sort(tab, (Publication p1 ,Publication p2 ) -> p2.getDatePublication().compareToIgnoreCase(p1.getDatePublication()));
   
        for(int i=0 ; i<tab.size();i++){
        
            add(constructPublication(tab.get(i)));
        }

    }

}
