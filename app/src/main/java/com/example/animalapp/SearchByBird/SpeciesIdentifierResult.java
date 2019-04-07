package com.example.animalapp.SearchByBird;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;

import com.example.animalapp.R;
import com.example.animalapp.SpeciesIdentifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_species_identifier_result, container, false);
        list = view.findViewById(R.id.result_list_view);
        Bundle bundle = this.getArguments();
        TextView passed_detail = view.findViewById(R.id.species_identifier_result_text);


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
                    if (passedHeight.get(1).equals(0)) {
                        Log.d("BIRD HEIGHT ", Integer.toString(passedHeight.get(0)));
                        filter.append("Bird Height").append(": >").append(passedHeight.get(0)).append(". ");
                    } else {
                        Log.d("BIRD HEIGHT ", Integer.toString(passedHeight.get(0)));
                        filter.append("Bird Height").append(": <").append(passedHeight.get(0)).append(". ");
                    }
                }
            }if (bundle.containsKey("BirdHeadColour")) {
                filters.add("BirdHeadColour");
                ArrayList<String> passedColour = bundle.getStringArrayList("BirdHeadColour");
                Log.d("BIRD HEAD COLOUR", passedColour.toString());
                filter.append("Head").append(": ").append(String.join(", ", passedColour)).append(". ");

            }if (bundle.containsKey("BirdWingColour")) {
                filters.add("BirdWingColour");
                ArrayList<String>  passedColour = bundle.getStringArrayList("BirdWingColour");
                Log.d("BIRD WING COLOUR", passedColour.toString());
                filter.append("Wing").append(": ").append(String.join(", ", passedColour)).append(". ");
            }if (bundle.containsKey("BirdBellyColour")) {
                filters.add("BirdBellyColour");
                ArrayList<String> passedColour = bundle.getStringArrayList("BirdBellyColour");
                Log.d("BIRD BELLY COLOUR", passedColour.toString());
                filter.append("Belly").append(": ").append(String.join(", ", passedColour)).append(". ");

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
        if (!resultAnimals.isEmpty()) {
            TextView error_result = (TextView) view.findViewById(R.id.result_message);
            error_result.setText("Possible Animals Found From Filter(s)");
            List<String> resultAnimalNames = new ArrayList<>();
            List<String> resultAnimalTypes = new ArrayList<>();
            List<String> resultAnimalScientificNouns = new ArrayList<>();
            List<String> resultAnimalImages = new ArrayList<>();
            for (Animal animal :
                    resultAnimals) {
                resultAnimalNames.add(animal.getName());
                resultAnimalTypes.add(animal.getType());
                resultAnimalScientificNouns.add(animal.getScientificName());
                resultAnimalImages.add(animal.getAnimalImage());
            }
            CustomAdapter adapter = new CustomAdapter(getContext(), resultAnimalNames, resultAnimalTypes, resultAnimalScientificNouns, resultAnimalImages);
            list.setAdapter(adapter);
        }else {
            TextView error_result = (TextView) view.findViewById(R.id.result_message);
            error_result.setText("No Animals Of the Specified Filter(s) Could be Found");
        }

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
    public ArrayList<Animal> searchByBellyColour(ArrayList<Animal> animalList, List<String> bellyColours){
        ArrayList<Animal> resultList = new ArrayList<>();
        Log.d("STARTING", "THIS SEARCH BY BELLY COLOUR HAS STARTED "+ bellyColours);
        for (Animal animal :
                animalList) {
            ArrayList<String> colours = new ArrayList<String>(Arrays.asList(animal.getBellyColour().split(";")));
            if (colours.containsAll(bellyColours)) {
                resultList.add(animal);
            }
        }
        return resultList;
    }
    public ArrayList<Animal> searchBySize(ArrayList<Animal> animalList, List<Integer> sizesList){
        Log.d("STARTING", "THIS SEARCH BY SIZE HAS STARTED "+ sizesList);
        ArrayList<Animal> resultList = new ArrayList<>();
        for (Animal animal :
                animalList) {
            if (sizesList.size() > 2){
                if((sizesList.get(0) > animal.getMinBodyLengthCm()) && (sizesList.get(1) < animal.getMaxBodyLengthCm()) && (animal.getType().equalsIgnoreCase("Bird"))) {
                    resultList.add(animal);
                }
                Log.d("MID", "THIS PROCESS HAS WORKED");

            } else{
                if(sizesList.get(1).equals(0)){
                    if((sizesList.get(0) > animal.getMinBodyLengthCm())&& (animal.getType().equalsIgnoreCase("Bird"))){
                        resultList.add(animal);
                    }
                }
                if(sizesList.get(1).equals(1)){
                    if((sizesList.get(0) < animal.getMaxBodyLengthCm()) && (animal.getType().equalsIgnoreCase("Bird"))){
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
    public ArrayList<Animal> searchByHeadColour(ArrayList<Animal> animalList, List<String> headColours){
        ArrayList<Animal> resultList = new ArrayList<>();
        Log.d("STARTING", "THIS SEARCH BY HEAD COLOUR HAS STARTED "+ headColours);
        for (Animal animal :
                animalList) {
            ArrayList<String> colours = new ArrayList<String>(Arrays.asList(animal.getHeadColour().split(";")));
            if (colours.containsAll(headColours)) {
                resultList.add(animal);
            }
        }
        return resultList;

    }
    public ArrayList<Animal> searchByWingColour(ArrayList<Animal> animalList, List<String> wingColours){
        ArrayList<Animal> resultList = new ArrayList<>();
        Log.d("STARTING", "THIS SEARCH BY WING COLOUR HAS STARTED "+ wingColours);
        for (Animal animal :
                animalList) {
            ArrayList<String> colours = new ArrayList<String>(Arrays.asList(animal.getWingColour().split(";")));
            if (colours.containsAll(wingColours)) {
                resultList.add(animal);
            }
        }
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
            if (filter.equalsIgnoreCase("BirdBellyColour")){
                resultList = searchByBellyColour(resultList, getArguments().getStringArrayList("BirdBellyColour"));
                Log.d("AFTER BY BELLY COLOUR", resultList.size() + "");
            }
            if (filter.equalsIgnoreCase("BirdHeadColour")){
                resultList = searchByHeadColour(resultList, getArguments().getStringArrayList("BirdHeadColour"));
                Log.d("AFTER BY HEAD COLOUR", resultList.size() + "");
            }
            if (filter.equalsIgnoreCase("BirdWingColour")){
                resultList = searchByWingColour(resultList, getArguments().getStringArrayList("BirdWingColour"));
                Log.d("AFTER BY WING COLOUR", resultList.size() + "");
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
            case R.id.species_back_button:
                Navigation.findNavController(v).navigate(R.id.action_speciesIdentifierResult_to_birdBellyColourFragment, this.getArguments());
                break;
        }
    }

    class CustomAdapter extends ArrayAdapter<String> {
        Context context;
        List<String> names = new ArrayList<String>();
        List<String> types = new ArrayList<String>();
        List<String> scientificNouns = new ArrayList<String>();
        List<String> images = new ArrayList<String>();
//        int[] imgs;


        public CustomAdapter(Context context, List<String> names, List<String> types, List<String> scientificNouns, List<String> animalImages) {
            super(context, R.layout.animal_lists_item, R.id.animal_name, names);

            this.context = context;
            this.images = animalImages;
            this.names = names;
            this.types = types;
            this.scientificNouns = scientificNouns;
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View animal_item = inflater.inflate(R.layout.animal_lists_item, parent, false);
//            ImageView images = reserve_item.findViewById(R.id.reserve_logo);
            ImageView image = animal_item.findViewById(R.id.animal_image);
            TextView name = animal_item.findViewById(R.id.animal_name);
            TextView type = animal_item.findViewById(R.id.animal_type);
            TextView scientificNoun = animal_item.findViewById(R.id.animal_scientific_name);

            Context context = image.getContext();
            Integer id = context.getResources().getIdentifier(images.get(position), "drawable", context.getPackageName());
            if (!id.toString().equals("null") ){
                image.setImageResource(id);
            }
//

//            images.setImageResource(imgs[position]);
            ;
            name.setText(names.get(position));
            type.setText(types.get(position));
            scientificNoun.setText(scientificNouns.get(position));

            return animal_item;
        }
    }
}


