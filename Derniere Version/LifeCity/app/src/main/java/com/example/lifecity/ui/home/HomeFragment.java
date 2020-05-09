package com.example.lifecity.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lifecity.R;
import com.example.lifecity.ui.commerce.MainCommerceActivity;
import com.example.lifecity.ui.news.MainNewsActivity;
import com.example.lifecity.ui.reseaux.MainReseauxActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private CardView commerceCard, newsCard, reseauxCard, pubCard;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        commerceCard = (CardView) root.findViewById(R.id.card_Commerce);
        newsCard = (CardView) root.findViewById(R.id.card_News);
        reseauxCard = (CardView) root.findViewById(R.id.card_Reseaux);
        pubCard = (CardView) root.findViewById(R.id.card_Publicites);
        //On ajoute les ClickListener
        System.out.println(commerceCard);
        commerceCard.setOnClickListener(this);
        newsCard.setOnClickListener(this);
        reseauxCard.setOnClickListener(this);
        pubCard.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch(v.getId()) {
            case R.id.card_Commerce:
                i = new Intent(getActivity(), MainCommerceActivity.class);
                startActivity(i);
                break;
            case R.id.card_News:
                i = new Intent(getActivity(), MainNewsActivity.class);
                startActivity(i);
                break;
            case R.id.card_Reseaux:
                i = new Intent(getActivity(), MainReseauxActivity.class);
                startActivity(i);
                break;
            /*case R.id.card_Publicites:
                i = new Intent(this, PubActivity.class);
                startActivity(i);
                break;*/
            default:
                break;

        }
    }

}