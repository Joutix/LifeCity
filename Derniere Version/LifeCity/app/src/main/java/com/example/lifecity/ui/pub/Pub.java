package com.example.lifecity.ui.pub;

import java.io.Serializable;

public class Pub implements Serializable {
    private String NomPUB;
    private String Description;
    private String URLImage;

    private Pub(){}

    private Pub(String n, String v, String i){
        NomPUB = n;
        Description = v;
        URLImage = i;
    }

    public String getNomPUB(){
        return NomPUB;
    }

    public void setNomPUB(String n){
        NomPUB = n;
    }

    public String getDescription(){
        return Description;
    }

    public void setDescription(String n){
        Description = n;
    }

    public String getURLImage(){
        return URLImage;
    }

    public void setURLImage(String n){
        URLImage = n;
    }
}
