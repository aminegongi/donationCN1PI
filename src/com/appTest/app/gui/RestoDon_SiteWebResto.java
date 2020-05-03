/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.DonRestaurant;
import com.appTest.app.services.ServiceDonRestaurant;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.webserver.WebServer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author safratix
 */
public class RestoDon_SiteWebResto extends SideMenuNov {
    public RestoDon_SiteWebResto(Form previous, String link, String username){
        
        getToolbar().setUIID("RestoDon-Toolbar");
        setTitle(username);
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new RestoDon_MapResto().showBack()); // Revenir vers l'interface précédente
                
        
        
       
       
        BrowserComponent browser = new BrowserComponent();
        Display.getInstance().setProperty("BrowserComponent.useWKWebView", "true");


        browser.setURL(link);
        browser.setPinchToZoomEnabled(true);
        addComponent(browser);

    }
    
    
    
}
