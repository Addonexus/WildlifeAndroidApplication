package com.example.animalapp.SearchByMammal;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animalapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class mammalHeadColourFragment extends Fragment {


    public mammalHeadColourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mammal_head_colour, container, false);
    }

}
