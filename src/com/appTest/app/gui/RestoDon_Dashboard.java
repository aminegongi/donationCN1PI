/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.services.ServiceDonRestaurant;
import com.appTest.app.services.ServiceRepasServi;
import com.codename1.components.ButtonList;
import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author safratix
 */
public class RestoDon_Dashboard extends SideMenuNov {
    
    public RestoDon_Dashboard() {
        
        setLayout(new BorderLayout());
        getToolbar().setUIID("RestoDon-Toolbar");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               
                   
                        new RestoDon_HomeResto().showBack();
                    
            }
        });
        
        Tabs t = new Tabs(Tabs.TOP);
//        t.setUIID("RestoDon-TabInactif");
        t.setScrollableY(true);
//        int selected = t.getSelectedIndex();
//        s.setBgColor(0xFFFFFF, true);
//        s.setFgColor(0x79eeac, true);
//        t.setUnselectedStyle(UIManager.getInstance().getComponentStyle("RestoDon-TabInactif"));
//        
//        Style u = new Style();
//        u.setFgColor(0xFFFFFF, true);
//        u.setBgColor(0x79eeac, true);
//        t.setSelectedStyle(UIManager.getInstance().getComponentStyle("RestoDon-Tab"));
        
//        InfiniteContainer ic = new InfiniteContainer() {
//        @Override
//        public Component[] fetchComponents(int index, int amount) {
//            ArrayList<Map<String, Object>> data = ServiceDonRestaurant.getInstance().getListDon();
//            MultiButton[] cmps = new MultiButton[data.size()];
//            for(int iter = 0 ; iter < cmps.length ; iter++) {
//                Map<String, Object> currentListing = data.get(iter);
//                if(currentListing == null) {
//                    return null;
//                }
//                String username = currentListing.get("username").toString();
//                
//                float montant = Float.parseFloat(currentListing.get("montant").toString());
//                String montantTxt = String.valueOf(montant);
//                System.out.println(montant);
////                String date = currentListing.get("date").toString();
//                cmps[iter] = new MultiButton(username);
//                cmps[iter].setTextLine2(montantTxt);
////                cmps[iter].setTextLine3(date);
//            }
//            return cmps;
//        }
//    };
//        add(BorderLayout.CENTER, ic);
        Container cDon = new Container(BoxLayout.y());
        cDon.setScrollableY(true);
        DefaultListModel model = new DefaultListModel();
        ArrayList<Map<String, Object>> data = new ArrayList<>();
       for(Component cmp : fetchComponents()){
            cDon.add(cmp);
        }
       
       t.addTab("Donation", cDon);
       
        Container cRepas = new Container(BoxLayout.y());
        cRepas.setScrollableY(true);
        DefaultListModel model2 = new DefaultListModel();
        ArrayList<Map<String, Object>> data2 = new ArrayList<>();
       for(Component cmp : fetchComponentsRepas()){
            cRepas.add(cmp);
        }
       
       
       t.addTab("Repas Servis", cRepas);
       
       addComponent(BorderLayout.CENTER, t);
       
//        Container ic = new Container().addAll( fetchComponents() );
        
        
        
}
    public Component[] fetchComponents() {
            ArrayList<Map<String, Object>> data = ServiceDonRestaurant.getInstance().getListDon();
            MultiButton[] cmps = new MultiButton[data.size()];
            int dataCase = data.size()-1;
            for(int iter = 0 ; iter < cmps.length ; iter++) {
                Map<String, Object> currentListing = data.get(dataCase);
                if(currentListing == null) {
                    return null;
                }
                String username = currentListing.get("username").toString();
                
                float montant = Float.parseFloat(currentListing.get("montant").toString());
                String montantTxt = String.valueOf(montant) + " Dinars";
//                System.out.println(montant);
                String date = currentListing.get("date").toString();
                cmps[iter] = new MultiButton(username);
                cmps[iter].setUIIDLine1("RestoDon-Label");
                cmps[iter].setTextLine2(montantTxt);
                cmps[iter].setUIIDLine2("RestoDon-LabelHistorique");
                cmps[iter].setHorizontalLayout(true);
                cmps[iter].setTextLine3(date);
                dataCase--;
            }
            return cmps;
        }
    
    
    public Component[] fetchComponentsRepas() {
            ArrayList<Map<String, Object>> data = ServiceRepasServi.getInstance().getListRepas();
            MultiButton[] cmps = new MultiButton[data.size()];
            int dataCase = data.size()-1;
            for(int iter = 0 ; iter < cmps.length ; iter++) {
                Map<String, Object> currentListing = data.get(dataCase);
                if(currentListing == null) {
                    return null;
                }
//                String username = currentListing.get("username").toString();
                
                float tarif = Float.parseFloat(currentListing.get("tarif").toString());
                String tarifTxt = String.valueOf(tarif) + " Dinars";
//                System.out.println(montant);
                String date = currentListing.get("date").toString();
                int numRepas = dataCase+1;
                cmps[iter] = new MultiButton("Repas "+numRepas);
                cmps[iter].setUIIDLine1("RestoDon-Label");
                cmps[iter].setTextLine2(tarifTxt);
                cmps[iter].setUIIDLine2("RestoDon-LabelHistorique");
                cmps[iter].setHorizontalLayout(true);
                cmps[iter].setTextLine3(date);
                dataCase--;
            }
            return cmps;
        }
}
