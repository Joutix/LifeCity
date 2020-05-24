package com.example.lifecity.ui.news.ui.main;

public class Article {

    String titreArticle;
    String descArticle;
    String nomSite;
    String urlImage;
    String urlArticle;

    public Article(String titreArticle, String descArticle, String nomSite, String urlImage, String urlArticle) {
        this.titreArticle=titreArticle;
        this.descArticle=descArticle;
        this.nomSite = nomSite;
        this.urlImage = urlImage;
        this.urlArticle = urlArticle;
    }

    public String getTitreArticle() {
        return titreArticle;
    }

    public String getDescArticle() {
        return descArticle;
    }

    public String getNomSite() {
        return nomSite;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getUrlArticle() {
        return urlArticle;
    }
}
