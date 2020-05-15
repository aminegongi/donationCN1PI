/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.DemandeAide;
import com.appTest.app.entities.ParticipationAide;
import com.appTest.app.entities.User;
import com.appTest.app.services.ServiceDemandeAide;
import com.appTest.app.services.ServiceParticipationAide;
import com.appTest.app.utils.Statics;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Hedi
 */
public class ListeParticipationsAide_gui extends SideMenuNov{

    Form current;
    EncodedImage placeholder;
    public ListeParticipationsAide_gui(int idDmnd) {
        
            //addSideMenu();
            getToolbar().setUIID("aide_Toolbar");
            
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK_IOS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //el traitement t3awed ta3mel user + getUser FLoginC
                new MesDmndAide_gui().showBack();
            }
        });
        current = this;
        current.setTitle("Mes Demandes d'Aide");
        current.setLayout(BoxLayout.y());
        
        
        
        
        
        
        
        //floating button
        FloatingActionButton aide_FAB = new FloatingActionButton(FontImage.MATERIAL_DASHBOARD, "hello", "aide-FAB", 5) {};
        
        
        FloatingActionButton aideAccueil_FAB = aide_FAB.createSubFAB(FontImage.MATERIAL_COLLECTIONS, "Accueil");
        aideAccueil_FAB.setUIID("aide-FAB");
        aideAccueil_FAB.setPreferredSize(new Dimension(200,200));
        aideAccueil_FAB.addActionListener(e -> new ListeDmndAide_gui().show());
        
        
        FloatingActionButton aideMesDmnd_FAB = aide_FAB.createSubFAB(FontImage.MATERIAL_SPEAKER_NOTES, "Mes Demandes");
        aideMesDmnd_FAB.setUIID("aide-FAB"); 
        aideMesDmnd_FAB.setPreferredSize(new Dimension(200,200));
        aideMesDmnd_FAB.addActionListener(e -> new MesDmndAide_gui().show());
        
        
        
        
        
        FloatingActionButton addDmnd_FAB = aide_FAB.createSubFAB(FontImage.MATERIAL_RECORD_VOICE_OVER, "Publier une demande");
        addDmnd_FAB.setUIID("aide-FAB");
        addDmnd_FAB.setPreferredSize(new Dimension(200,200));
        addDmnd_FAB.addActionListener(e -> new AddDmndAide_gui().show());        
        
        
        
        
        aide_FAB.bindFabToContainer(getContentPane());
        
        
        
       // ArrayList<ParticipationAide> participations = ServiceParticipationAide.getInstance().getAllParticipations(idDmnd);
       ArrayList<User> participants = ServiceParticipationAide.getInstance().getAllParticipations(idDmnd);
        
       int size = Display.getInstance().convertToPixels(7);
//EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(size, size, 0xff000000), true); 
        
        try {
            placeholder = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            
        }
    //  for (ParticipationAide part : participations) { 
           for (User part : participants) { 
          
            MultiButton mb = new MultiButton(part.getUsername());
            mb.setTextLine2(part.getEmail());
            
            
                    String photo = part.getImage();
            
            
            String url = Statics.BASE_URL_Image_User + part.getImage();
            System.out.println(url);
        
            URLImage imagePart = URLImage.createToStorage(placeholder, url,
            url);
            
            
            

            
            mb.addActionListener((evt) -> {
                 new Profil_Chat(part,current).show();
            });
        

/*
Image[] pictures = {
    URLImage.createToStorage(placeholder, part.getUsername(),part.getImage()),
    URLImage.createToStorage(placeholder, part.getUsername(),part.getImage()),
    URLImage.createToStorage(placeholder, part.getUsername(),part.getImage())
};

*/

     mb.setIcon(URLImage.createToStorage(placeholder, url,url));
       
               
               
               
               
               
               
               
               
               
        SpanLabel sp = new SpanLabel();
        sp.setText(part.toString());
        //add(sp);
        
        //Button participantsBtn = new Button("participants");
        
        
        Container partBox = new Container();
        //dmndBox.getToolbar().setUIID("Container");
        //dmndBox.setUIID("dmdAide_BackgroundList");
        //dmndBox.setLayout(BoxLayout.y());
        
       // dmndBox.setUIID("dmndAideBox");
       // Label lblMail = new Label(part.getEmailUser());
        //lblTitre.setUIID("dmndAideItem_Title");
        
        Label lblMail = new Label(part.getNumTel());
        
        partBox.add(lblMail);
        
        
        
        
      // add(partBox);
         add(mb);           
      }
        
        
        
    }
    
}
