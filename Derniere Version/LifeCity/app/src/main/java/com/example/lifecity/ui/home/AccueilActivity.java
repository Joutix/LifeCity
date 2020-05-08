package com.example.lifecity.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lifecity.ui.commerce.CommerceActivity;
import com.example.lifecity.ui.news.NewsActivity;
import com.example.lifecity.MainActivity;
import com.example.lifecity.R;
import com.example.lifecity.ui.pub.PubActivity;
import com.example.lifecity.ui.reseaux.ReseauxActivity;

public class AccueilActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView commerceCard, newsCard, reseauxCard, pubCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        // Definition des ViewCard
        commerceCard = (CardView) findViewById(R.id.card_Commerce);
        newsCard = (CardView) findViewById(R.id.card_News);
        reseauxCard = (CardView) findViewById(R.id.card_Reseaux);
        pubCard = (CardView) findViewById(R.id.card_Publicites);
        //On ajoute les ClickListener
        commerceCard.setOnClickListener(this);
        newsCard.setOnClickListener(this);
        reseauxCard.setOnClickListener(this);
        pubCard.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId()){
            case R.id.card_Commerce :
                i = new Intent(this, CommerceActivity.class);
                startActivity(i);
                break;
            case R.id.card_News :
                i = new Intent(this, NewsActivity.class);
                startActivity(i);
                break;
            case R.id.card_Reseaux :
                i = new Intent(this, ReseauxActivity.class);
                startActivity(i);
                break;
            case R.id.card_Publicites :
                i = new Intent(this, PubActivity.class);
                startActivity(i);
                break;
            default :
                break;

        }
    }
}
