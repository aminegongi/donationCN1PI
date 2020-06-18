/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.DemandeAide;
import com.appTest.app.entities.Emploi;
import com.appTest.app.entities.ParticipationAide;
import com.appTest.app.entities.ReactionAide;
import com.appTest.app.services.ServiceDemandeAide;
import com.appTest.app.services.ServiceEmploi;
import com.appTest.app.services.ServiceParticipationAide;
import com.appTest.app.services.ServiceReactionAide;
import com.appTest.app.utils.Statics;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageButton;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;

import java.util.ArrayList;

/**
 *
 * @author Hechem
 */
public class h_MesEmploi_emploi1 extends SideMenuNov {

    Form current;
    EncodedImage placeholder;

    public h_MesEmploi_emploi1() {
        addSideMenu();
        getToolbar().setUIID("aide_Toolbar");

        current = this;
        current.setTitle("Mes Emplois");
        current.setLayout(BoxLayout.y());

        //floating button
        FloatingActionButton aide_FAB = new FloatingActionButton(FontImage.MATERIAL_DASHBOARD, "hello", "aide-FAB", 5) {
        };

        FloatingActionButton aideAccueil_FAB = aide_FAB.createSubFAB(FontImage.MATERIAL_COLLECTIONS, "Les Emplois");
        aideAccueil_FAB.setUIID("aide-FAB");
        aideAccueil_FAB.setPreferredSize(new Dimension(200, 200));
        aideAccueil_FAB.addActionListener(e -> new h_listeEmploi_emploi().show());

        FloatingActionButton aideMesDmnd_FAB = aide_FAB.createSubFAB(FontImage.MATERIAL_SPEAKER_NOTES, "Mes Emplois");
        aideMesDmnd_FAB.setUIID("aide-FAB");
        aideMesDmnd_FAB.setPreferredSize(new Dimension(200, 200));
        aideMesDmnd_FAB.addActionListener(e -> new h_MesEmploi_emploi1().show());

        FloatingActionButton addDmnd_FAB = aide_FAB.createSubFAB(FontImage.MATERIAL_RECORD_VOICE_OVER, "déposer un emploi");
        addDmnd_FAB.setUIID("aide-FAB");
        addDmnd_FAB.setPreferredSize(new Dimension(200, 200));
        addDmnd_FAB.addActionListener(e -> new h_addEmploi_emploi().show());

        aide_FAB.bindFabToContainer(getContentPane());

        ArrayList<Emploi> emploi = ServiceEmploi.getInstance().getMesEmplois(FLogIns_gui.userCon.getId());
        
        for (Emploi emp : emploi) {
            Container y = new Container(BoxLayout.y());
            SpanLabel titre = new SpanLabel(emp.getTitre());
            titre.setUIID("dmndAideItem_Title");
            SpanLabel description = new SpanLabel(emp.getDescription());
            description.setUIID("dmndAideItem_desc");

            String photo = emp.getPhoto();
            try {
                //EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(current.getWidth(), current.getHeight()/ 5, 0xe4e6e8), true);
                placeholder = EncodedImage.create("/logodonationC.png").scaledEncoded(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayWidth() / 2);
            } catch (IOException ex) {

            }

            String url = Statics.BASE_URL_Image_Emploi + photo;

            URLImage imageDmnd = URLImage.createToStorage(placeholder, url,
                    url, URLImage.RESIZE_SCALE);

            imageDmnd.scaledWidth(800);
            imageDmnd.scaledHeight(400);

            ImageViewer imgv = new ImageViewer(imageDmnd);
            imgv.setSize(new Dimension(700, 400));

            Container cImg = new Container();
            cImg.setSize(new Dimension(700, 400));
            cImg.add(imgv);

            Container shareBox = new Container(BoxLayout.x());
            ShareButton shareBtn = new ShareButton();
            shareBtn.setText("Partager");
            Style shareStyle = UIManager.getInstance().getComponentStyle("aide_ShareStyle");
            Image shareIcon = FontImage.createMaterial(FontImage.MATERIAL_SHARE, shareStyle);
            shareBtn.setUIID("aide_ShareBtn");
            shareBtn.setIcon(shareIcon);
            shareBtn.setTextToShare("https://donation.tn/");

            
            
            Button supp = new Button();
            supp.setText("Supprimer");
            Style suppStyle = UIManager.getInstance().getComponentStyle("aide_ShareStyle");
            Image suppIcon = FontImage.createMaterial(FontImage.MATERIAL_DELETE, shareStyle);
            supp.setUIID("aide_ShareBtn");
            supp.setIcon(suppIcon);

            Button detail = new Button();
            detail.setText("Détail");
            Style btnStyle = UIManager.getInstance().getComponentStyle("aide_ShareStyle");
            Image btnIcon = FontImage.createMaterial(FontImage.MATERIAL_VISIBILITY, shareStyle);
            detail.setUIID("aide_ShareBtn");
            detail.setIcon(btnIcon);
            
            shareBox.add(detail);
            shareBox.add(supp);
            shareBox.add(shareBtn);

            
            detail.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    new h_UnEmploi_emploi(emp).show();
                }
            });
            
            
            supp.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceEmploi.getInstance().deleteEmploi(emp);
                    new h_MesEmploi_emploi1().show();
                }
            });
            
            Label separator = new Label("", "WhiteSeparator");
            y.setUIID("dmndAideBox");
            y.add(cImg);
            y.add(titre);
            y.add(description);
            y.add(shareBox);
            y.add(separator);
            
            current.add(y);
        }

    }

}
