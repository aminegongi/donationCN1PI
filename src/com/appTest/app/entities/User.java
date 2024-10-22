/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.entities;

import com.appTest.app.utils.Adresse;
import java.util.Date;

/**
 *
 * @author Amine Gongi
 */
public class User {
    private int id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private int enabled;
    private String salt;
    private String password;
    private Date last_login;
    private String confirmation_token;
    private Date password_requested_at;
    private String roles;
    private String nom;
    private String numTel;
    
    //private String pays;
    //private String ville;
    protected Adresse adresse;
    
    private String image;
    private int pointXP;
    private String prenom;
    private String genre;
    private Date dateNaissance;
    private String pageFB;
    private String siteWeb;
    private String description;
    private float longitude;
    private float latitude;
    private int yesNews;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPointXP() {
        return pointXP;
    }

    public void setPointXP(int pointXP) {
        this.pointXP = pointXP;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPageFB() {
        return pageFB;
    }

    public void setPageFB(String pageFB) {
        this.pageFB = pageFB;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getYesNews() {
        return yesNews;
    }

    public void setYesNews(int yesNews) {
        this.yesNews = yesNews;
    }

    public User() {
    }

    public User(int id, String username, String email, String roles, String nom, String numTel, Adresse adresse, String image, int pointXP, String prenom, String genre, Date dateNaissance, String pageFB, String siteWeb, String description, float longitude, float latitude, int yesNews) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.nom = nom;
        this.numTel = numTel;
        this.adresse = adresse;
        this.image = image;
        this.pointXP = pointXP;
        this.prenom = prenom;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
        this.pageFB = pageFB;
        this.siteWeb = siteWeb;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.yesNews = yesNews;
    }

    
    public User(String username, String email, String password, String roles, String numTel, int yesNews) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.numTel = numTel;
        this.yesNews = yesNews;
    }

    public User(int id, String nom, Adresse adresse, String image, String prenom, String genre, Date dateNaissance) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.image = image;
        this.prenom = prenom;
        this.genre = genre;
        this.dateNaissance = dateNaissance;
    }

    public User(int id, String image, String pageFB, String siteWeb, String description, float longitude, float latitude) {
        this.id = id;
        this.image = image;
        this.pageFB = pageFB;
        this.siteWeb = siteWeb;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", last_login=" + last_login + ", confirmation_token=" + confirmation_token + ", password_requested_at=" + password_requested_at + ", roles=" + roles + ", nom=" + nom + ", numTel=" + numTel + ", adresse=" + adresse + ", image=" + image + ", pointXP=" + pointXP + ", prenom=" + prenom + ", genre=" + genre + ", dateNaissance=" + dateNaissance + ", pageFB=" + pageFB + ", siteWeb=" + siteWeb + ", description=" + description + ", longitude=" + longitude + ", latitude=" + latitude + ", yesNews=" + yesNews + '}';
    }
    
    public String tokenBaseString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ",password=" + password + ", roles=" + roles +  ", numTel=" + numTel +  ", yesNews=" + yesNews + '}';
    }
    
}
