package com.example.lifecity.ui.commerce.List;

import java.io.Serializable;

public class Commerce implements Serializable {

    private String nom;
    private String ville;
    private String adresse;
    private String interet;

    private Commerce(){}

    private Commerce(String n, String v, String a, String i){
        nom = n;
        ville = v;
        adresse = a;
        interet = i;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String n){
        nom = n;
    }

    public String getVille(){
        return ville;
    }

    public void setVille(String n){
        ville = n;
    }

    public String getAdresse(){
        return adresse;
    }

    public void setAdresse(String n){
        adresse = n;
    }

    public String getInteret(){
        return interet;
    }

    public void setInteret(String n){
        interet = n;
    }
}
