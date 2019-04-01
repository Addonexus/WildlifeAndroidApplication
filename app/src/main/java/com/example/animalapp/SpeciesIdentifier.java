package com.example.animalapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpeciesIdentifier extends Fragment {
    AnimalDatabase db;


    public SpeciesIdentifier() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Species Identifier");
        View view = inflater.inflate(R.layout.fragment_species_identifier, container, false);

        Log.d("THINGS IN SPECIES FRAG", view.toString());
        db = AnimalDatabase.getDatabase(getContext());
        db.animalDAO().clearAnimal();
        addData();

        return view;
    }
    public AnimalDatabase getDb() {
        return db;
    }
    private void addData() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.animalDAO().clearAnimal();

                List<Animal> list = null;
                try {
                    list = readAnimalListFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                db.animalDAO().insertAll(list);

//                final List<Animal> allAnimals = db.animalDAO().getAllAnimals();

//                Log.d("List All Animals: ", "AllAnimals: " + allAnimals);
            }
        });
    }
    private List<Animal> readAnimalListFile() throws IOException {

        List<Animal> animalList = new ArrayList<>();

        InputStream is = getResources().openRawResource(R.raw.animal_list);
        InputStreamReader csvStreamReader = new InputStreamReader(is);

        CSVReader reader = new CSVReader(csvStreamReader);
        reader.skip(1);

        String[] record = null;

        while ((record = reader.readNext()) != null) {
            Animal animal = new Animal(record[0], record[1], record[2],
                    record[3], record[4], record[5], record[6],
                    record[7], record[8], record[9], record[10], record[11]);

            animalList.add(animal);
//                Log.d("Read file: ", "Animal List: " + animal);

        }
        return animalList;
    }
}
