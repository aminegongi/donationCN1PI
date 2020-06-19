/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.entities;
/**
 *
 * @author Ahmed Zeghibi
 */
//Cagnotte/newCagnotteMobile?user=x&nom=x&montant_demande=x&id_categorie=x

public class Cagnotte{
    private int id;
    private int user;
    private String nom;
    private float montant_demande;
    private float montant_actuel;
    private float categorie;
    private int etat;

    public Cagnotte() {
    }

    public Cagnotte(int user, String nom, float montant_demande, float montant_actuel) {
        this.user = user;
        this.nom = nom;
        this.montant_demande = montant_demande;
        this.montant_actuel = montant_actuel;
        this.categorie = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getMontant_demande() {
        return montant_demande;
    }

    public void setMontant_demande(float montant_demande) {
        this.montant_demande = montant_demande;
    }

    public float getMontant_actuel() {
        return montant_actuel;
    }

    public void setMontant_actuel(float montant_actuel) {
        this.montant_actuel = montant_actuel;
    }

    public float getCategorie() {
        return categorie;
    }

    public void setCategorie(float categorie) {
        this.categorie = categorie;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    

    @Override
    public String toString() {
        return "Cagnotte{" + "id=" + id + ", user=" + user + ", nom=" + nom + ", montant_demande=" + montant_demande + ", montant_actuel=" + montant_actuel + ", categorie=" + categorie + '}';
    }
    
        
}
