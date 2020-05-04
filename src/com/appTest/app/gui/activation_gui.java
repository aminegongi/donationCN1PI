/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.services.Ges_User;
import com.codename1.sms.intercept.SMSInterceptor;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Amine Gongi
 */
public class activation_gui extends SideMenuNov {

    Form current;
    TextField tToken = new TextField();
    TextField tMail = new TextField();
    Button btVal = new Button("Activer");

    public activation_gui() {
        getToolbar().setUIID("VioletBgBlanc");
        if (Inscrition_gui.tomail != null) {
            tMail.setText(Inscrition_gui.tomail);
            tMail.setEditable(false);
            tMail.setHidden(true);
        }
        current = this;

        tMail.setUIID("textfieldBgTranspRondAmine");
        tToken.setUIID("textfieldBgTranspRondAmine");
        btVal.setUIID("ButtonInscriptionAmine");

        setLayout(BoxLayout.y());
        setTitle("Activation Compte");
        setUIID("bgThankYouAmine");

        tMail.setHint("votre adresse mail :");

        tToken.setHint("Code de confirmation : ");

        btVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int i = Ges_User.getInstance().activerCompte(tMail.getText(), tToken.getText());
                if (i == -1) {
                    Dialog.show("Erreur", "Code Incorrect", new Command("OK"));
                } else {
                    Dialog.show("Bravo", "Compte Activer", new Command("OK"));
                    new FLogIns_gui().show();
                }
            }
        });

        if (SMSInterceptor.isSupported()) {
            String tt = Ges_User.getInstance().getTokenMail(tMail.getText());
            System.out.println("*****Token: " + tt);
            addShowListener(evt -> {
                SMSInterceptor.grabNextSMS((value) -> {
                    if (value.contains(tt)) {
                        int i = Ges_User.getInstance().activerCompte(tMail.getText(), tToken.getText());
                        if (i == -1) {
                            Dialog.show("Erreur", "Code Incorrect", new Command("OK"));
                        } else {
                            Dialog.show("Bravo", "Compte Activer", new Command("OK"));
                            new FLogIns_gui().show();
                        }
                    } else {
                        Dialog.show("Erreur", "Code Incorrect", new Command("OK"));
                    }
                });

            });
        }
        current.addAll(tMail, tToken, btVal);

    }

}
