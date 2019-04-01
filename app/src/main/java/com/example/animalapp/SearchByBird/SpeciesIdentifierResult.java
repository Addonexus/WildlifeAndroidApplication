package com.example.animalapp.SearchByBird;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;
import com.example.animalapp.R;
import com.example.animalapp.SpeciesIdentifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpeciesIdentifierResult extends Fragment implements View.OnClickListener {



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

        AppCompatButton initialiseButton = view.findViewById(R.id.initialise);
        initialiseButton.setOnClickListener(this);
        AppCompatButton animalButton = view.findViewById(R.id.animal);
        animalButton.setOnClickListener(this);

        Button species_back_btn = (Button) view.findViewById(R.id.species_back_button);
        species_back_btn.setOnClickListener(this);


        if (bundle != null) {
            StringBuilder filter = new StringBuilder();
            if (bundle.containsKey("SpeciesType")) {
                String type = bundle.getString("SpeciesType");
                Log.d("TYPE", type);
                filter.append("Type").append(": ").append(type).append(". ");
            }
            if (bundle.containsKey("BirdHeight")){
                ArrayList<Integer> passedHeight = bundle.getIntegerArrayList("BirdHeight");
                if (passedHeight.size() > 1){
                    Log.d("BIRD HEIGHT ", Integer.toString(passedHeight.get(0)) + ", " + Integer.toString(passedHeight.get(1)));
                    filter.append("Height").append(": >").append(passedHeight.get(0)).append(", <").append(passedHeight.get(1)).append(". ");
                } else {
                    Log.d("BIRD HEIGHT ", Integer.toString(passedHeight.get(0)));
                    filter.append("Bird Height").append(": ").append(passedHeight.get(0)).append(". ");}
            }if (bundle.containsKey("BirdHeadColour")) {
                String passedColour = bundle.getString("BirdHeadColour");
                Log.d("BIRD HEAD COLOUR", passedColour);
                filter.append("Head").append(": ").append(passedColour).append(". ");
            }
//            filterItems = bundle.getStringArrayList("filter");
//
//
//            for (String filterItem :
//                    filterItems) {
//                Log.i("PASSED VALUES: ", filterItem);
//                List<String> splitFilterItem = Arrays.asList(filterItem.split(":"));
//                filter.append(splitFilterItem.get(0)).append(":").append(splitFilterItem.get(1)).append(" ");
//            }
            passed_detail.setText("Filter: " + filter);
        }
        return view;
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
//        Fragment parentFragment = getParentFragment();
//        Log.d("THINGS", parentFragment.toString());
//        Log.d("CHECKING", Boolean.toString(parentFragment instanceof ChooseSpecies));
//        Log.d("WHAT", getActivity().toString());
        NavHostFragment navHostFragment = (NavHostFragment) getParentFragment();
        Fragment parent = (Fragment) navHostFragment.getParentFragment();
        parent.getView().findViewById(R.id.species_identifier_nav_graph);
//        Log.d("THINFY", parent.toString());
//        Log.d("WHO", navHostFragment.toString());

        AnimalDatabase db= ((SpeciesIdentifier)(parent)).getDb();
        List<Animal> allAnimals = db.animalDAO().getAllAnimals();
        final ArrayList<Animal> resultList = new ArrayList<>(allAnimals);
        switch (viewId) {

            case R.id.initialise:
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
//                        Log.d("initialised","animals" + allanimals);
                        for (Animal list : resultList) {
                            if (list.getName().equalsIgnoreCase("pintail")) {
                                Log.d("Id 2", "Animal 2" + list);
                            }
                        }
                    }
                });
                break;
            case R.id.animal:
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (Animal list : resultList) {
                            if (list.getMinBodyLengthCm().equals("44")) {
                                Log.d("ID ANIMAL", "ANIMAL WING 44" + list);
                            }
                        }
                    }
                });
                break;
            case R.id.species_back_button:
                Navigation.findNavController(v).navigate(R.id.action_speciesIdentifierResult_to_birdHeadColourFragment, this.getArguments());

                break;

        }

    }
}


