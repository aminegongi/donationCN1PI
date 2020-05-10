/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appTest.app.entities;

/**
 *
 * @author Ahmed Fourati
 */
public class Pub {
    int id ;
    String image; 
    String description;
    String titre;

    public Pub(int id, String image, String description, String titre) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Pub{" + "id=" + id + ", image=" + image + ", description=" + description + ", titre=" + titre + '}';
    }

    public Pub() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    
}
