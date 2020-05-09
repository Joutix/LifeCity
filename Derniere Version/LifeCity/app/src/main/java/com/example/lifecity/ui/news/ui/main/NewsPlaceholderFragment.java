package com.example.lifecity.ui.news.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.lifecity.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewsPlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private int index;
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

    private NewsPageViewModel newsPageViewModel;

    public static NewsPlaceholderFragment newInstance(int index) {
        NewsPlaceholderFragment fragment = new NewsPlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }



    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_news, container, false);
        System.out.println(index);
        switch(index){

            case 2://Actu

                scrollview = root.findViewById(R.id.scoll);
                linearLayout = root.findViewById(R.id.linear);
                textView1 = root.findViewById(R.id.textView3);
                textView11 = root.findViewById(R.id.textView4);
                textView2 = root.findViewById(R.id.textView5);
                textView21 = root.findViewById(R.id.textView6);
                textView3 = root.findViewById(R.id.textView7);
                textView31 = root.findViewById(R.id.textView8);
                imageView1 = root.findViewById(R.id.imageView3);
                imageView2 = root.findViewById(R.id.imageView4);
                imageView3 = root.findViewById(R.id.imageView5);

                final int[] nbArticle = {0};
                mTextViewResult = root.findViewById(R.id.text_view_result);
                mQueue = Volley.newRequestQueue(getActivity());

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
                                    Picasso.with(getActivity()).load(urlArticle).into(imageView1);
                                }
                                else if(i==2){
                                    textView2.setText(nomSite);
                                    textView21.setText(urlArticle);
                                    Picasso.with(getActivity()).load(urlArticle).into(imageView2);
                                }
                                else{
                                    textView3.setText(nomSite);
                                    textView31.setText(urlArticle);
                                    Picasso.with(getActivity()).load(urlArticle).into(imageView3);
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


                break;


            default:
                return  null;




        }
        return root;
    }
}