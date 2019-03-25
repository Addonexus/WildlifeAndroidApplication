package com.example.animalapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BirdHeightFragment extends Fragment {


    public BirdHeightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bird_height, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.filter_view);
        if (bundle != null) {
            ArrayList<String> filterItems = bundle.getStringArrayList("filter");
            StringBuilder filter = new StringBuilder();
            for (String filterItem :
                    filterItems) {
                List<String> splitFilterItem = Arrays.asList(filterItem.split(":"));
                filter.append(splitFilterItem.get(0)).append(",").append(splitFilterItem.get(1));
            }
            passed_detail.setText("Filter: " + filter);
        }


        return view;
    }

}
