/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.DemandeAide;
import com.appTest.app.services.ServiceDemandeAide;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.paint.Color.color;

/**
 *
 * @author Hedi
 */
public class MesDmndAide_gui extends SideMenuNov {

    Form current;
    EncodedImage placeholder;
    public MesDmndAide_gui() {
        addSideMenu();
        getToolbar().setUIID("aide_Toolbar");
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
        
        
        
        
        
        
        
        
        
        /*
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceDemandeAide.getInstance().getMesDemandes().toString());
        add(sp);*/
        
        
        ArrayList<DemandeAide> demandes = ServiceDemandeAide.getInstance().getMesDemandes();
        
        
      for (DemandeAide dmnd : demandes) { 
          
        SpanLabel sp = new SpanLabel();
        sp.setText(dmnd.toString());
        //add(sp);
        
        Label lblNbReact = new Label(Integer.toString(dmnd.getNbReaction()));
        lblNbReact.setUIID("aide_NbReact");
        
        Container infoBox = new Container();
        Container reactBox = new Container();
        Container partBox = new Container();
        Container shareBox = new Container(); 
        
        
        ScaleImageButton reactBtn = new ScaleImageButton();
        reactBtn.setUIID("aide_ReactStyle");
        Style downStyle = UIManager.getInstance().getComponentStyle("aide_DownStyle");
        Image downIcon = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, downStyle);
        reactBtn.setIcon(downIcon);
        
        Button participantsBtn = new Button("participants");
        Style partStyle = UIManager.getInstance().getComponentStyle("aide_PartStyle");
        Image partIcon = FontImage.createMaterial(FontImage.MATERIAL_PEOPLE, partStyle);
        participantsBtn.setIcon(partIcon);
        participantsBtn.setUIID("aide_PartBtn");
        
        
        ShareButton shareBtn = new ShareButton();
        shareBtn.setText("Partager");
        Style shareStyle = UIManager.getInstance().getComponentStyle("aide_ShareStyle");
        Image shareIcon = FontImage.createMaterial(FontImage.MATERIAL_SHARE, shareStyle);
        shareBtn.setUIID("aide_ShareBtn");
        
        shareBtn.setIcon(shareIcon);
        shareBtn.setTextToShare("https://donation.tn/aide/participationaide/"+dmnd.getId()+"/new");
        shareBox.add(shareBtn);
        
        
        
        reactBox.add(reactBtn);
        reactBox.add(lblNbReact);
        partBox.add(participantsBtn);
        infoBox.addAll(reactBox,partBox,shareBox);
        
        
        
        
        participantsBtn.addActionListener((evt) -> {
             new ListeParticipationsAide_gui(dmnd.getId()).show();
        });
        
        Container dmndBox = new Container();
        //dmndBox.getToolbar().setUIID("Container");
        dmndBox.setUIID("dmdAide_BackgroundList");
        dmndBox.setLayout(BoxLayout.y());
        
        dmndBox.setUIID("dmndAideBox");
        //Label lblTitre = new Label(dmnd.getTitre());
        TextArea lblTitre = new TextArea(dmnd.getTitre());
        lblTitre.setEditable(false);
        lblTitre.setFocusable(false);
        lblTitre.setUIID("dmndAideItem_Title");
        
        
        
        String photo = dmnd.getPhoto();
        
            try {
                //EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(current.getWidth(), current.getHeight()/ 5, 0xe4e6e8), true);
                 placeholder = EncodedImage.create("/logodonationC.png").scaledEncoded(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayWidth()/2);
            } catch (IOException ex) {
               
            }
            
            String url = "http://localhost/donationwebpidev/web/uploads/imagesAide/"+photo;
        
            URLImage imageDmnd = URLImage.createToStorage(placeholder, url,
            url, URLImage.RESIZE_SCALE);
        
        imageDmnd.scaledWidth(800);
        imageDmnd.scaledHeight(400);
        
        ImageViewer imgv = new ImageViewer(imageDmnd);
        imgv.setSize(new Dimension(700,400));
        
        
        Container cImg = new Container();
        cImg.setSize(new Dimension(700,400));
        cImg.add(imgv);
        
        //SpanLabel lblDesc = new SpanLabel(dmnd.getDescription());
        TextArea lblDesc = new TextArea(dmnd.getDescription());
        lblDesc.setEditable(false);
        lblDesc.setUIID("dmndAideItem_desc");
        lblDesc.setFocusable(false);
        
        
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(0xeeeeee);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        
         dmndBox.addAll(lblTitre);
        //dmndBox.add(imageDmnd);
        dmndBox.add(cImg);
        dmndBox.addAll(lblDesc);
        //dmndBox.addAll(participantsBtn);
        dmndBox.add(infoBox);
        dmndBox.add(separator);
        
        
        
       add(dmndBox);
                      
      }
        
        
    }
    
    
}
