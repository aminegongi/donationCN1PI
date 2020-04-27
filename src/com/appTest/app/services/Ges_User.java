/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.appTest.app.entities.User;
import com.appTest.app.utils.Adresse;
import com.appTest.app.utils.Statics;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amine Gongi
 */
public class Ges_User {

    public ArrayList<User> users;

    public static Ges_User instance = null;
    public boolean resultOK;
    public int addRes;
    public int loginRes;
    public int actRes;
    String res;
    private ConnectionRequest req;
    private Database db;

    private Ges_User() {
        req = new ConnectionRequest();
        try {
            db = Database.openOrCreate("donation");
            db.execute("Create table if not exists rememberU (idUser TEXT , date TEXT , code TEXT );");
        } catch (IOException ex) {
            System.out.println("ERR");
            System.out.println(ex.getMessage());
        }
    }

    public static Ges_User getInstance() {
        if (instance == null) {
            instance = new Ges_User();
        }
        return instance;
    }

    public void insertRem(String userID, String date) {
        try {
            db.execute("insert into rememberU (idUser ,date) values ('" + userID + "' ,'" + date + "');");
        } catch (IOException ex) {
            System.out.println("INSERT ERR");
            System.out.println(ex.getMessage());
        }
    }

    public void insertRemWCode(String userID, String date, String code) {
        try {
            db.execute("insert into rememberU (idUser ,date,code) values ('" + userID + "' ,'" + date + "','" + code + "');");
        } catch (IOException ex) {
            System.out.println("INSERT ERR");
            System.out.println(ex.getMessage());
        }
    }

    public void deleteDb() {
        try {
            db.execute("delete from rememberU where 1 ;");
        } catch (IOException ex) {
            System.out.println("deletet ERR");
            System.out.println(ex.getMessage());
        }
    }

    public int checkOneRem() {
        try {
            Cursor cr = db.executeQuery("select * from rememberU LIMIT 1 ;");
            while (cr.next()) {
                Row row = cr.getRow();
                return Integer.parseInt(row.getString(0));
            }
            cr.close();
            return -1;
        } catch (IOException ex) {
            System.out.println("check ERR ERR");
            System.out.println(ex.getMessage());
            return -1;
        }
    }

    public String getCodeRem(String idu) {
        try {
            Cursor cr = db.executeQuery("select * from rememberU where idUser='" + idu + "'  LIMIT 1 ;");
            while (cr.next()) {
                System.out.println("yes one");
                Row row = cr.getRow();
                return row.getString(2);
            }
            cr.close();
            return "errno";
        } catch (IOException ex) {
            System.out.println("check ERR ERR");
            System.out.println(ex.getMessage());
            return "errno";
        }
    }

    //-1 mail existant / -2 username existant / -3 info incmplete / -11 existants zouz / 1 perfect
    public int inscription(User u) {
        String url = Statics.BASE_URL + "/user/api/inscri?un=" + u.getUsername() + "&mail=" + u.getEmail() + "&tel=" + u.getNumTel() + "&mdp=" + u.getPassword() + "&role=" + u.getRoles() + "&yn=" + u.getYesNews();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                res = new String(req.getResponseData());
                if (res.contains("existants")) {
                    addRes = -11;
                } else if (res.contains("mail existant")) {
                    addRes = -1;
                } else if (res.contains("username existant")) {
                    addRes = -2;
                } else if (res.contains("information incomplete")) {
                    addRes = -3;
                } else {
                    addRes = 0;
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return addRes;
    }

    public int login(String mail, String pass) {
        String url = Statics.BASE_URL + "/user/api/login?mail=" + mail + "&pass=" + pass;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                res = new String(req.getResponseData());
                if (res.contains("paspaspasNon")) {
                    loginRes = -1;
                } else {
                    loginRes = 0;
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return loginRes;
    }

    public int activerCompte(String mail, String token) {
        String url = Statics.BASE_URL + "/user/api/activer?mail=" + mail + "&token=" + token;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                res = new String(req.getResponseData());
                if (res.contains("non")) {
                    actRes = -1;
                } else {
                    actRes = 0;
                }
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return actRes;
    }

    public ArrayList<User> JsonToUserParser(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
                u.setUsername((String) obj.get("username"));
                u.setEmail((String) obj.get("email"));
                u.setPassword((String) obj.get("password"));
                u.setRoles((String) obj.get("roles").toString());
                u.setAdresse(new Adresse((String) obj.get("pays"), (String) obj.get("ville")));
                u.setNumTel((String) obj.get("numTel"));
                u.setNom((String) obj.get("nom"));
                u.setPrenom((String) obj.get("prenom"));
                u.setImage((String) obj.get("image"));
                u.setGenre((String) obj.get("genre"));
                /*if(obj.get("dateNaissance") != null){
                 Date d = new Date(obj.get("dateNaissance").toString());
                 u.setDateNaissance(d);
                 }*/

                u.setPageFB((String) obj.get("pageFB"));
                u.setSiteWeb((String) obj.get("siteWeb"));
                u.setDescription((String) obj.get("description"));
                if (obj.get("longitude") != null) {
                    u.setLongitude(Float.parseFloat(obj.get("longitude").toString()));
                }
                if (obj.get("latitude") != null) {
                    u.setLatitude(Float.parseFloat(obj.get("latitude").toString()));
                }

                u.setYesNews((int) Float.parseFloat(obj.get("yesNews").toString()));

                users.add(u);
            }

        } catch (IOException ex) {

        }
        return users;
    }

    public ArrayList<User> getUserbyId(int id) {
        String url = Statics.BASE_URL + "/user/api/getUserID?id=" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jp = "[" + new String(req.getResponseData()) + "]";
                users = JsonToUserParser(jp);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

    public ArrayList<User> getUserbyMail(String mail) {
        String url = Statics.BASE_URL + "/user/api/getUserMail?mail=" + mail;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String jp = new String(req.getResponseData());
                users = JsonToUserParser(jp);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }

}
