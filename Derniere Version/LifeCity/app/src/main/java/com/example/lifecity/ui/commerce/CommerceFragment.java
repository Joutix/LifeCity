package com.example.lifecity.ui.commerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.lifecity.R;

public class CommerceFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //View root = inflater.inflate(R.layout.fragment_commerces, container, false);
        Intent intent=new Intent(getActivity(),MainCommerceActivity.class);
        startActivity((intent));
        return null;
    }
}