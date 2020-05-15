/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.DemandeAide;
import com.appTest.app.entities.ParticipationAide;
import com.appTest.app.entities.ReactionAide;
import com.appTest.app.services.ServiceDemandeAide;
import com.appTest.app.services.ServiceParticipationAide;
import com.appTest.app.services.ServiceReactionAide;
import com.appTest.app.utils.Statics;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;


import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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

/**
 *
 * @author Hedi
 */
public class ListeDmndAide_gui extends SideMenuNov {

    Form current;
    EncodedImage placeholder;
   
    public ListeDmndAide_gui() {
        addSideMenu();
        getToolbar().setUIID("aide_Toolbar");
        current = this;
        current.setTitle("Aider quelqu'un");
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
        
        
        
        
        
        
        ArrayList<DemandeAide> demandes = ServiceDemandeAide.getInstance().getAllDemandes();
        
        
      for (DemandeAide dmnd : demandes) { 
          
        SpanLabel sp = new SpanLabel();
        sp.setText(dmnd.toString());

        
        
        Container dmndBox = new Container();
        Container partBox = new Container();                
        Container reactBox = new Container();
        Container shareBox = new Container();
        Container btnBox = new Container();
        
        btnBox.addAll(reactBox,partBox,shareBox);
        Label lblNbReact = new Label();
        
        lblNbReact.setUIID("aide_NbReact");
        
        Button partBtn = new Button();

        Style partStyle = UIManager.getInstance().getComponentStyle("aide_PartStyle");
        Style notPartStyle = UIManager.getInstance().getComponentStyle("aide_NotPartStyle");
        Image partIcon = FontImage.createMaterial(FontImage.MATERIAL_PERSON, partStyle);
        Image notPartIcon = FontImage.createMaterial(FontImage.MATERIAL_HOW_TO_REG, notPartStyle);
        partBox.add(partBtn);
        
        //Button cancelPartBtn = new Button("ann part");
        
        ScaleImageButton reactBtn = new ScaleImageButton();
        reactBtn.setUIID("aide_ReactStyle");
        Style upStyle = UIManager.getInstance().getComponentStyle("aide_UpStyle");
        Style downStyle = UIManager.getInstance().getComponentStyle("aide_DownStyle");
        Image upIcon = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE_BORDER, upStyle);
        Image downIcon = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, downStyle);
        reactBox.add(reactBtn);
        

        ShareButton shareBtn = new ShareButton();
        shareBtn.setText("Partager");
        Style shareStyle = UIManager.getInstance().getComponentStyle("aide_ShareStyle");
        Image shareIcon = FontImage.createMaterial(FontImage.MATERIAL_SHARE, shareStyle);
        shareBtn.setUIID("aide_ShareBtn");        
        shareBtn.setIcon(shareIcon);
        shareBtn.setTextToShare("https://donation.tn/aide/participationaide/"+dmnd.getId()+"/new");
        shareBox.add(shareBtn);        
        
        
        
        


         partBtn.addActionListener((evt) -> {
            
             ParticipationAide participation = new ParticipationAide(dmnd.getId(), FLogIns_gui.userCon.getId());
             
             
if(partBtn.getText().equals("Participer")){
                 
                 
                 partBtn.setText("Participé(e)");
                 partBtn.setIcon(notPartIcon);
                 partBtn.setUIID("aide_NotPartBtn");
                 partBox.revalidate();
                 
                 ServiceParticipationAide.getInstance().addParticipationAide(participation);
                 
                 
                 
             }
             else {
                 
                 partBtn.setText("Participer");
                 partBtn.setIcon(partIcon);
                 partBtn.setUIID("aide_PartBtn");
                 partBox.revalidate();
                 
                 ServiceParticipationAide.getInstance().deleteParticipationAide(participation);
             }
        });
        
        
        

        
  
        
         reactBtn.addActionListener((evt) -> {

            ReactionAide reaction = new ReactionAide(dmnd.getId(), FLogIns_gui.userCon.getId());
            int nbReactNow = Integer.parseInt(lblNbReact.getText());
            
            
             if(reactBtn.getName().equals("up")){
                 reactBtn.setName("down");
                 reactBtn.setIcon(downIcon);
                 
                 
                 ServiceReactionAide.getInstance().addReactionAide(reaction);
                 nbReactNow++;
                 lblNbReact.setText(Integer.toString(nbReactNow));
             }
             else {
                 
                 reactBtn.setIcon(upIcon);
                 reactBtn.setName("up");
                 
                 
                 ServiceReactionAide.getInstance().deleteReactionAide(reaction);
                 nbReactNow--;
                 lblNbReact.setText(Integer.toString(nbReactNow));
                 
             }
        });
        
        

        
        //Container descPreview = new Container();
        
        //descPreview.setLayout(BoxLayout.X_AXIS);
        //dmndBox.getToolbar().setUIID("Container");
        dmndBox.setUIID("dmdAide_BackgroundList");
        dmndBox.setLayout(BoxLayout.y());
        
        dmndBox.setUIID("dmndAideBox");
       // Label lblTitre = new Label(dmnd.getTitre());
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
            
            String url = Statics.BASE_URL_Image_Aide + photo;
        
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
        
        
        int nbReaction = dmnd.getNbReaction();
        lblNbReact.setText(Integer.toString(nbReaction));
        reactBox.add(lblNbReact);
        
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(0xeeeeee);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        
        dmndBox.addAll(lblTitre);
        dmndBox.add(cImg);
        dmndBox.addAll(lblDesc);
        dmndBox.add(btnBox);
         dmndBox.add(separator);
       
        
       
        String status = dmnd.getStatus();
        
         //test status( p, r, pr, none) ?
         



       if(status.contains("p")){
         partBtn.setText("Participé(e)");
         partBtn.setIcon(notPartIcon);
         partBtn.setUIID("aide_NotPartBtn");
        
        }
       
       else if (status.contains("p") == false){
         partBtn.setText("Participer");
         partBtn.setIcon(partIcon);
         partBtn.setUIID("aide_PartBtn");
        
        }
       
       
        if(status.contains("r")){
        
        //reactBtn.setText("cancel aime");
        reactBtn.setName("down");
        reactBtn.setIcon(downIcon);
        }
        else if (status.contains("r") == false){
         //reactBtn.setText("aime");  
         reactBtn.setName("up");
         reactBtn.setIcon(upIcon);
        }
       
        
        //if not already participated ==> btnParticiper
//       else {
//        dmndBox.add(partBtn);
//        partBtn.addActionListener((evt) -> {
//           if(Dialog.show("Donner de l'aide","un email sera envoyé au demandeur","Yes","No")){
//            System.out.println("dialog yes");
//            ParticipationAide participation = new  ParticipationAide(dmnd.getId(), FLogIns_gui.userCon.getId());
//            ServiceParticipationAide.getInstance().addParticipationAide(participation);
//            //Dialog.show("Merci pour votre contribution","un email sera envoyé au demandeur");
//        }
//        });}
       
       
       
       
        //dmndBox.addAll(partBtn);
        //Container descPreview =  new Container( new GridLayout(2, 2));
        //descPreview.add(lblDesc);
        //descPreview.add(showBtn);
        
        
        //descPreview.addAll(lblDesc,showBtn);
       // dmndBox.add(descPreview);
        
        
       add(dmndBox);
                      
      }
        /*
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceDemandeAide.getInstance().getAllDemandes().toString());
        add(sp);
*/
    }
    
    
}
