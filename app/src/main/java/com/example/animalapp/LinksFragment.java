package com.example.animalapp;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class LinksFragment extends Fragment {


    public LinksFragment() {
        /* Required empty public constructor */
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.title_external_links);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_links, container, false);
    }

    /*private void goToUrl (View view) {
        String url = "http://www.google.com";
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }*/
}
