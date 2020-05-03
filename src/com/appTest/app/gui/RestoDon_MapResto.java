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
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.maps.Projection;
import com.codename1.maps.Tile;
import com.codename1.maps.layers.Layer;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.maps.providers.OpenStreetMapProvider;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.webserver.WebServer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author safratix
 */
public class RestoDon_MapResto extends SideMenuNov {
     Form current;
    Boolean afficher = true;
     Location location = LocationManager.getLocationManager().getCurrentLocationSync();
     ArrayList<RestoMapCoord> listMap;
     EncodedImage enc;
    ImageViewer imgv;
    Image img;
    
    public RestoDon_MapResto(){
        current = this;
//        setLayout(new BorderLayout());
        getToolbar().setTitleCentered(true);
        setTitle("Nos restaurants");  
        getToolbar().setUIID("RestoDon-Toolbar");
              
        addSideMenu();
//    MapContainer map = new MapContainer("AIzaSyCnTApZnOxFy05dbgRJPENjE6PPzSZQHVk");
        
        setLayout(new BorderLayout());
        listMap = ServiceMapRestaurant.getInstance().getRestoMap();
        final MapContainer cnt = new MapContainer("AIzaSyCnTApZnOxFy05dbgRJPENjE6PPzSZQHVk");
//       final MapContainer cnt = new MapContainer();
//        cnt.setCameraPosition(new Coord(36.798663,10.186145));
        cnt.zoom(new Coord(36.798663,10.186145), 20);
        
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        
        Style r = new Style();
        s.setFgColor(0x79eeac);
        s.setBgTransparency(0);
        
        FontImage restoImg = FontImage.createMaterial(FontImage.MATERIAL_STOREFRONT, s, 8);
        
        for(RestoMapCoord resto : listMap){
            if( (resto.getActiveLongitude() == true) && (resto.getActiveLatitude() == true ) ){
                
            try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            System.out.println("enc ERR");
        }

        String urlImg = Statics.BASE_URL_Image_User + resto.getIcon();

        img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);

      
                
            String username = resto.getUsername();
            String fb = resto.getFb();
            String web = resto.getWeb();
            String icon = resto.getIcon();
            float longitude = resto.getLongitude();
            float latitude = resto.getLatitude();
                System.out.println(resto.toString());
            cnt.addMarker(
                    EncodedImage.createFromImage(restoImg, false),
                    new Coord(latitude, longitude),
                    resto.getUsername(),
                    "Optional long description",
                       evt -> {
                             Button WebButton = new Button("Site Web");
                             WebButton.addActionListener(e -> {
                             new RestoDon_SiteWebResto(current, web, username).show();
                             });
                            Container wrapper = BoxLayout.encloseY(WebButton);
                            InteractionDialog dlg = new InteractionDialog(username);
                            dlg.getContentPane().add(wrapper);
                             WebButton.addFocusListener(new FocusListener() {


                                @Override
                                public void focusGained(Component cmp) {
                                    
                                }

                                @Override
                                public void focusLost(Component cmp) {
                                    dlg.disposeToTheRight();
                                    dlg.getContentPane().removeComponent(wrapper);
                                    afficher = false;
                                }
                        });
                            
                            if (afficher == true){
                              dlg.showPopupDialog(new Rectangle(200, 300, 10, 10));
                              dlg.addFocusListener(new FocusListener() {


                                @Override
                                public void focusGained(Component cmp) {
                                    
                                }

                                @Override
                                public void focusLost(Component cmp) {
                                    dlg.disposeToTheRight();
                                    dlg.getContentPane().removeComponent(wrapper);
                                    afficher = false;
                                }
                        });
                            } else {
                              afficher = true;
                          }
                                       }
            );
            }
        }
        
        
        
        Button btnMoveCamera = new Button("Centrer");
        btnMoveCamera.setUIID("RestoDon-btn");
        btnMoveCamera.addActionListener(e->{
            location = LocationManager.getLocationManager().getCurrentLocationSync();
            cnt.setCameraPosition(new Coord(location.getLatitude(), location.getLongitude()));
        });
        
        
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s, 8);
        

        
        
//        Coord coordonnees = new Coord(location.getLatitude(), location.getLongitude());
        
        Button btnLocation = new Button("Mon emplacement");
        btnLocation.setUIID("RestoDon-btn");
        btnLocation.addActionListener(e->{
            location = LocationManager.getLocationManager().getCurrentLocationSync();
            cnt.setCameraPosition(new Coord(location.getLatitude(), location.getLongitude()));
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
        });
        
        

        cnt.addTapListener(e->{
            
           
            TextField enterName = new TextField();
            Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
            InteractionDialog dlg = new InteractionDialog("Add Marker");
            dlg.getContentPane().add(wrapper);
             enterName.addFocusListener(new FocusListener() {
            

                @Override
                public void focusGained(Component cmp) {
                    
                }

                @Override
                public void focusLost(Component cmp) {
                    dlg.disposeToTheRight();
                    dlg.getContentPane().removeComponent(wrapper);
                    afficher = false;
                }
        });
            enterName.setDoneListener(e2->{
                String txt = enterName.getText();
                cnt.addMarker(
                        EncodedImage.createFromImage(markerImg, false),
                        cnt.getCoordAtPosition(e.getX(), e.getY()),
                        enterName.getText(),
                        "",
                        e3->{
                                Dialog.show("Nice", "You clicked "+txt, "Done", null);
                        }
                );
                dlg.dispose();
            });
          if (afficher == true){
            dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
            enterName.startEditingAsync();
          } else {
            afficher = true;
        }
        });
        
