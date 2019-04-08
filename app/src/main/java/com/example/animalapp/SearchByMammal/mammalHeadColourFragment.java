package com.example.animalapp.SearchByMammal;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.animalapp.R;

import java.util.ArrayList;

import androidx.navigation.Navigation;

/**
 * A simple {@link Fragment} subclass.
 */
public class mammalHeadColourFragment extends Fragment implements View.OnClickListener {


    public mammalHeadColourFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mammal_head_colour, container, false);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.filter_view);
        if (bundle != null) {
            StringBuilder filter = new StringBuilder();
            if (bundle.containsKey("SpeciesType")) {
                String type = bundle.getString("SpeciesType");
                Log.d("TYPE", type);
                filter.append("Type").append(": ").append(type).append(". ");
            }
            if (bundle.containsKey("MammalHeight")){
                ArrayList<Integer> passedHeight = bundle.getIntegerArrayList("MammalHeight");
                if (passedHeight.size() > 2){
                    Log.d("Mammal HEIGHT ", Integer.toString(passedHeight.get(0)) + ", " + Integer.toString(passedHeight.get(1)));
                    filter.append("Height").append(": >").append(passedHeight.get(0)).append(", <").append(passedHeight.get(1)).append(". ");
                } else {
                    if (passedHeight.get(1).equals(0)) {
                        Log.d("Mammal HEIGHT ", Integer.toString(passedHeight.get(0)));
                        filter.append("Mammal Height").append(": <").append(passedHeight.get(0)).append(". ");
                    } else {
                        Log.d("Mammal HEIGHT ", Integer.toString(passedHeight.get(0)));
                        filter.append("Mammal Height").append(": >").append(passedHeight.get(0)).append(". ");
                    }
                }
            }if (bundle.containsKey("MammalHeadColour")) {
                ArrayList<String> passedColour = bundle.getStringArrayList("MammalHeadColour");
                Log.d("Mammal HEAD COLOUR", passedColour.toString());
                filter.append("Head").append(": ").append(String.join(", ", passedColour)).append(". ");
            }if (bundle.containsKey("MammalFurColour")) {
                ArrayList<String>  passedColour = bundle.getStringArrayList("MammalFurColour");
                Log.d("Mammal Fur COLOUR", passedColour.toString());
                filter.append("Fur").append(": ").append(String.join(", ", passedColour)).append(". ");
            }
            passed_detail.setText("Filter: " + filter);
        }

        Button mammal_colour_option_black = (Button) view.findViewById(R.id.mammal_colour_option_black);
        Button mammal_colour_option_white = (Button) view.findViewById(R.id.mammal_colour_option_white);
        Button mammal_colour_option_brown = (Button) view.findViewById(R.id.mammal_colour_option_brown);

        Button mammal_colour_option_dark_brown = (Button) view.findViewById(R.id.mammal_colour_option_dark_brown);
        Button mammal_colour_option_light_brown = (Button) view.findViewById(R.id.mammal_colour_option_light_brown);
        Button mammal_colour_option_red = (Button) view.findViewById(R.id.mammal_colour_option_red);


        mammal_colour_option_black.setOnClickListener(this);
        mammal_colour_option_white.setOnClickListener(this);
        mammal_colour_option_brown.setOnClickListener(this);
        mammal_colour_option_dark_brown.setOnClickListener(this);
        mammal_colour_option_light_brown.setOnClickListener(this);
        mammal_colour_option_red.setOnClickListener(this);



        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        Button species_skip_btn = (Button) view.findViewById(R.id.species_skip_button);
        species_back_btn.setOnClickListener(this);
        species_skip_btn.setOnClickListener(this);
        return view;
    }
    public void setHeadColour(ArrayList<String> values, Bundle bundle, View view){
        bundle.putStringArrayList("MammalHeadColour", values);
        Navigation.findNavController(view).navigate(R.id.action_mammalHeadColourFragment_to_mammalFurColourFragment,bundle);

    }

    @Override
    public void onClick(View v) {
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            Log.i("NULL BUNDLE", "NOT WORKED");
            bundle = new Bundle();
        }

        int i = v.getId();
        if (i == R.id.mammal_colour_option_black) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Black"));
            setHeadColour(colours, bundle, v);

        } if (i == R.id.mammal_colour_option_white) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("White"));
            setHeadColour(colours, bundle, v);

        } if (i == R.id.mammal_colour_option_brown) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Brown"));
            setHeadColour(colours, bundle, v);

        } if (i == R.id.mammal_colour_option_dark_brown) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Dark Brown"));
            setHeadColour(colours, bundle, v);

        } if (i == R.id.mammal_colour_option_light_brown) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Light Brown"));
            setHeadColour(colours, bundle, v);

        } if (i == R.id.mammal_colour_option_red) {
            ArrayList<String> colours = new ArrayList<>();
            colours.add(("Red"));
            setHeadColour(colours, bundle, v);
        }
        if (i == R.id.species_back_button) {
            Navigation.findNavController(v).navigate(R.id.action_mammalHeadColourFragment_to_mammalHeightFragment,bundle);
        }
        if (i == R.id.species_skip_button) {
            bundle.remove("MammalHeadColour");
            Navigation.findNavController(v).navigate(R.id.action_mammalHeadColourFragment_to_mammalFurColourFragment,bundle);
        }

    }

}
