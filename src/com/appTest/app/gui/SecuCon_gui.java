/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.User;
import static com.appTest.app.gui.FLogIns_gui.userCon;
import com.appTest.app.services.Ges_User;
import com.codename1.components.SpanLabel;
import com.codename1.fingerprint.Fingerprint;
import com.codename1.ui.Button;
import com.codename1.ui.CN;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.table.TableLayout;
import java.util.ArrayList;

/**
 *
 * @author Amine Gongi
 */
public class SecuCon_gui extends Form {

    Button bt1 = new Button("1");
    Button bt2 = new Button("2");
    Button bt3 = new Button("3");
    Button bt4 = new Button("4");
    Button bt5 = new Button("5");
    Button bt6 = new Button("6");
    Button bt7 = new Button("7");
    Button bt8 = new Button("8");
    Button bt9 = new Button("9");
    Button bt0 = new Button("0");
    Button bt = new Button("Oubilée");
    Button btt = new Button(FontImage.MATERIAL_ARROW_UPWARD);

    TextField tCode = new TextField();
    Label sl = new Label("Code ou Empreinte");


    int ic = 0;

    public SecuCon_gui(String code, int user) {
        setLayout(new BorderLayout());
        bt1.setUIID("ButtonCode");
        bt2.setUIID("ButtonCode");
        bt3.setUIID("ButtonCode");
        bt4.setUIID("ButtonCode");
        bt5.setUIID("ButtonCode");
        bt6.setUIID("ButtonCode");
        bt7.setUIID("ButtonCode");
        bt8.setUIID("ButtonCode");
        bt9.setUIID("ButtonCode");
        bt0.setUIID("ButtonCode");
        bt.setUIID("ButtonCode");
        btt.setUIID("ButtonCode");
        tCode.setUIID("textfieldAmine");
        sl.setUIID("LabelCenterBlancTranspAmine");
        setUIID("bgSecCodeAmine");
        getToolbar().setUIID("LabelCenterBlancTranspAmine");
        
        
        tCode.setConstraint(TextField.PASSWORD);
        tCode.setEditable(false);

        if (Fingerprint.isAvailable()) {
            Fingerprint.scanFingerprint(value -> {
                ArrayList<User> u = Ges_User.getInstance().getUserbyId(user);
                userCon = u.get(0);
                new Home_gui().show();
                revalidate();
            }, (sender, err, errorCode, errorMessage) -> {
                Dialog.show("Erreur", "Merci d'utiliser votre Code", new Command("ok"));
            });
        } else {
            sl.setText("Votre Code");
        }

        Container t = new Container(BoxLayout.y());
        t.addAll(sl, tCode);

        Container tt = new Container(new GridLayout(4, 3));
        tt.addAll(bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt, bt0, btt);

        bt1.addActionListener((ActionListener) (ActionEvent evt) -> {
            zidChiffre("1", code, user);
        });
        bt2.addActionListener((ActionListener) (ActionEvent evt) -> {
            zidChiffre("2", code, user);
        });
        bt3.addActionListener((ActionListener) (ActionEvent evt) -> {
            zidChiffre("3", code, user);
        });
        bt4.addActionListener((ActionListener) (ActionEvent evt) -> {
            zidChiffre("4", code, user);
        });
        bt5.addActionListener((ActionListener) (ActionEvent evt) -> {
            zidChiffre("5", code, user);
        });
        bt6.addActionListener((ActionListener) (ActionEvent evt) -> {
            zidChiffre("6", code, user);
        });
        bt7.addActionListener((ActionListener) (ActionEvent evt) -> {
            zidChiffre("7", code, user);
        });
        bt8.addActionListener((ActionListener) (ActionEvent evt) -> {
            zidChiffre("8", code, user);
        });
        bt9.addActionListener((ActionListener) (ActionEvent evt) -> {
            zidChiffre("9", code, user);
        });
        bt0.addActionListener((ActionListener) (ActionEvent evt) -> {
            zidChiffre("0", code, user);
        });
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                FLogIns_gui.userCon = null;
                Ges_User.getInstance().deleteDb();
                Dialog.show("Sécurité", "Vous allez être rediriger vers l'interface de login", new Command("OK"));
                new Login_gui().show();
            }
        });

        add(BorderLayout.NORTH, t);

        add(BorderLayout.SOUTH, tt);

    }

    private void zidChiffre(String c, String code, int user) {
        tCode.setText(tCode.getText() + c);
        ic++;
        refreshTheme();
        if (tCode.getText().length() == 4) {
            System.out.println("check pass " + tCode.getText());
            if (tCode.getText().equals(code)) {
                ArrayList<User> u = Ges_User.getInstance().getUserbyId(user);
                userCon = u.get(0);
                new Home_gui().show();
            }
            ic = 0;
            tCode.clear();
            sl.setText("Code erroné");
            Display.getInstance().vibrate(500);
        }
    }

}
