/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.CategorieAide;
import com.appTest.app.entities.DemandeAide;
import com.appTest.app.services.ServiceCategorieAide;
import com.appTest.app.services.ServiceDemandeAide;
import com.appTest.app.utils.AideImgUpload;
import com.appTest.app.utils.OpenGalleryAide;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
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
public class AddDmndAide_gui extends SideMenuNov {
    Form current;
    Button btnVal = new Button("Publier");
    TextField tfTitre = new TextField();
    //TextField tfDesc = new TextField();
    TextArea tfDesc = new TextArea();
    MultiButton b = new MultiButton("Catégorie...");
     Button btnPhoto = new Button("Ajouter Photo ");
     Container imgCnt = new Container();
    String[] catNames = ServiceCategorieAide.getInstance().getAllCatNames();
    Integer[] catIds = ServiceCategorieAide.getInstance().getAllCatIds();
    String categorieNom;
    int categorieId;
    String imgp;
    AideImgUpload uploader = new AideImgUpload();
    EncodedImage placeholder;
           
    
    
    

    public AddDmndAide_gui() {
        addSideMenu();
        getToolbar().setUIID("aide_Toolbar");
        current = this;
        current.setTitle("Publier Une Demande");
        current.setLayout(BoxLayout.y());
        
        tfTitre.setHint("Titre De La Demande");
        tfTitre.setUIID("TextFieldBlack");
        tfDesc.setHint("Description");
        tfDesc.setRows(5);
        
        Style photoStyle = UIManager.getInstance().getComponentStyle("aide_PhotoStyle");
        Image photoIcon = FontImage.createMaterial(FontImage.MATERIAL_ADD_PHOTO_ALTERNATE, photoStyle);
        btnPhoto.setIcon(photoIcon);
        btnPhoto.setUIID("aide_PhotoBtn");
        
        current.addAll(tfTitre,tfDesc,b,imgCnt,btnPhoto);
        current.add(btnVal);
        
        
        b.setUIID("multiBtn_Aide");
        b.setUIIDLine1("aide_PhotoBtn");
        
        btnVal.setUIID("aide_ValiderBtn");
        
        
        
        
        
        
        
        
        
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
        
        
        
        
        
        
        
        
        
        
        
        




b.addActionListener(e -> {
    Dialog d = new Dialog();
    //d.setUIID("aide_Dialog");
    d.setDialogUIID("aide_Dialog");
    d.setDefaultBlurBackgroundRadius(8);
    //current.setTintColor(0);
    
    d.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
    for(int iter = 0 ; iter < catNames.length ; iter++) {
        int i = iter;
        MultiButton mb = new MultiButton(catNames[iter]);
        mb.setUIID("aide_Multi");
        mb.setUIIDLine1("aide_MultiTxt");
        
        mb.addActionListener((evt) -> {
            categorieNom = mb.getTextLine1();
            categorieId = catIds[i];
        });
        d.add(mb);
        mb.addActionListener(ee -> {
            b.setTextLine1(mb.getTextLine1());
            b.setTextLine2(mb.getTextLine2());
            b.setIcon(mb.getIcon());
            d.dispose();
            b.revalidate();
        });
    }
    d.showPopupDialog(b);
});


        
           
        
        
        btnVal.addActionListener((evt) -> {
            System.out.println("aaaaaaaaaaa");
            System.out.println(categorieNom);
            System.out.println(categorieId);
            
            DemandeAide dmnd = new DemandeAide();
            dmnd.setTitre(tfTitre.getText());
            dmnd.setDescription(tfDesc.getText());
            dmnd.setIdCategorie(categorieId);
            dmnd.setIdUser(FLogIns_gui.userCon.getId());
            
            String photo =uploader.upload(imgp);
            dmnd.setPhoto(photo);
            
            
            dmnd.setNbReaction(0);
            dmnd.setEtat("valide");
            
            ServiceDemandeAide.getInstance().addDemandeAide(dmnd);
            
            
            
            new ListeDmndAide_gui().show();
        });
        
        /*
        btnPhoto.addActionListener((evt) -> {
            
            OpenGalleryAide gallery = new OpenGalleryAide();
            imgp = gallery.openGallery();
            
            
        });*/
        
        
        
        
        btnPhoto.addActionListener(new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
     
            // TODO Auto-generated method stub
            Display.getInstance().openGallery((e) -> {
                if(e != null && e.getSource() != null) {
                    String file = (String)e.getSource();
                    try {
                    
                    String path = file;
                    System.out.println(path);                                
                    imgp=path;
                /*
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(current.getWidth(), current.getHeight()/ 5, 0xe4e6e8), true);                
                    URLImage imageDmnd = URLImage.createToStorage(placeholder, imgp, imgp);                
                    ImageViewer imgViewer = new ImageViewer(imageDmnd);
                 */
                
                
                
                
                
                           try {
                //EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(current.getWidth(), current.getHeight()/ 5, 0xe4e6e8), true);
                 placeholder = EncodedImage.create("/giphy.gif").scaledEncoded(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayWidth()/2);
            } catch (IOException ex) {
               
            }
            
            //String url = "http://localhost/donationwebpidev/web/uploads/imagesAide/"+photo;
        
            URLImage imageDmnd = URLImage.createToStorage(placeholder, imgp,
            imgp, URLImage.RESIZE_SCALE);
        
        imageDmnd.scaledWidth(800);
        imageDmnd.scaledHeight(400);
        
        ImageViewer imgv = new ImageViewer(imageDmnd);
        imgv.setSize(new Dimension(700,400));
        
        
        
        imgCnt.setSize(new Dimension(700,400));
        
                
                
                
                
                    imgCnt.removeAll();
                    //imgCnt.add(imgViewer); 
                    imgCnt.add(imgv);
                    imgCnt.revalidate();
                               
                    } catch(Exception err) {
                    System.out.println("catch");
                    System.out.println(err);
                        } 
            }
        }, Display.GALLERY_IMAGE);

 }
 });
        
        
        
        
        
    }
    
    
}
