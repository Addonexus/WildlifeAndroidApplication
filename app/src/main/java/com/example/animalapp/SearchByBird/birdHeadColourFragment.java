package com.example.animalapp.SearchByBird;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.animalapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class birdHeadColourFragment extends Fragment implements View.OnClickListener{
    ArrayList<String> filterItems = new ArrayList<>();


    public birdHeadColourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bird_head_colour, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.filter_view);
        if (bundle != null) {
            filterItems = bundle.getStringArrayList("filter");
            StringBuilder filter = new StringBuilder();
            for (String filterItem :
                    filterItems) {
                Log.i("PASSED VALUES: ", filterItem);
                List<String> splitFilterItem = Arrays.asList(filterItem.split(":"));
                filter.append(splitFilterItem.get(0)).append(":").append(splitFilterItem.get(1));
            }
            passed_detail.setText("Filter: " + filter);
        }

        Button bird_colour_option_white = (Button) view.findViewById(R.id.bird_colour_option_white);
        Button bird_colour_option_yellow = (Button) view.findViewById(R.id.bird_colour_option_yellow);
        Button bird_colour_option_grey = (Button) view.findViewById(R.id.bird_colour_option_grey);

        bird_colour_option_white.setOnClickListener(this);
        bird_colour_option_yellow.setOnClickListener(this);
        bird_colour_option_grey.setOnClickListener(this);

        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        Button species_skip_btn = (Button) view.findViewById(R.id.species_skip_button);
        species_back_btn.setOnClickListener(this);
        species_skip_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            Log.i("NULL BUNDLE", "NOT WORKED");
            bundle = new Bundle();
        }

        int i = v.getId();
        if (i == R.id.bird_colour_option_white) {
            filterItems.add("Colour" + ":" + "White");
            bundle.putStringArrayList("filter", filterItems);
            Navigation.findNavController(v).navigate(R.id.action_birdHeadColourFragment_to_speciesIdentifierResult,bundle);

        }else if (i == R.id.bird_colour_option_yellow) {
            filterItems.add("Colour" + ":" + "Yellow");
            bundle.putStringArrayList("filter", filterItems);
            Navigation.findNavController(v).navigate(R.id.action_birdHeadColourFragment_to_speciesIdentifierResult,bundle);

        }else if (i == R.id.bird_colour_option_grey) {
            filterItems.add("Colour" + ":" + "Grey");
            bundle.putStringArrayList("filter", filterItems);
            Navigation.findNavController(v).navigate(R.id.action_birdHeadColourFragment_to_speciesIdentifierResult,bundle);

        }
        if (i == R.id.species_back_button) {
            bundle.remove("BirdHeadColour");
            Navigation.findNavController(v).navigate(R.id.action_birdHeadColourFragment_to_birdHeightFragment,bundle);
        }
        if (i == R.id.species_skip_button) {
            bundle.remove("BirdHeadColour");
            Navigation.findNavController(v).navigate(R.id.action_birdHeightFragment_to_speciesIdentifierResult,bundle);
        }

    }

}

