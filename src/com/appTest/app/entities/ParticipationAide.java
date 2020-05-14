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
public class ParticipationAide {
    private int id,idDemande,idUser;
    String emailUser;

    public ParticipationAide() {
    }

    public ParticipationAide(int idDemande, int idUser) {
        this.idDemande = idDemande;
        this.idUser = idUser;
    }

    public ParticipationAide(int idDemande, int idUser, String emailUser) {
        this.idDemande = idDemande;
        this.idUser = idUser;
        this.emailUser = emailUser;
    }
    
    

    public ParticipationAide(int id, int idDemande, int idUser) {
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

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    
    
    @Override
    public String toString() {
        return "ParticipationAide{" + "id=" + id + ", idDemande=" + idDemande + ", idUser=" + idUser + '}';
    }
    
    
}
