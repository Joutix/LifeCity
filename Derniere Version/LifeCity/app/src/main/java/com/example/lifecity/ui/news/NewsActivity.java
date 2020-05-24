package com.example.lifecity.ui.news;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
//import com.example.lifecity.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lifecity.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    ScrollView scrollview;
    LinearLayout linearLayout;
    TextView textView1;
     TextView textView11;
     ImageView imageView1;
     TextView textView2;
     TextView textView21;
     ImageView imageView2;
     TextView textView3;
     TextView textView31;
    ImageView imageView3;

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
      /*  scrollview = findViewById(R.id.scoll);
        linearLayout = findViewById(R.id.linear);
        textView1 = findViewById(R.id.textView3);
        textView11 = findViewById(R.id.textView4);
        textView2 = findViewById(R.id.textView5);
        textView21 = findViewById(R.id.textView6);
        textView3 = findViewById(R.id.textView7);
        textView31 = findViewById(R.id.textView8);
        imageView1 = findViewById(R.id.imageView3);
        imageView2 = findViewById(R.id.imageView4);
        imageView3 = findViewById(R.id.imageView5);*/

        final int[] nbArticle = {0};
       // mTextViewResult = findViewById(R.id.text_view_result);
        mQueue = Volley.newRequestQueue(this);

        final String url = "https://newsapi.org/v2/top-headlines?country=fr&apiKey=c42d8060d2dc46c8bde71e3578dcb742";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONArray jsonArray = response.getJSONArray("articles");
                    nbArticle[0] = jsonArray.length();
                    for(int i=0; i<3; i++){
                        JSONObject article = jsonArray.getJSONObject(i);
                        JSONObject site = article.getJSONObject("source");
                        String nomSite = site.getString("name");
                        String urlImage = article.getString("urlToImage");
                        String urlArticle = article.getString("url");
                        if(i==1){
                            textView1.setText(nomSite);
                            textView11.setText(urlArticle);
                            Picasso.with(NewsActivity.this).load(urlArticle).into(imageView1);
                        }
                        else if(i==2){
                            textView2.setText(nomSite);
                            textView21.setText(urlArticle);
                            Picasso.with(NewsActivity.this).load(urlArticle).into(imageView2);
                        }
                        else{
                            textView3.setText(nomSite);
                            textView31.setText(urlArticle);
                            Picasso.with(NewsActivity.this).load(urlArticle).into(imageView3);
                        }



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);



    }


}
