/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.gui;

import com.appTest.app.entities.User;
import com.appTest.app.services.Ges_User;
import com.appTest.app.utils.Adresse;
import com.appTest.app.utils.Statics;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.Switch;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;
import java.util.ArrayList;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Amine Gongi
 */
public class ProfilModifUser_gui extends SideMenuNov {

    EncodedImage enc;
    ImageViewer imgv;
    Image img;
    int save = 0;
    String sub;

    TextField tfnom;
    TextField tfprenom;
    //TextField tfdateNaissance = new TextField(u.getDateNaissance(), "Date Naissance");
    Switch sGenre;

    FileUploader file;
    String fns;
    String imgp = null;

    public ProfilModifUser_gui() {
        setTitle("Modification Profil");
        setLayout(BoxLayout.y());
        User u = FLogIns_gui.userCon;
        sub = u.getRoles().substring(1, u.getRoles().indexOf(","));

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (save == 0) {
                    if (Dialog.show("Attention", "Vous n'avez pas enregistrer vos changements", "Sortir", "Ah Oui")) {
                        new ProfilUser_gui().showBack();
                    }
                }
            }
        });
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_SAVE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //el traitement t3awed ta3mel user + getUser FLoginC
                System.out.println(sub);
                if (sub.equals("Utilisateur Simple")) {
                    String gg = new String();
                    if (sGenre.isOn()) {
                        gg = "Femme";
                    } else if (sGenre.isOff()) {
                        gg = "Homme";
                    }
                    if (imgp == null) {
                        imgp = FLogIns_gui.userCon.getImage();
                    } else {
                        String l = imgp.toString();
                        int p = l.indexOf("/", 2);
                        String n = l.substring(p + 2, l.length());

                        FileUploader f = new FileUploader(Statics.BASE_URL_Upload_Image_User);
                        try {
                            fns = f.upload(n);
                        } catch (Exception ex) {
                            System.out.println("errrrr");
                        }
                    }
                    User um = new User(FLogIns_gui.userCon.getId(), tfnom.getText(), null, fns, tfprenom.getText(), gg, null);
                    ArrayList<User> u = Ges_User.getInstance().ModifierUS(um);
                    FLogIns_gui.userCon = u.get(0);
                    new ProfilUser_gui().show();
                }
            }
        });

        try {
            enc = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {
            System.out.println("enc ERR");
        }

        String urlImg = Statics.BASE_URL_Image_User + u.getImage();

        img = URLImage.createToStorage(enc, urlImg, urlImg, URLImage.RESIZE_SCALE);

        imgv = new ImageViewer(img);
        Container y = new Container(BoxLayout.yCenter());
        Container x = new Container(BoxLayout.xCenter());

        Button btCam = new Button(FontImage.MATERIAL_CAMERA_ALT);
        Button btGal = new Button(FontImage.MATERIAL_PHOTO);
        btCam.setUIID("SkipButton");
        btGal.setUIID("SkipButton");

        btCam.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                imgp = Capture.capturePhoto();
                if (imgp == null) {
                    showToast("User canceled Camera");
                    return;
                }
                setImage(imgp, imgv);
            }
        });

        btGal.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().openGallery(e -> {
                    if (e == null || e.getSource() == null) {
                        showToast("User canceled Gallery");
                        return;
                    }
                    String imgp = (String) e.getSource();
                    setImage(imgp, imgv);
                }, Display.GALLERY_IMAGE);
            }
        });

        y.addAll(btGal, btCam);
        x.addAll(imgv, y);
        add(BorderLayout.center(x));
        add(createLineSeparator(0xeeeeee));

        int t = -1;
        if (sub.equals("ROLE_RES")) {
            sub = "Restaurant";
            t = 2;
        } else if (sub.equals("ROLE_US")) {
            sub = "Utilisateur Simple";
            t = 1;
        } else if (sub.equals("ROLE_ORG")) {
            sub = "Organisation";
            t = 2;
        } else if (sub.equals("ROLE_ENT")) {
            sub = "Entreprise";
            t = 2;
        } else if (sub.equals("ROLE_ADMIN")) {
            sub = "Le Boss";
            t = 3;
        }

        if (t == 1) {
            tfnom = new TextField(u.getNom(), "Nom");
            tfnom.setUIID("TextFieldBlack");
            tfprenom = new TextField(u.getPrenom(), "Prénom");
            tfprenom.setUIID("TextFieldBlack");
            //TextField tfdateNaissance = new TextField(u.getDateNaissance(), "Date Naissance");
            sGenre = new Switch();

            Container xx = new Container(BoxLayout.xCenter());

            xx.addAll(new Label("Homme"), sGenre, new Label("Femme"));

            addStringValue("Nom", tfnom);

            addStringValue("Prénom", tfprenom);

            /*if (u.getDateNaissance() != null) {
             addStringValue("date Naissance", tfdateNaissance);
             }*/
            if (u.getGenre() != null) {

                if (u.getGenre().contains("Homme")) {
                    sGenre.setOff();
                } else if (u.getGenre().contains("Femme")) {
                    sGenre.setOn();
                }
            }
            add(xx);

        } else if (t == 2) {
            TextField tfPageFb = new TextField(u.getPageFB(), "Facebook");
            TextField tfsWeb = new TextField(u.getSiteWeb(), "Site Web");
            TextField taDesc = new TextField(u.getDescription(), "Déscription");
            TextField tfLong = new TextField(Float.toString(u.getLongitude()), "Longitude");
            TextField tfLat = new TextField(Float.toString(u.getLatitude()), "Latitude");

            addStringValue("Facebook", tfPageFb);
            addStringValue("Site Web", tfsWeb);
            addStringValue("Déscription", taDesc);
            addStringValue("Longitude", tfLong);
            addStringValue("latitude", tfLat);

        }

    }

    private void showToast(String text) {
        Image errorImage = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"), 4);
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage(text);
        status.setIcon(errorImage);
        status.setExpires(2000);
        status.show();
    }

    private void setImage(String filePath, ImageViewer iv) {
        try {
            Image i1 = Image.createImage(filePath);
            iv.setImage(i1);
            iv.getParent().revalidate();
        } catch (Exception ex) {
            Dialog.show("Error", "Error during image loading: " + ex, "OK", null);
        }
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

}
