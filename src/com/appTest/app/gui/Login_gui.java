/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.User;
import static com.appTest.app.gui.FLogIns_gui.userCon;
import com.appTest.app.services.Ges_User;
import com.codename1.components.ImageViewer;
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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
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

    Label lcodeO = new Label("Entrer Un Code ");
    TextField tfcodeO1 = new TextField();
    TextField tfcodeO2 = new TextField();

    public Login_gui() {
        
        current = this;
        getToolbar().setUIID("LabelCenterBlancTranspAmine");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new FLogIns_gui().showBack();
            }
        });
        setUIID("bgBleuDegAmine");
        //setUIID("SignIn");
        setLayout(new BorderLayout());
        setTitle("Connexion");

        ImageViewer logodc = null;
        try {
            logodc = new ImageViewer(Image.createImage("/logodonationC.png"));

        } catch (IOException ex) {
        }

        tMail.setHint("votre Adresse Mail :");
        tPass.setHint("votre Mot de Passe :");

        tMail.setUIID("textfieldBgBlancRondAmine");
        tPass.setUIID("textfieldBgBlancRondAmine");
        lMdpOub.setUIID("LabelCenterBlancTranspAmine");
        rem.setUIID("LabelCenterBlancTranspAmine");
        tMail.setConstraint(TextField.EMAILADDR);
        tPass.setConstraint(TextField.PASSWORD);

        v = new Validator();
        v.addConstraint(tMail, new LengthConstraint(1, "champ mail vide "), new RegexConstraint("^(.+)@(.+)$", "format Incorrect"));
        v.addConstraint(tPass, new LengthConstraint(1, "champ password vide "));
        v.addSubmitButtons(btLogin);

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
                    ip.dispose();
                    if (rem.isSelected()) {
                        System.out.println("********************REM connection fil sqlite");
                        boolean b = Dialog.show("Sécurité", "Voulez ajouter une authentification simplifiée", "Oui", "Non");
                        if (b) {//Oui
                            removeAll();
                            tfcodeO1.setHint("Entrer un Code");
                            tfcodeO2.setHint("Confirmer le Code");
                            tfcodeO1.setConstraint(TextField.PASSWORD);
                            tfcodeO2.setConstraint(TextField.PASSWORD);
                            tfcodeO1.setUIID("textfieldBgBlancRondAmine");
                            tfcodeO2.setUIID("textfieldBgBlancRondAmine");
                            lcodeO.setUIID("LabelCenterBlancTranspAmine");
                            Button btOk = new Button("Valider");
                            btOk.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    if (tfcodeO1.getText().length() != 4) {
                                        Dialog.show("Erreur", "Le code doit Contenir 4 chiffres", new Command("ok"));
                                    } else {
                                        if (tfcodeO1.getText().equals(tfcodeO2.getText())) {
                                            Ges_User.getInstance().insertRemWCode(Integer.toString(u.get(0).getId()), "26/26", tfcodeO1.getText());

                                            new Home_gui().show();
                                        } else {
                                            Dialog.show("Erreur", "Code non identique !", new Command("ok"));
                                        }
                                    }
                                }
                            });
                            Container yy = new Container(BoxLayout.yCenter());

                            yy.addAll(lcodeO, tfcodeO1, tfcodeO2, btOk);
                            current.add(BorderLayout.SOUTH, yy);

                        } else { //Non
                            Ges_User.getInstance().insertRem(Integer.toString(u.get(0).getId()), "26/26");
                            new Home_gui().show();
                        }
                    } else {
                        System.out.println("******************** NOREM");
                        new Home_gui().show();
                    }
                    //goTopage ili feha kolchay

                }
                System.out.println(i);

                current.refreshTheme();
            }

        });
        Container y = new Container(BoxLayout.y());
        Container Table2 = new Container(new GridLayout(2));
        Table2.add(lMdpOub);
        Table2.add(btLogin);
        y.addAll(tMail, tPass, rem, Table2);
        //current.add(BorderLayout.NORTH,logodc);
        current.add(BorderLayout.SOUTH, y);

    }
}
