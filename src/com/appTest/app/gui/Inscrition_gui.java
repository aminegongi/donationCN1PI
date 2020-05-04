/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.User;
import com.appTest.app.services.Ges_User;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;

/**
 *
 * @author Amine Gongi
 */
public class Inscrition_gui extends SideMenuNov {

    Form current;

    Button btVal = new Button("Creer mon Compte");
    TextField tUn = new TextField();
    TextField tMail = new TextField();
    TextField tPass = new TextField();
    TextField tConfPass = new TextField();
    TextField tTel = new TextField();

    ComboBox cbRole = new ComboBox();

    ButtonGroup bg = new ButtonGroup();
    RadioButton Nnon = RadioButton.createToggle("Non", bg);
    RadioButton Noui = RadioButton.createToggle("Oui", bg);
    Container forNews = new Container(BoxLayout.x());

    SpanLabel lTitre = new SpanLabel("Creer Votre Compte");
    SpanLabel errAll = new SpanLabel("Champs incomplet");
    SpanLabel errUn = new SpanLabel("Username existant :( ");
    SpanLabel errMail = new SpanLabel("@Mail existante :( ");
    SpanLabel telEx = new SpanLabel("exemple : +21698999888");
    SpanLabel lNews = new SpanLabel("Recevoir notre Newsletter :");
    SpanLabel lpass = new SpanLabel("mot de passe different");

    Validator v;

    public static String tomail = null;

    public Inscrition_gui() {

        current = this;
        getToolbar().setUIID("VioletBgBlanc");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new FLogIns_gui().showBack();
            }
        });
        setUIID("bgInscriptionAmine");
        setLayout(BoxLayout.y());
        setTitle("Inscription");

        tUn.setHint("votre Username :");
        tMail.setHint("votre Adresse Mail :");
        tPass.setHint("votre Mot de Passe :");
        tConfPass.setHint("Confirmer votre Mot de Passe :");
        tTel.setHint("Votre numéro de téléphone :");

        tUn.setUIID("textfieldBgTranspRondAmine");
        tMail.setUIID("textfieldBgTranspRondAmine");
        tPass.setUIID("textfieldBgTranspRondAmine");
        tConfPass.setUIID("textfieldBgTranspRondAmine");
        tTel.setUIID("textfieldBgTranspRondAmine");
        btVal.setUIID("ButtonInscriptionAmine");
        
        tPass.setConstraint(TextField.PASSWORD);
        tConfPass.setConstraint(TextField.PASSWORD);
        tTel.setConstraint(TextField.NUMERIC);
        
        v = new Validator();
        v.addConstraint(tUn, new LengthConstraint(2, "champ nom vide "));
        v.addConstraint(tMail, new LengthConstraint(2, "champ nom vide "), new RegexConstraint("^(.+)@(.+)$", "format Incorrect"));
        v.addConstraint(tTel, new LengthConstraint(2, "champ nom vide "), new RegexConstraint("[0-9]+", "champ Numéro ne doit pas contenir des char specieaux"));
        v.addConstraint(tPass, new LengthConstraint(2, "champ nom vide "));
        v.addConstraint(tConfPass, new LengthConstraint(2, "champ nom vide "));
        v.addConstraint(tUn, new LengthConstraint(2, "champ nom vide "));
        v.addSubmitButtons(btVal);

        forNews.addAll(Nnon, Noui);

        errMail.setHidden(true);
        errUn.setHidden(true);
        errAll.setHidden(true);
        lpass.setHidden(true);
        cbRole.addItem("Utilisateur Simple");
        cbRole.addItem("Organisation");
        cbRole.addItem("Entreprise");
        cbRole.addItem("Restaurant");

        btVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String role = "";
                if (cbRole.getSelectedIndex() == 0) {
                    role = "ROLE_US";
                } else if (cbRole.getSelectedIndex() == 1) {
                    role = "ROLE_ORG";
                } else if (cbRole.getSelectedIndex() == 2) {
                    role = "ROLE_ENT";
                } else if (cbRole.getSelectedIndex() == 3) {
                    role = "ROLE_RES";
                }
                if (tConfPass.getText().equals(tPass.getText())) {
                    Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                    User u = new User(tUn.getText(), tMail.getText(), tPass.getText(), role, tTel.getText(), bg.getSelectedIndex());
                    int i = Ges_User.getInstance().inscription(u);
                    if (i == -11) {
                        errMail.setHidden(false);
                        errUn.setHidden(false);
                        errAll.setHidden(true);
                    } else if (i == -3) {
                        errMail.setHidden(false);
                        errUn.setHidden(false);
                        errAll.setHidden(true);
                    } else if (i == -1) {
                        errMail.setHidden(false);
                        errUn.setHidden(true);
                        errAll.setHidden(true);
                    } else if (i == -2) {
                        errMail.setHidden(true);
                        errUn.setHidden(false);
                        errAll.setHidden(true);
                    } else {
                        Dialog.show("Bravo !", "Inscription reussi il ne vous rest qu'a le valider", new Command("OK"));
                        tomail = tMail.getText();
                        ip.dispose();
                        new activation_gui().show();

                    }
                    System.out.println(i);
                    lpass.setHidden(true);
                    ip.dispose();
                } else {
                    lpass.setHidden(false);
                }
                current.refreshTheme();
            }

        });

        current.addAll(errAll, tUn, errUn, tMail, errMail, tPass, tConfPass, lpass, tTel, telEx, cbRole, lNews, forNews, btVal);

    }

}
