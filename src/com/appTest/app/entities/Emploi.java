/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.entities;

/**
 *
 * @author hechem
 */
public class Emploi {
    private int id;
    private User user;
    private String categorie;
    private float salaire;
    private String titre,description,photo,emplacement,type_emploi,type_contrat;

    public Emploi(int id, User user, String categorie, float salaire, String titre, String description, String photo, String emplacement, String type_emploi, String type_contrat) {
        this.id = id;
        this.user = user;
        this.categorie = categorie;
        this.salaire = salaire;
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.emplacement = emplacement;
        this.type_emploi = type_emploi;
        this.type_contrat = type_contrat;
    }

    @Override
    public String toString() {
        return "Emploi{" + "id=" + id + ", user=" + user + ", categorie=" + categorie + ", salaire=" + salaire + ", titre=" + titre + ", description=" + description + ", photo=" + photo + ", emplacement=" + emplacement + ", type_emploi=" + type_emploi + ", type_contrat=" + type_contrat + '}';
    }

    public Emploi(User user, String categorie, float salaire, String titre, String description, String photo, String emplacement, String type_emploi, String type_contrat) {
        this.user = user;
        this.categorie = categorie;
        this.salaire = salaire;
        this.titre = titre;
        this.description = description;
        this.photo = photo;
        this.emplacement = emplacement;
        this.type_emploi = type_emploi;
        this.type_contrat = type_contrat;
    }

    public Emploi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
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

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public String getType_emploi() {
        return type_emploi;
    }

    public void setType_emploi(String type_emploi) {
        this.type_emploi = type_emploi;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public void setType_contrat(String type_contrat) {
        this.type_contrat = type_contrat;
    }

    
    
    
    
    
    
}
