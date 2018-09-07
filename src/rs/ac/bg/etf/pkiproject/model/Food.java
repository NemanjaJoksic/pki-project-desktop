/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.etf.pkiproject.model;

import static rs.ac.bg.etf.pkiproject.context.Context.FOOD_PICTURE_PATH_TEMPLATE;
import static rs.ac.bg.etf.pkiproject.context.Context.RESTAURANT_PICTURE_PATH_TEMPLATE;

/**
 *
 * @author Nemanja
 */
public class Food {
    
    private String id;
    private String name;
    private String desc;
    private String picturePath;
    private double price;
    
    public Food() {
        
    }
    
    public Food(String name, String desc) {
        this.name = name;
        this.desc = desc;
        this.picturePath = FOOD_PICTURE_PATH_TEMPLATE.replaceAll("#NAME", name.toLowerCase().replaceAll(" ", "-"));
    }

    public Food(String id, String name, String desc, double price) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.picturePath = FOOD_PICTURE_PATH_TEMPLATE.replaceAll("#NAME", name.toLowerCase().replaceAll(" ", "-"));
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicturePath() {
        return FOOD_PICTURE_PATH_TEMPLATE.replaceAll("#NAME", name.toLowerCase().replaceAll(" ", "-"));
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
