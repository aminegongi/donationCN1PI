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
public class ReactionAide {
     private int id,idDemande,idUser;

    public ReactionAide() {
    }

    public ReactionAide(int idDemande, int idUser) {
        this.idDemande = idDemande;
        this.idUser = idUser;
    }

    public ReactionAide(int id, int idDemande, int idUser) {
        this.id = id;
        this.idDemande = idDemande;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDemande() {
        return idDemande;
    }

    public void setIdDemande(int idDemande) {
        this.idDemande = idDemande;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
     
     
}
