package com.example.lifecity.ui.commerce.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lifecity.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.InputStream;

public class CommerceListActivity extends AppCompatActivity {

    private RecyclerView mFirestoreList;
    private FirebaseFirestore firebaseFirestore;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce_list);

        mFirestoreList = (RecyclerView)findViewById(R.id.firestore_list);
        firebaseFirestore = FirebaseFirestore.getInstance();

        Query query = firebaseFirestore.collection("commerce");

        FirestoreRecyclerOptions<Commerce> options = new FirestoreRecyclerOptions.Builder<Commerce>()
                .setQuery(query, Commerce.class).build();
        System.out.println("Suite1...");

        adapter = new FirestoreRecyclerAdapter<Commerce, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_commerce_single, parent, false);
                System.out.println("Nouveau commerce: ");
                return new ProductsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder productsViewHolder, int i, @NonNull Commerce commerce) {

                productsViewHolder.list_nom.setText(commerce.getNom());
                productsViewHolder.list_adresse.setText(commerce.getAdresse());
                productsViewHolder.list_ville.setText(commerce.getVille());
                Glide.with(getApplicationContext()).load(commerce.getImage()).into(productsViewHolder.list_image);
                //productsViewHolder.urlImage = commerce.getImage();
            }
        };
        System.out.println("Suite2...");
       // mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);
    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder{

        private TextView list_nom;
        private TextView list_ville;
        private TextView list_adresse;
        private ImageView list_image;

        public ProductsViewHolder(@NonNull View itemView){
            super(itemView);

            list_image = itemView.findViewById(R.id.imageCommerce);
            list_nom = itemView.findViewById(R.id.textNom);
            list_ville = itemView.findViewById(R.id.textVille);
            list_adresse = itemView.findViewById(R.id.textAdresse);

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
