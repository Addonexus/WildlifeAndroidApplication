package com.example.animalapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
public class SpeciesIdentifierResult extends Fragment {
    ArrayList<String> filterItems = new ArrayList<>();


    public SpeciesIdentifierResult() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_species_identifier_result, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.species_identifier_result_text);
        if (bundle != null) {
            filterItems = bundle.getStringArrayList("filter");

            StringBuilder filter = new StringBuilder();
            for (String filterItem :
                    filterItems) {
                Log.i("PASSED VALUES: ", filterItem);
                List<String> splitFilterItem = Arrays.asList(filterItem.split(":"));
                filter.append(splitFilterItem.get(0)).append(":").append(splitFilterItem.get(1)).append(" ");
            }
            passed_detail.setText("Filter: " + filter);
        }
        return view;
    }

}
