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
public class CategorieAide {
    private int id;
    private String nom,icone;

    public CategorieAide() {
    }

    public CategorieAide(String nom, String icone) {
        this.nom = nom;
        this.icone = icone;
    }

    public CategorieAide(int id, String nom, String icone) {
        this.id = id;
        this.nom = nom;
        this.icone = icone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    @Override
    public String toString() {
        return "CategorieAide{" + "id=" + id + ", nom=" + nom + ", icone=" + icone + '}';
    }
    
    
    
}
