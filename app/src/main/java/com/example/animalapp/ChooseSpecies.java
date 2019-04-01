package com.example.animalapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import androidx.navigation.Navigation;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChooseSpecies extends Fragment implements View.OnClickListener {


    public ChooseSpecies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_choose_species, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            bundle.remove("BirdHeight");
            bundle.remove("BirdColour");
            bundle.remove("SpeciesType");

        }
        Button species_bird_btn = (Button) view.findViewById(R.id.species_bird_button);
        Button species_mammal_btn = (Button) view.findViewById(R.id.species_mammal_button);
        Button species_reptile_btn = (Button) view.findViewById(R.id.species_reptile_button);
        Button species_invertebrate_btn = (Button) view.findViewById(R.id.species_invertebrate_button);
        species_bird_btn.setOnClickListener(this);
        species_mammal_btn.setOnClickListener(this);
        species_reptile_btn.setOnClickListener(this);
        species_invertebrate_btn.setOnClickListener(this);


        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        Button species_skip_btn = (Button) view.findViewById(R.id.species_skip_button);
        species_back_btn.setOnClickListener(this);
        species_skip_btn.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {
        ArrayList<ArrayList<String>> filter = new ArrayList<>();
        ArrayList<String> filterItems = new ArrayList<>();
        Bundle bundle = new Bundle();
        int i = v.getId();
        if (i == R.id.species_bird_button) {
            filterItems.add("Type" + ":" + "Bird");

            bundle.putStringArrayList("filter", filterItems);
            bundle.putString("SpeciesType", "Bird");
            Navigation.findNavController(v).navigate(R.id.action_chooseSpecies_to_birdHeightFragment,bundle);

        }
        if (i == R.id.species_back_button) {
            SpeciesIdentifier fragment = new SpeciesIdentifier();
            replaceFragment(new SpeciesIdentifier());
        }
        if (i == R.id.species_skip_button) {
            bundle.remove("SpeciesType");
            Navigation.findNavController(v).navigate(R.id.action_chooseSpecies_to_birdHeadColourFragment,bundle);
        }
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, someFragment);
        fragmentTransaction.commit();
    }
}
