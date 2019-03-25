package com.example.animalapp;


import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalFragment extends Fragment {

    private AnimalDatabase db;
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_animal, container, false);

        db = Room.databaseBuilder(
                getActivity(),
                AnimalDatabase.class,
                "AnimalDatabase"
        ).build();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                final List<Animal> animals = db.animalDAO().getAllAnimals();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        doCustomAdapterExample(animals);
                    }
                });

            }
        });


        return v;
    }

    private List<Animal> animalList = new ArrayList<>();

    private void doCustomAdapterExample(List<Animal> listOfAnimals) {

        InputStream is = getResources().openRawResource(R.raw.animal_list);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                Animal animal = new Animal(tokens[0], tokens[1], tokens[2],
                        Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]),
                        Integer.valueOf(tokens[5]),Integer.valueOf(tokens[6]),
                        tokens[7], tokens[8], tokens[9], tokens[10],tokens[11]);

                animalList.add(animal);

                Log.d("Activity: ", "Animal List: " + animal);

            }
        } catch (IOException e) {
            Log.wtf("Opening File", "Error reading file." + line, e);
            e.printStackTrace();
        }


    }


}