//        FontImage animationImg = FontImage.createMaterial(FontImage.MATERIAL_CANCEL, s, 8);
//        Button animationBtn = new Button();
//        animationBtn.setUIID("RestoDon-btn");
//        animationBtn.setIcon(animationImg);
//        TextField search = new TextField();
//        search.setUIID("RestoDon-TextField");
//        search.setHint("Trouver un resto");
//        FontImage searchImg = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s, 8);
//        Button searchBtn = new Button();
//        animationBtn.setUIID("RestoDon-btn");
//        searchBtn.setIcon(animationImg);
//        
//        
//        
//        Container topcnt = new Container();
//        topcnt.setLayout(BoxLayout.x());
//        topcnt.addAll(animationBtn, search, searchBtn) ;
//        
        
        
        Container midTop= new Container() ;
        midTop.setLayout(BoxLayout.xCenter());
        midTop.addAll(btnClearAll, btnMoveCamera) ;
        
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
        
        
        
        
        
        
//        MapContainer mapC = new MapContainer();
//        OpenStreetMapProvider map = new OpenStreetMapProvider();
//        
//        map.attribution();
//        Projection proj = map.projection();
//        
//        
//        try {
//            Image img = Image.createImage("/location.png");
//            Location location = LocationManager.getLocationManager().getCurrentLocationSync();
//            Coord coordonnees = new Coord(location.getLatitude(), location.getLongitude());
//             
//            PointLayer pl = new PointLayer(coordonnees, "Hello, Vous êtes ici", img);
//            proj.fromWGS84(pl);
//            pl.setDisplayName(true);
//            PointsLayer pointsL= new PointsLayer();
//            pointsL.addPoint(pl);
//            pointsL.setPointIcon(img);
//            Tile t = new Tile(map.tileSize(),map.bboxFor(coordonnees, 17),img);
//            t.pointPosition(pl);
//            map.maxZoomFor(t);
//            
//            } catch (IOException ex) {
//            Dialog.show("erreur", ex.getMessage(), "Ok", null);
//        }
//        
//            add(map);
//            
            
            
            
            
//         try {
//            initWebsite();
//        } catch (IOException ex) {
//            Log.e(ex);
//        }
//                 
//        WebServer server = new WebServer(
//        new File("httpdocs").getAbsolutePath(), // Path to doc root in FileSystemStorage
//        8888 // Port
//        );
//        if (server.isSupported()) {
//            // Check that the platform supports the server.
//            server.start();
//          }
////        browser.setURL("http://localhost:8888/index.html");
//    // NOTE: On iOS, you need to add localhost exceptions
//    // to the transport settings in order access localhost URLs
//        BrowserComponent browser = new BrowserComponent();
//        Display.getInstance().setProperty("BrowserComponent.useWKWebView", "true");
//
//
//        browser.setURL("http://localhost:8888/index.html");
//        browser.setPinchToZoomEnabled(true);
//        addComponent(BorderLayout.CENTER, browser);
//
//    }
//    
//    private WebServer server;
//    
//    private void initWebsite() throws IOException {
//        FileSystemStorage fs = FileSystemStorage.getInstance();
//        File docRoot = new File("httpdocs");
//        delTree(docRoot);
//        docRoot.mkdir();
//        String helloContent = "<!doctype html><html>\n" +
//"<head>\n" +
//"    <meta charset=\"utf-8\" />\n" +
//"    <title>Display a map</title>\n" +
//"    <meta name=\"viewport\" content=\"initial-scale=1,maximum-scale=1,user-scalable=no\" />\n" +
//"    <script src=\"https://api.mapbox.com/mapbox-gl-js/v1.10.0/mapbox-gl.js\"></script>\n" +
//"    <link href=\"https://api.mapbox.com/mapbox-gl-js/v1.10.0/mapbox-gl.css\" rel=\"stylesheet\" />\n" +
//"    <style>\n" +
//"        body { margin: 0; padding: 0; }\n" +
//"        #map { position: absolute; top: 0; bottom: 0; width: 100%; }\n" +
//"    </style>\n" +
//"</head>\n" +
//"<body>\n" +
//"<div id=\"map\"></div>\n" +
//"<script>\n" +
//"    mapboxgl.accessToken = 'pk.eyJ1Ijoic2FmcmF0aXgiLCJhIjoiY2s4dGppaTl1MDB0bTNpcnp3MWRuZTd3MSJ9.4N2a8yFjGEvHPuAjmOV03w';\n" +
//"    var map = new mapboxgl.Map({\n" +
//"        container: 'map', // container id\n" +
//"        style: 'mapbox://styles/mapbox/streets-v11', // stylesheet location\n" +
//"        center: [-74.5, 40], // starting position [lng, lat]\n" +
//"        zoom: 9 // starting zoom\n" +
//"    });\n" +
//"</script>\n" +
//"\n" +
//"</body>\n" +
//"</html>";
//        File indexHtml = new File(docRoot, "index.html");
//        writeStringToFile(indexHtml, helloContent);
//        
//    }
//    
//    private void writeStringToFile(File file, String content) throws IOException {
//        FileSystemStorage fs = FileSystemStorage.getInstance();
//        try (OutputStream os = fs.openOutputStream(file.getAbsolutePath())) {
//            Util.copy(new ByteArrayInputStream(content.getBytes("UTF-8")), os);
//        }
//        
//    }
//    
//    private void delTree(File file) {
//        if (file.isDirectory()) {
//            for (File f : file.listFiles()) {
//                delTree(f);
//            }
//        }
//        file.delete();
        
    }         
}
