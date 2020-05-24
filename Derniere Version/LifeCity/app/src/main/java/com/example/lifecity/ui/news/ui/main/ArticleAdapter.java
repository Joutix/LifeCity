package com.example.lifecity.ui.news.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lifecity.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

public class ArticleAdapter extends ArrayAdapter<Article> {
    public ArticleAdapter(Context context, ArrayList<Article> articles) {
        super(context,0,articles);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Article article =getItem(position);

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.article_view,parent,false);
        }

        //lookup view for data population
        TextView tvName = (TextView)convertView.findViewById(R.id.tvArticleName);
        TextView tvDescription = (TextView)convertView.findViewById(R.id.tvDescription);
        ImageView ivCat = (ImageView)convertView.findViewById(R.id.ivArticle);

        //populate into template view from data source
        tvName.setText(article.getTitreArticle());
        tvDescription.setText(article.getDescArticle());
        Glide.with(getContext()).load(article.getUrlImage()).into(ivCat);

        return convertView;


    }

}
