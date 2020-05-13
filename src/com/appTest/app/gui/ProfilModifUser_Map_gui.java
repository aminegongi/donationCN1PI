/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.RestoMapCoord;
import com.appTest.app.services.ServiceMapRestaurant;
import com.appTest.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.googlemaps.MapContainer;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author safratix
 */
public class ProfilModifUser_Map_gui extends SideMenuNov {
    Form current;
    Boolean afficher = true;
     Location location = LocationManager.getLocationManager().getCurrentLocationSync();
     ArrayList<RestoMapCoord> listMap;
     EncodedImage enc;
    ImageViewer imgv;
    Image img;
    double longitude;
    double latitude;
    Boolean valide = false;
    
    public ProfilModifUser_Map_gui(){
        current = this;
//        setLayout(new BorderLayout());

        getToolbar().setTitleCentered(true);
        setTitle("Ajoutez votre emplacement");  
        getToolbar().setUIID("ToolbarAmine");
              
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new ProfilModifUser_gui(null, null).showBack()); // n'oublies pas de changer la page précédente si amine
        
        
//    MapContainer map = new MapContainer("AIzaSyCnTApZnOxFy05dbgRJPENjE6PPzSZQHVk");
        
        setLayout(new BorderLayout());


        final MapContainer cnt = new MapContainer("AIzaSyCnTApZnOxFy05dbgRJPENjE6PPzSZQHVk");
//       final MapContainer cnt = new MapContainer();
//        cnt.setCameraPosition(new Coord(36.798663,10.186145));
        cnt.zoom(new Coord(location.getLatitude(),location.getLongitude()), 20);
        
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        
        Style r = new Style();
        s.setFgColor(0x79eeac);
        s.setBgTransparency(0);

        Button btnMoveCamera = new Button("Centrer");
        btnMoveCamera.setUIID("RestoDon-btn");
        btnMoveCamera.addActionListener(e->{
            location = LocationManager.getLocationManager().getCurrentLocationSync();
            cnt.setCameraPosition(new Coord(location.getLatitude(), location.getLongitude()));
        });
        
        
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 8);
        

        
        
//        Coord coordonnees = new Coord(location.getLatitude(), location.getLongitude());
        
        Button btnLocation = new Button("Ma localisation");
        btnLocation.setUIID("RestoDon-btn");
        btnLocation.addActionListener(e->{
            location = LocationManager.getLocationManager().getCurrentLocationSync();
            cnt.setCameraPosition(new Coord(location.getLatitude(), location.getLongitude()));
            
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            valide = true;
            System.out.println("latitude: " + latitude + "longitude: " +longitude);
            cnt.clearMapLayers();
            cnt.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    cnt.getCameraPosition(),
                    "you are here",
                    "Optional long description",
                     evt -> {
                             Dialog.show("Nice", "You clicked the marker", "Done", null);
                     }
            );

        });

        

        Button btnClearAll = new Button("Clear All");
        btnClearAll.setUIID("RestoDon-btn");
        btnClearAll.addActionListener(e->{
            cnt.clearMapLayers();
            valide = false;
        });
        
        

        cnt.addTapListener(e->{
            Coord c = cnt.getCoordAtPosition(e.getX(), e.getY());
            latitude = c.getLatitude();
            longitude = c.getLongitude();
            valide = true;
            System.out.println("latitude: " + latitude + "longitude: " +longitude);
           cnt.clearMapLayers();
           cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        cnt.getCoordAtPosition(e.getX(), e.getY()),
                        "votre choix",
                        "",
                        e3->{
                                Dialog.show("Nice", "You clicked ", "Done", null);
                        }
                );
            
        });
        
        Button btnValider = new Button("Valider");
        btnValider.setUIID("RestoDon-btn");
        btnValider.addActionListener(e->{
        
            if (valide == false){
            Command dg = Dialog.show("Oops", " Vous n'avez pas choisi un emplacement !  ", new Command("Quitter"), new Command("Rester"));
                if (dg.getCommandName().equalsIgnoreCase("Rester"))
                {
                // ici s'il reste
                }else{
                    new ProfilModifUser_gui(null, null).show();
                }
            }else{
                new ProfilModifUser_gui(Double.toString(latitude), Double.toString(longitude)).show();
            }
            
        });
        
        
        Container midTop= new Container() ;
        midTop.setLayout(BoxLayout.xCenter());
        midTop.addAll(btnClearAll, btnMoveCamera, btnValider) ;
        
//         Container midX= new Container() ;
//        midX.setLayout(BoxLayout.xCenter());
//        midX.addAll(btnLocation, btnValider) ;
        
        Container midY= new Container() ;
        midY.setLayout(BoxLayout.yCenter());
        midY.addAll(midTop, btnLocation) ;
        
        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        FlowLayout.encloseCenterBottom(midY)
                )
        );

        add(BorderLayout.CENTER, root);
        
        
        
        
        
      
        
    }         
}
