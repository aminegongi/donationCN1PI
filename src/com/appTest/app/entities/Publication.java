/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.entities;

import com.appTest.app.entities.User;
import java.util.Date;

/**
 *
 * @author Ahmed Fourati
 */
public class Publication {
    private int id ; 
    private String type ; 
    private String titre ; 
    private String description ;
    private String datePublication ; 
    private int nbrUp ; 
    private int nbrePlat ; 
    private int etat ; 
    private User ajoutePar; 

    public User getAjoutePar() {
        return ajoutePar;
    }

    public void setAjoutePar(User ajoutePar) {
        this.ajoutePar = ajoutePar;
    }

    public Publication(int id, String type, String titre, String description, int nbrUp, int nbrePlat, int etat, User ajoutePar) {
        this.id = id;
        this.type = type;
        this.titre = titre;
        this.description = description;
        this.nbrUp = nbrUp;
        this.nbrePlat = nbrePlat;
        this.etat = etat;
        this.ajoutePar=ajoutePar;
    }

    public Publication() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public int getNbrUp() {
        return nbrUp;
    }

    public void setNbrUp(int nbrUp) {
        this.nbrUp = nbrUp;
    }

    public int getNbrePlat() {
        return nbrePlat;
    }

    public void setNbrePlat(int nbrePlat) {
        this.nbrePlat = nbrePlat;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", type=" + type + ", titre=" + titre + ", description=" + description + ", datePublication=" + datePublication + ", nbrUp=" + nbrUp + ", nbrePlat=" + nbrePlat + ", etat=" + etat + '}';
    }
    
    
}
