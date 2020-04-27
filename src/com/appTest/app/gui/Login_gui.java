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
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.util.ArrayList;

/**
 *
 * @author Amine Gongi
 */
public class Login_gui extends SideMenuNov {

    Form current;

    Button btLogin = new Button("Connexion");

    TextField tMail = new TextField();
    TextField tPass = new TextField();

    SpanLabel lTitre = new SpanLabel("Creer Votre Compte");
    SpanLabel lMdpOub = new SpanLabel("vous avez oublié votre mot de passe ?");

    RadioButton rem = new RadioButton("se souvenir de moi");

    Validator v;

    public static String tomail = null;

    Label lcodeO = new Label("un Code Merci :");
    TextField tfcodeO1 = new TextField();
    TextField tfcodeO2 = new TextField();

    public Login_gui() {
        addSideMenu();
        current = this;
        setLayout(BoxLayout.y());
        setTitle("Connexion");

        tMail.setHint("votre Adresse Mail :");
        tPass.setHint("votre Mot de Passe :");

        tPass.setConstraint(TextField.PASSWORD);

        v = new Validator();
        v.addConstraint(tMail, new LengthConstraint(2, "champ mail vide "));
        v.addConstraint(tPass, new LengthConstraint(1, "champ password vide "));

        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                int i = Ges_User.getInstance().login(tMail.getText(), tPass.getText());
                if (i == -1) {
                    Dialog.show("Erreur !", "mot de passe ou @Mail incorrect", new Command("OK"));
                    ip.dispose();
                } else {

                    ArrayList<User> u = Ges_User.getInstance().getUserbyMail(tMail.getText());
                    FLogIns_gui.userCon = u.get(0);
                    if (rem.isSelected()) {
                        System.out.println("********************REM connection fil sqlite");
                        boolean b = Dialog.show("Sécurité", "Voulez ajouter une authentification simplifiée", "Oui", "Non");
                        if (b) {//Oui
                            
                            tfcodeO1.setHint("Entrer un Code");
                            tfcodeO2.setHint("Confirmer le Code");
                            tfcodeO1.setConstraint(TextField.PASSWORD);
                            tfcodeO2.setConstraint(TextField.PASSWORD);

                            Button btOk = new Button("Valider");
                            btOk.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    if (tfcodeO1.getText().equals(tfcodeO2.getText())) {
                                        Ges_User.getInstance().insertRemWCode(Integer.toString(u.get(0).getId()), "26/26", tfcodeO1.getText());
                                        new Home_gui().show();
                                    } else {
                                        Dialog.show("Erreur", "Code non identique !", new Command("ok"));
                                    }
                                }
                            });
                            addAll(lcodeO, tfcodeO1, tfcodeO2, btOk);

                        } else { //Non
                            Ges_User.getInstance().insertRem(Integer.toString(u.get(0).getId()), "26/26");
                        }
                    }
                    //goTopage ili feha kolchay
                    new Home_gui().show();
                    ip.dispose();
                }
                System.out.println(i);

                current.refreshTheme();
            }

        });

        current.addAll(tMail, tPass, rem, lMdpOub, btLogin);

    }
}
