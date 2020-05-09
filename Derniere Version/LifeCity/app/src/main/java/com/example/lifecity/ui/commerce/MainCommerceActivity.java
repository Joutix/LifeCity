package com.example.lifecity.ui.commerce;

import android.content.Intent;
import android.os.Bundle;

import com.example.lifecity.ui.commerce.List.CommerceListActivity;
import com.example.lifecity.ui.commerce.Offre.CommercialOffreActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.view.View;

import com.example.lifecity.R;

public class MainCommerceActivity extends AppCompatActivity implements View.OnClickListener{
    private CardView commerceListCard, commerceOfferCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_commerce);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        commerceListCard = (CardView) findViewById(R.id.card_CommerceList);
        commerceOfferCard = (CardView) findViewById(R.id.card_CommerceOffre);

        commerceListCard.setOnClickListener(this);
        commerceOfferCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId()){
            case R.id.card_CommerceList :
                i = new Intent(this, CommerceListActivity.class);
                startActivity(i);
                break;
            case R.id.card_CommerceOffre :
                i = new Intent(this, CommercialOffreActivity.class);
                startActivity(i);
                break;
            default :
                break;

        }
    }
}
