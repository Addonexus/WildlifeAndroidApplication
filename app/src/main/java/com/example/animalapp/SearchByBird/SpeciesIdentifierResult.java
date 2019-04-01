package com.example.animalapp.SearchByBird;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;

import com.example.animalapp.R;
import com.example.animalapp.SpeciesIdentifier;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpeciesIdentifierResult extends Fragment implements View.OnClickListener {
    Bundle bundle = this.getArguments();
    int numberOfFilters = 0;
    ArrayList<String> filters = new ArrayList<>();
    ListView list;



    public SpeciesIdentifierResult() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_species_identifier_result, container, false);
        list = view.findViewById(R.id.result_list_view);
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
                numberOfFilters =+ 1;
                filters.add("SpeciesType");
                String type = bundle.getString("SpeciesType");
                Log.d("TYPE", type);
                filter.append("Type").append(": ").append(type).append(". ");
            }
            if (bundle.containsKey("BirdHeight")){
                filters.add("BirdHeight");
                ArrayList<Integer> passedHeight = bundle.getIntegerArrayList("BirdHeight");
                if (passedHeight.size() > 2){
                    Log.d("BIRD HEIGHT ", Integer.toString(passedHeight.get(0)) + ", " + Integer.toString(passedHeight.get(1)));
                    filter.append("Height").append(": >").append(passedHeight.get(0)).append(", <").append(passedHeight.get(1)).append(". ");
                } else {
                    Log.d("BIRD HEIGHT ", Integer.toString(passedHeight.get(0)));
                    filter.append("Bird Height").append(": ").append(passedHeight.get(0)).append(". ");}
            }if (bundle.containsKey("BirdHeadColour")) {
                filters.add("BirdHeadColour");
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
        ArrayList<Animal> resultAnimals = new ArrayList<>(searchUsingFilters());
        List<String> resultAnimalNames = new ArrayList<>();
        List<String> resultAnimalTypes = new ArrayList<>();
        List<String> resultAnimalScientificNouns = new ArrayList<>();
        for (Animal animal :
                resultAnimals) {
            resultAnimalNames.add(animal.getName());
            resultAnimalTypes.add(animal.getType());
            resultAnimalScientificNouns.add(animal.getScientificName());
        }
        CustomAdapter adapter = new CustomAdapter(getContext(), resultAnimalNames, resultAnimalTypes, resultAnimalScientificNouns);
        list.setAdapter(adapter);
        return view;
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    public ArrayList<Animal> searchByType(ArrayList<Animal> animalList, String type){
        Log.d("STARTING", "THIS PROCESS SEARCH BY TYPE HAS STARTED "+ type);
        ArrayList<Animal> resultList = new ArrayList<>();
        for (Animal animal :
                animalList) {
            if (animal.getType().equalsIgnoreCase(type)){
                resultList.add(animal);
                Log.d("MID", "THIS PROCESS HAS WORKED");
            }
        }
//        for (Animal animal: resultList){
//            Log.d("RSEULT ANIMAL", animal.toString());
//        }
        return resultList;
    }
    public ArrayList<Animal> searchBySize(ArrayList<Animal> animalList, List<Integer> sizesList){
        Log.d("STARTING", "THIS SEARCH BY SIZE HAS STARTED "+ sizesList);

        ArrayList<Animal> resultList = new ArrayList<>();
        for (Animal animal :
                animalList) {
            if (sizesList.size() > 2){
                if((sizesList.get(0) > Integer.parseInt(animal.getMinBodyLengthCm())) && (sizesList.get(1) < Integer.parseInt(animal.getMaxBodyLengthCm())) && (animal.getType().equalsIgnoreCase("Bird"))) {
                    resultList.add(animal);
                }
                Log.d("MID", "THIS PROCESS HAS WORKED");

            } else{
                if(sizesList.get(1).equals(0)){
                    if((sizesList.get(0) > Integer.parseInt(animal.getMinBodyLengthCm()))&& (animal.getType().equalsIgnoreCase("Bird"))){
                        resultList.add(animal);
                    }
                }
                if(sizesList.get(1).equals(1)){
                    if((sizesList.get(0) < Integer.parseInt(animal.getMaxBodyLengthCm())) && (animal.getType().equalsIgnoreCase("Bird"))){
                        resultList.add(animal);
                    }
                }

                Log.d("MID", "THIS PROCESS HAS WORKED");
            }
        }
//        for (Animal animal: resultList){
//            Log.d("RSEULT ANIMAL", animal.toString());
//        }
        return resultList;

    }
    public ArrayList<Animal> searchUsingFilters(){
        NavHostFragment navHostFragment = (NavHostFragment) getParentFragment();
        Fragment parent = (Fragment) navHostFragment.getParentFragment();
        parent.getView().findViewById(R.id.species_identifier_nav_graph);
//        Log.d("THINFY", parent.toString());
//        Log.d("WHO", navHostFragment.toString());

        AnimalDatabase db= ((SpeciesIdentifier)(parent)).getDb();

        ArrayList<Animal> allAnimals = new ArrayList<>(db.animalDAO().getAllAnimals());
        ArrayList<Animal> resultList = allAnimals;

        for (String filter: filters) {
            if (filter.equalsIgnoreCase("SpeciesType")){
                resultList = searchByType(resultList, getArguments().getString("SpeciesType"));
                Log.d("AFTER BY TYPE", resultList.size() + "");
            }
            if (filter.equalsIgnoreCase("BirdHeight")){
                resultList = searchBySize(resultList, getArguments().getIntegerArrayList("BirdHeight"));
                Log.d("AFTER BY SIZE", resultList.size() + "");
            }

        }
        for (Animal animal: resultList){
            Log.d("FINAL ANIMAL RESULT", animal.toString());
        }
        return resultList;

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

    class CustomAdapter extends ArrayAdapter<String> {
        Context context;
        List<String> names = new ArrayList<String>();
        List<String> types = new ArrayList<String>();
        List<String> scientificNouns = new ArrayList<String>();
//        int[] imgs;


        public CustomAdapter(Context context, List<String> names, List<String> types, List<String> scientificNouns) {
            super(context, R.layout.animal_lists_item, R.id.animal_name, names);

            this.context = context;
//            this.imgs = imgs;
            this.names = names;
            this.types = types;
            this.scientificNouns = scientificNouns;
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View animal_item = inflater.inflate(R.layout.animal_lists_item, parent, false);
//            ImageView images = reserve_item.findViewById(R.id.reserve_logo);
            TextView name = animal_item.findViewById(R.id.animal_name);
            TextView type = animal_item.findViewById(R.id.animal_type);
            TextView scientificNoun = animal_item.findViewById(R.id.animal_scientific_name);

//            images.setImageResource(imgs[position]);
            name.setText(names.get(position));
            type.setText(types.get(position));
            scientificNoun.setText(scientificNouns.get(position));

            return animal_item;
        }
    }
}


