/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.model;

import static rs.ac.bg.etf.pkiproject.context.Context.RESTAURANT_PICTURE_PATH_TEMPLATE;

/**
 *
 * @author Nemanja
 */
public class Restaurant {
    
    private String id;
    private String name;
    private double raiting;
    private String desc;
    private String address;
    private String location;
    private String picturePath;

    public Restaurant() {
        
    }

    public Restaurant(String id, String name, double raiting, String desc) {
        this.id = id;
        this.name = name;
        this.raiting = raiting;
        this.desc = desc;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRaiting() {
        return raiting;
    }

    public void setRaiting(double raiting) {
        this.raiting = raiting;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicturePath() {
        return RESTAURANT_PICTURE_PATH_TEMPLATE.replaceAll("#NAME", name.toLowerCase().replaceAll(" ", "-"));
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
    
}
