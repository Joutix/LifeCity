package com.example.lifecity.ui.pub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lifecity.R;
import com.example.lifecity.ui.commerce.List.Commerce;
import com.example.lifecity.ui.commerce.List.CommerceListActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.InputStream;

public class PubActivity extends AppCompatActivity {

    private RecyclerView mFirestoreList;
    private FirebaseFirestore firebaseFirestore;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub);

        mFirestoreList = (RecyclerView)findViewById(R.id.firestore_list_pub);
        firebaseFirestore = FirebaseFirestore.getInstance();

        Query query = firebaseFirestore.collection("pub");

        FirestoreRecyclerOptions<Pub> options = new FirestoreRecyclerOptions.Builder<Pub>()
                .setQuery(query, Pub.class).build();
        System.out.println("Suite Pub...");

        adapter = new FirestoreRecyclerAdapter<Pub, PubActivity.ProductsViewHolder>(options) {
            @NonNull
            @Override
            public PubActivity.ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pub_single, parent, false);
                System.out.println("Nouveau pub: ");
                return new PubActivity.ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull PubActivity.ProductsViewHolder productsViewHolder, int i, @NonNull Pub pub) {

                productsViewHolder.list_nom.setText(pub.getNomPUB());
                productsViewHolder.list_desc.setText(pub.getDescription());
                Glide.with(getApplicationContext()).load(pub.getUrlImage()).into(productsViewHolder.list_image);
                //productsViewHolder.urlImage = commerce.getImage();
            }
        };
        System.out.println("Suite Pub...");
        // mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder{

        private TextView list_nom;
        private TextView list_desc;
        private ImageView list_image;

        public ProductsViewHolder(@NonNull View itemView){
            super(itemView);

            list_image = itemView.findViewById(R.id.imageCommerce2);
            list_nom = itemView.findViewById(R.id.textNom2);
            list_desc = itemView.findViewById(R.id.textDesc);

        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
}