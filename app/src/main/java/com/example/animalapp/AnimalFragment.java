package com.example.animalapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalFragment extends Fragment implements View.OnClickListener{


    public AnimalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle(R.string.title_animal_menu);
        View view = inflater.inflate(R.layout.fragment_animal_dictionary, container, false);
        Button species_identifier_btn = (Button) view.findViewById(R.id.species_identifier_button);
        species_identifier_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        int i = v.getId();
        if (i == R.id.species_identifier_button) {
            fragment = new SpeciesIdentifier();
            replaceFragment(fragment);

        }
    }
    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
