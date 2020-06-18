/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.CategorieAide;
import com.appTest.app.entities.DemandeAide;
import com.appTest.app.entities.Emploi;
import com.appTest.app.services.ServiceCategorieAide;
import com.appTest.app.services.ServiceDemandeAide;
import com.appTest.app.services.ServiceEmploi;
import com.appTest.app.utils.AideImgUpload;
import com.appTest.app.utils.OpenGalleryAide;
import com.appTest.app.utils.Statics;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Hedi
 */
public class h_addEmploi_emploi extends SideMenuNov {

    Form current;
    Button btnVal = new Button("Publier");
    TextField tfTitre = new TextField();
    TextField tfSalaire = new TextField();
    TextArea tfDesc = new TextArea();
    Button Photo = new Button("Image");
    ComboBox categorie = new ComboBox();
    ComboBox typeEmploi = new ComboBox();
    ComboBox typeContrat = new ComboBox();
    ComboBox emplacement = new ComboBox();
    
    
    FileUploader file;
    String fns;
    String imgp = null;
    ImageViewer imgv;

    public h_addEmploi_emploi() {
        addSideMenu();
        getToolbar().setUIID("aide_Toolbar");
        current = this;
        current.setTitle("Déposer Un emploi");
        current.setLayout(BoxLayout.y());

        tfTitre.setHint("Titre De l'emploi");
        tfTitre.setUIID("TextFieldBlack");

        tfSalaire.setHint("Salaire");
        tfTitre.setUIID("TextFieldBlack");

        tfDesc.setHint("Description de l'emploi");
        tfDesc.setRows(5);

        categorie.addItem("Informatique");
        categorie.addItem("Sport");
        categorie.addItem("jardinier");

        typeEmploi.addItem("Offre");
        typeEmploi.addItem("Demande");

        typeContrat.addItem("Contrat Duree Indeterminee");
        typeContrat.addItem("Contrat duree determinee");
        typeContrat.addItem("Contrat Travail Temporaire");
        typeContrat.addItem("Contrat Apprentissage");
        typeContrat.addItem("Contrat Professionnalisation");
        typeContrat.addItem("Contrat Unique Insertion");

        emplacement.addItem("Tunis");
        emplacement.addItem("Sousse");

        
        Validator v = new Validator();
        v.addConstraint(tfSalaire, new LengthConstraint(2, "champ nom vide "), new RegexConstraint("[0-9]+", "champ Numéro ne doit pas contenir des char specieaux"));
        current.addAll(tfTitre, emplacement, categorie, typeEmploi, typeContrat,Photo, tfSalaire, tfDesc, btnVal);

        Photo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().openGallery(e -> {
                    if (e == null || e.getSource() == null) {
                        return;
                    }
                    imgp = (String) e.getSource();
                }, Display.GALLERY_IMAGE);
            }
        });
        btnVal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if ( imgp == null || tfTitre.getText().length() == 0 || tfSalaire.getText().length() == 0 || tfDesc.getText().length() == 0) {
                    Dialog.show("Erreur !", "Champs Vide", new Command("OK"));
                } 
                else {
                    String catego = "";
                    if (categorie.getSelectedIndex() == 0) {
                        catego = "Informatique";
                    } else if (categorie.getSelectedIndex() == 1) {
                        catego = "Sport";
                    } else if (categorie.getSelectedIndex() == 2) {
                        catego = "Jardinier";
                    }

                    String tyEmp = "";
                    if (typeEmploi.getSelectedIndex() == 0) {
                        tyEmp = "Offre";
                    } else if (typeEmploi.getSelectedIndex() == 1) {
                        tyEmp = "Demande";
                    }

                    String tyCon = "";
                    if (typeContrat.getSelectedIndex() == 0) {
                        tyCon = "ContratDureeIndeterminee";
                    } else if (typeContrat.getSelectedIndex() == 1) {
                        tyCon = "Contratdureedeterminee";
                    } else if (typeContrat.getSelectedIndex() == 2) {
                        tyCon = "Contrat Travail Temporaire";
                    } else if (typeContrat.getSelectedIndex() == 3) {
                        tyCon = "Contrat Apprentissage";
                    } else if (typeContrat.getSelectedIndex() == 4) {
                        tyCon = "Contrat Professionnalisation";
                    } else if (typeContrat.getSelectedIndex() == 5) {
                        tyCon = "Contrat Unique Insertion";
                    }

                    String emp = "";
                    if (emplacement.getSelectedIndex() == 0) {
                        emp = "Tunis";
                    } else if (emplacement.getSelectedIndex() == 1) {
                        emp = "Sousse";
                    }
                    
                    String l = imgp.toString();
                    int p = l.indexOf("/", 2);
                    String n = l.substring(p + 2, l.length());

                    FileUploader f = new FileUploader(Statics.BASE_URL_Upload_Image_Emploi);
                    try {
                        fns = f.upload(n);
                    } catch (Exception ex) {
                        System.out.println("errrrr");
                    }
                    
                    Emploi e = new Emploi();
                    e.setTitre(tfTitre.getText());
                    e.setDescription(tfDesc.getText());
                    e.setSalaire(Float.parseFloat(tfSalaire.getText()));
                    e.setCategorie(catego);
                    e.setType_contrat(tyCon);
                    e.setType_emploi(tyEmp);
                    e.setEmplacement(emp);
                    e.setUser(FLogIns_gui.userCon);
                    e.setPhoto(fns);

                    ServiceEmploi.getInstance().addEmploi(e);
                    Dialog.show("Bravo !", "Annonce d'emploi déposer avec succes", new Command("OK"));
                    new h_MesEmploi_emploi1().show();
                }
            }
        });

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

    }

}
