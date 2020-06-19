/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

/**
 *
 * @author Amine Gongi
 */
public class Home_gui extends SideMenuNov {

    public Home_gui() {
        addSideMenu();
        setLayout(new BorderLayout());
        setUIID("bgWelcomeAmine");
        getToolbar().setUIID("LabelCenterBlancTranspAmine");
        //getToolbar().setUIID("ToolbarAmine");
        setTitle("Home");
        //add(new Label("Houni nal9aw les intefaces el kol :) :) "));

        Container y = new Container(BoxLayout.y());
        Button cagnotte = new Button("Vous Voulez faire un don ?");
        Button aide = new Button("Vous Proposez, Cherchez de l'aide ?");
        Button emploi = new Button("Vous cherchez un Emploi ?");
        Button restoDon = new Button("Vous cherchez un Restaurant ?");
        Button restoOrg = new Button("Une Organisation ici !");

        cagnotte.setUIID("ButtonHomeC");
        aide.setUIID("ButtonHomeA");
        emploi.setUIID("ButtonHomeE");
        restoDon.setUIID("ButtonHomeRD");
        restoOrg.setUIID("ButtonHomeRO");
        
        cagnotte.addActionListener((ActionListener) (ActionEvent evt) -> {
            new Cagnotte_Home().show();
        });
        aide.addActionListener((ActionListener) (ActionEvent evt) -> {
            new ListeDmndAide_gui().show();
        });
        emploi.addActionListener((ActionListener) (ActionEvent evt) -> {
            new Home_gui().show();
        });
        restoDon.addActionListener((ActionListener) (ActionEvent evt) -> {
            if(FLogIns_gui.userCon.getRoles().contains("RES")){
                    new RestoDon_HomeResto().show();
                }else{
                new RestoDon_MapResto().show();
                }
        });
        restoOrg.addActionListener((ActionListener) (ActionEvent evt) -> {
            new PubForm(new ListPublicationsForm()).show();
        });
        

        y.add(cagnotte);
        y.add(aide);
        y.add(emploi);
        y.add(restoDon);
        y.add(restoOrg);

        //add(BorderLayout.SOUTH, y);

    }

}
