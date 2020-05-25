package com.example.lifecity.ui.commerce.List;

import java.io.Serializable;

public class Commerce implements Serializable {

    private String Nom;
    private String Ville;
    private String Adresse;
    private String Interet;
    private String image;

    private Commerce(){}

    private Commerce(String Nom, String Ville, String Adresse, String Interet, String image){
        this.Nom = Nom;
        this.Ville = Ville;
        this.Adresse = Adresse;
        this.Interet = Interet;
        this.image = image;
    }

    public String getNom(){
        return Nom;
    }

    public void setNom(String n){
        Nom = n;
    }

    public String getVille(){
        return Ville;
    }

    public void setVille(String n){
        Ville = n;
    }

    public String getAdresse(){
        return Adresse;
    }

    public void setAdresse(String n){
        Adresse = n;
    }

    public String getInteret(){
        return Interet;
    }

    public void setInteret(String n){
        Interet = n;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String n){
        image = n;
    }
}
