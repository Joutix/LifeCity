package com.example.lifecity.ui.reseaux.ui.main;

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

/**
 * A placeholder fragment containing a simple view.
 */
public class ReseauxPlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private ReseauxPageViewModel reseauxPageViewModel;

    public static ReseauxPlaceholderFragment newInstance(int index) {
        ReseauxPlaceholderFragment fragment = new ReseauxPlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reseauxPageViewModel = ViewModelProviders.of(this).get(ReseauxPageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        reseauxPageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_reseaux, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        reseauxPageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}