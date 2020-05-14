/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.utils.AideImgUpload;
import com.appTest.app.utils.OpenGalleryAide;

import com.codename1.components.FloatingActionButton;

import com.codename1.components.ShareButton;

import com.codename1.io.Storage;
import com.codename1.share.FacebookShare;
import com.codename1.social.FacebookConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;


/**
 *
 * @author Hedi
 */
public class HomeAide_gui extends SideMenuNov {
    
    Form current;
    
    Button gallery = new Button("gal");
    Container imgCnt = new Container();
   
    public HomeAide_gui() {
        addSideMenu();
        getToolbar().setUIID("aide_Toolbar");
        current = this;
        current.setTitle("Plateforme Aide");
        current.setLayout(BoxLayout.y());


     
        
        
       current.add(gallery);
       current.add(imgCnt);
       gallery.addActionListener(new ActionListener<ActionEvent>() {
 @Override
 public void actionPerformed(ActionEvent evt) {
 // TODO Auto-generated method stub
 Display.getInstance().openGallery((e) -> {
            if(e != null && e.getSource() != null) {
                String file = (String)e.getSource();
                try {
                    System.out.println("try");
                String path = file;
                System.out.println(path);
                EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(current.getWidth(), current.getHeight()/ 5, 0xe4e6e8), true);
                URLImage imageDmnd = URLImage.createToStorage(placeholder, "photo",
        path);
                imgCnt.add(imageDmnd);
                imgCnt.revalidate();
                    
                } catch(Exception err) {
                    System.out.println("catch");
                    System.out.println(err);
                } 
            }
        }, Display.GALLERY_IMAGE);

 }
 });
       
       
       
       
       
       
       
       
       
       
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
       
       
       
       
       
    }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        /*
        fbLogin.addActionListener((evt) -> {
            //use your own facebook app identifiers here   
                //These are used for the Oauth2 web login process on the Simulator.
                String clientId = "825900971266791";
                String redirectURI = "http://localhost/";
                String clientSecret = "ccc5d1da307852617721ce0cc988efff";
                Login fb = FacebookConnect.getInstance();
                
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                //Sets a LoginCallback listener
                fb.setCallback(new LoginCallback() {
            @Override
            public void loginFailed(String errorMessage) {
                System.out.println("Failed login");
                Storage.getInstance().writeObject("token", "");
                //showIfNotLoggedIn(form);
            }

            @Override
            public void loginSuccessful() {
                System.out.println("successful login");
                String token = fb.getAccessToken().getToken();
                Storage.getInstance().writeObject("token", token);
                //showIfLoggedIn(form);
            }
            
        });
                






                //trigger the login if not already logged in
                if(!fb.isUserLoggedIn()){
                    fb.doLogin();
                }else{
                    //get the token and now you can query the facebook API
                    String token = fb.getAccessToken().getToken();
            Storage.getInstance().writeObject("token", token);
            //showIfLoggedIn(form);
            System.out.println("allready logged");
            FacebookShare fbs = new FacebookShare();
            
            fbs.share("donation");
                }
        
        });*/
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    

