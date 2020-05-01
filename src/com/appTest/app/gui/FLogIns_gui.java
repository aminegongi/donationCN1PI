/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.User;
import com.appTest.app.services.Ges_User;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import java.util.ArrayList;

/**
 *
 * @author Amine Gongi
 */
public class FLogIns_gui extends Form {

    Form current;
    public static User userCon = null;

    public FLogIns_gui() {
        current = this;
        
        setLayout(BoxLayout.y());

        Button btInscri = new Button("Inscription");
        Button btLogin = new Button("Login");

        
        int cor = Ges_User.getInstance().checkOneRem();
        if (cor == -1) {
            btLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    new Login_gui().show();
                }
            });
        } else {
            System.out.println("fama 7aja idu: " + cor);
            btLogin.setText("open Account");
            btLogin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    String code = Ges_User.getInstance().getCodeRem(Integer.toString(cor));
                    if (code == null || code.contains("errno")) {
                        ArrayList<User> u = Ges_User.getInstance().getUserbyId(cor);
                        userCon = u.get(0);
                        new Home_gui().show();
                    } else {
                        new SecuCon_gui(code, cor).show();
                    }

                }
            });
        }

        Button fb = new Button("Se connecter avec FaceBook");
        Button google = new Button("Se connecter avec Google");
        
        Container xx = new Container(GridLayout.autoFit());

        btInscri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //new Inscrition_gui().show();
                new Inscrition_gui().show();
            }
        });

        xx.addAll(btLogin, btInscri);
        addAll(xx, fb, google);
    }

}
