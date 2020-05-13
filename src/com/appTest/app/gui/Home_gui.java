/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.codename1.ui.Label;

/**
 *
 * @author Amine Gongi
 */
public class Home_gui extends SideMenuNov {

    public Home_gui() {
        addSideMenu();
        getToolbar().setUIID("ToolbarAmine");
        setTitle("Home");
        add(new Label("Houni nal9aw les intefaces el kol :) :) "));
    }
    
}
