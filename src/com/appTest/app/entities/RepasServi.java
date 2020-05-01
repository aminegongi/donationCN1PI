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
public class RepasServi {
    private int idResto;

    public RepasServi(int idResto) {
        this.idResto = idResto;
    }

    public int getIdResto() {
        return idResto;
    }

    public void setIdResto(int idResto) {
        this.idResto = idResto;
    }

    @Override
    public String toString() {
        return "RepasServi{" + "idResto=" + idResto + '}';
    }
    
    
    
}
