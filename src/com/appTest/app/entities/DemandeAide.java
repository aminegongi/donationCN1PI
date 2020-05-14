/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.entities;

/**
 *
 * @author Hedi
 */
public class DemandeAide {
    private int id,idCategorie,idUser;
    private String titre,description,photo,etat;
    private String status;
    private int nbReaction;

    public DemandeAide() {
    }

    public DemandeAide(int idCategorie, int idUser, String titre, String description, String photo, String etat, int nbReaction) {
        this.idCategorie = idCategorie;
        this.idUser = idUser;
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.etat = etat;
        this.nbReaction = nbReaction;
    }

    public DemandeAide(int id, int idCategorie, int idUser, String titre, String description, String photo, String etat, int nbReaction) {
        this.id = id;
        this.idCategorie = idCategorie;
        this.idUser = idUser;
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.etat = etat;
        this.nbReaction = nbReaction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getNbReaction() {
        return nbReaction;
    }

    public void setNbReaction(int nbReaction) {
        this.nbReaction = nbReaction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    @Override
    public String toString() {
        return "DemandeAide{" + "id=" + id + ", idCategorie=" + idCategorie + ", idUser=" + idUser + ", titre=" + titre + ", description=" + description + ", photo=" + photo + ", etat=" + etat + ", nbReaction=" + nbReaction + '}';
    }
    
    
}
