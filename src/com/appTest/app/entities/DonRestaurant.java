/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.entities;

/**
 *
 * @author safratix
 */
public class DonRestaurant {
    private int idResto;
    private String donator;
    private float montant;
    
    public DonRestaurant (int idResto, String donator, float montant){
        this.idResto = idResto;
        this.donator = donator;
        this.montant = montant;
    }

    public int getIdResto() {
        return idResto;
    }

    public void setIdResto(int idResto) {
        this.idResto = idResto;
    }

    public String getDonator() {
        return donator;
    }

    public void setDonator(String donator) {
        this.donator = donator;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "DonRestaurant{" + "idResto=" + idResto + ", donator=" + donator + ", montant=" + montant + '}';
    }
    
    
    
}
