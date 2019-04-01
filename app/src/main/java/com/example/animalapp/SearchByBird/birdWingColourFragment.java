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
public class birdWingColourFragment extends Fragment implements View.OnClickListener{
    ArrayList<String> filterItems = new ArrayList<>();


    public birdWingColourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bird_wing_colour, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.filter_view);
        if (bundle != null) {
            StringBuilder filter = new StringBuilder();
            if (bundle.containsKey("SpeciesType")) {
                String type = bundle.getString("SpeciesType");
                Log.d("TYPE", type);
                filter.append("Type").append(": ").append(type).append(". ");
            }
            if (bundle.containsKey("BirdHeight")){
                ArrayList<Integer> passedHeight = bundle.getIntegerArrayList("BirdHeight");
                if (passedHeight.size() > 2){
                    Log.d("BIRD HEIGHT ", Integer.toString(passedHeight.get(0)) + ", " + Integer.toString(passedHeight.get(1)));
                    filter.append("Height").append(": >").append(passedHeight.get(0)).append(", <").append(passedHeight.get(1)).append(". ");
                } else {
                    Log.d("BIRD HEIGHT ", Integer.toString(passedHeight.get(0)));
                    filter.append("Bird Height").append(": ").append(passedHeight.get(0)).append(". ");}
            }if (bundle.containsKey("BirdHeadColour")) {
                String passedColour = bundle.getString("BirdHeadColour");
                Log.d("BIRD HEAD COLOUR", passedColour);
                filter.append("Head").append(": ").append(passedColour).append(". ");
            }if (bundle.containsKey("BirdWingColour")) {
                String passedColour = bundle.getString("BirdWingColour");
                Log.d("BIRD WING COLOUR", passedColour);
                filter.append("Wing").append(": ").append(passedColour).append(". ");
            }if (bundle.containsKey("BirdBellyColour")) {
                String passedColour = bundle.getString("BirdBellyColour");
                Log.d("BIRD BELLY COLOUR", passedColour);
                filter.append("Belly").append(": ").append(passedColour).append(". ");
            }

            passed_detail.setText("Filter: " + filter);
        }

        Button bird_colour_option_white = (Button) view.findViewById(R.id.bird_colour_option_white);
        Button bird_colour_option_yellow = (Button) view.findViewById(R.id.bird_colour_option_yellow);
        Button bird_colour_option_grey = (Button) view.findViewById(R.id.bird_colour_option_grey);

        Button bird_colour_option_red = (Button) view.findViewById(R.id.bird_colour_option_red);
        Button bird_colour_option_blue = (Button) view.findViewById(R.id.bird_colour_option_blue);
        Button bird_colour_option_black = (Button) view.findViewById(R.id.bird_colour_option_black);

        bird_colour_option_white.setOnClickListener(this);
        bird_colour_option_yellow.setOnClickListener(this);
        bird_colour_option_grey.setOnClickListener(this);
        bird_colour_option_red.setOnClickListener(this);
        bird_colour_option_blue.setOnClickListener(this);
        bird_colour_option_black.setOnClickListener(this);

        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        Button species_skip_btn = (Button) view.findViewById(R.id.species_skip_button);
        species_back_btn.setOnClickListener(this);
        species_skip_btn.setOnClickListener(this);
        return view;
    }
    public Bundle setWingColour(String value, Bundle bundle){
        bundle.putString("BirdWingColour", value);
        return bundle;

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
            setWingColour("White", bundle);
            Navigation.findNavController(v).navigate(R.id.action_birdWingColourFragment_to_birdBellyColourFragment,bundle);

        } if (i == R.id.bird_colour_option_yellow) {

            setWingColour("Yellow", bundle);
            Navigation.findNavController(v).navigate(R.id.action_birdWingColourFragment_to_birdBellyColourFragment,bundle);

        } if (i == R.id.bird_colour_option_grey) {

            setWingColour("Grey", bundle);
            Navigation.findNavController(v).navigate(R.id.action_birdWingColourFragment_to_birdBellyColourFragment,bundle);

        } if (i == R.id.bird_colour_option_red) {

            setWingColour("Red", bundle);
            Navigation.findNavController(v).navigate(R.id.action_birdWingColourFragment_to_birdBellyColourFragment,bundle);

        } if (i == R.id.bird_colour_option_blue) {

            setWingColour("Blue", bundle);
            Navigation.findNavController(v).navigate(R.id.action_birdWingColourFragment_to_birdBellyColourFragment,bundle);

        } if (i == R.id.bird_colour_option_black) {

            setWingColour("Black", bundle);
            Navigation.findNavController(v).navigate(R.id.action_birdWingColourFragment_to_birdBellyColourFragment,bundle);

        }
        if (i == R.id.species_back_button) {
//            bundle.remove("BirdHeadColour");
            Navigation.findNavController(v).navigate(R.id.action_birdWingColourFragment_to_birdHeadColourFragment,bundle);
        }
        if (i == R.id.species_skip_button) {
            bundle.remove("BirdWingColour");
            Navigation.findNavController(v).navigate(R.id.action_birdWingColourFragment_to_birdBellyColourFragment,bundle);
        }

    }

}

