package com.example.lifecity.ui.news.ui.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.AdapterView.*;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        View root = null;
        System.out.println(index);
        switch(index){

            case 2://Actu
                root=inflater.inflate(R.layout.activity_news, container, false);
                final int[] nbArticle = {0};

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

            case 4:

                root=inflater.inflate(R.layout.alarm_layout, container, false);
                Button button=(Button) root.findViewById(R.id.alarm_button);
                System.out.println("Button ="+button);
                if(button!=null)
                button.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View view) {
                        TimePicker tp=(TimePicker)getActivity().findViewById(R.id.alarm_picker);
                        AlarmManager am =( AlarmManager)getContext().getSystemService(Context.ALARM_SERVICE);
                        Intent i = new Intent(getActivity().getApplicationContext(),Alarm.class);
                        PendingIntent pi = PendingIntent.getActivity(getContext(), 101, i, PendingIntent.FLAG_UPDATE_CURRENT);
                        

                        Date date = new Date();   // given date
                        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
                        calendar.setTime(date);   // assigns calendar to given date
                        System.out.println(calendar.get(Calendar.HOUR_OF_DAY)+calendar.get(Calendar.MINUTE)); // gets hour in 24h format
                        int deltaH=(tp.getHour()-calendar.get(Calendar.HOUR_OF_DAY));
                        int deltaM=(tp.getMinute()-calendar.get(Calendar.MINUTE));
                        int deltaTotalInSec=3600*deltaH+60*deltaM;

                        System.out.println("Alarm set on "+tp.getHour()+" "+tp.getMinute()+"sonne dans"+deltaTotalInSec);
                        am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() +  deltaTotalInSec * 1000,pi);

                    }
                });


                break;
            default:
                return  null;




        }
        return root;
    }
}