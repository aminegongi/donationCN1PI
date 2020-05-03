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
public class RestoMapCoord {
    private String username;
    private String fb;
    private String web;
    private String icon;
    private float longitude;
    private float latitude;
    private Boolean activeLongitude = true;
    private Boolean activeLatitude = true;

    public RestoMapCoord(String username, String coord, String fb, String web, String icon, float longitude, float latitude) {
        this.username = username;
        this.fb = fb;
        this.web = web;
        this.icon = icon;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public RestoMapCoord() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public Boolean getActiveLongitude() {
        return activeLongitude;
    }

    public void setActiveLongitude(Boolean activeLongitude) {
        this.activeLongitude = activeLongitude;
    }

    public Boolean getActiveLatitude() {
        return activeLatitude;
    }

    public void setActiveLatitude(Boolean activeLatitude) {
        this.activeLatitude = activeLatitude;
    }
    
    

    @Override
    public String toString() {
        return "RestoMapCoord{" + "username=" + username + ", fb=" + fb + ", web=" + web + ", icon=" + icon + ", longitude=" + longitude + ", latitude=" + latitude + '}';
    }
    
    

    
    
    
}
