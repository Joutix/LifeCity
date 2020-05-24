package com.example.lifecity.ui.news.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.AdapterView.*;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

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

                final int[] nbArticle = {0};
               // mTextViewResult = root.findViewById(R.id.text_view_result);
                mQueue = Volley.newRequestQueue(getActivity());

                final String url = "https://newsapi.org/v2/top-headlines?country=fr&apiKey=c42d8060d2dc46c8bde71e3578dcb742";


                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            final List<Article> articleList=new ArrayList<>();
                            ListView listView=(ListView)getActivity().findViewById(R.id.lvArticle);
                            ArticleAdapter adapter=new ArticleAdapter(getContext(),(ArrayList<Article>)articleList);
                            listView.setAdapter(adapter);




                            JSONArray jsonArray = response.getJSONArray("articles");
                            nbArticle[0] = jsonArray.length();

                            for(int i=0; i<10; i++){
                                JSONObject article = jsonArray.getJSONObject(i);
                                JSONObject site = article.getJSONObject("source");

                                String titreArticle = article.getString("title");
                                String descArticle = article.getString("description");

                                String nomSite = site.getString("name");
                                String urlImage = article.getString("urlToImage");
                                String urlArticle = article.getString("url");


                                Article art=new Article(titreArticle,descArticle,nomSite,urlImage,urlArticle);
                                articleList.add(art);


                            }
                            listView.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(articleList.get(position).getUrlArticle()));
                                    startActivity(i);
                                }
                            });
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