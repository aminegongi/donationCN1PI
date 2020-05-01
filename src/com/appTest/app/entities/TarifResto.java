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
public class TarifResto {
    private int idResto;
    private float tarif;
    private float portefeuilleVirtuel;

    public TarifResto(int idResto, float tarif, float portefeuilleVirtuel) {
        this.idResto = idResto;
        this.tarif = tarif;
        this.portefeuilleVirtuel = portefeuilleVirtuel;
    }

    public TarifResto(int idResto, float tarif) {
        this.idResto = idResto;
        this.tarif = tarif;
    }

    public TarifResto(int idResto) {
        this.idResto = idResto;
    }
    
    

    public TarifResto() {
    }
    
    

    public int getIdResto() {
        return idResto;
    }

    public void setIdResto(int idResto) {
        this.idResto = idResto;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public float getPortefeuilleVirtuel() {
        return portefeuilleVirtuel;
    }

    public void setPortefeuilleVirtuel(float portefeuilleVirtuel) {
        this.portefeuilleVirtuel = portefeuilleVirtuel;
    }

    @Override
    public String toString() {
        return "TarifResto{" + "idResto=" + idResto + ", tarif=" + tarif + ", portefeuilleVirtuel=" + portefeuilleVirtuel + '}';
    }
    
}
