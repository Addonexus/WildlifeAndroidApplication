package com.example.animalapp;


import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;

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

    private void doCustomAdapterExample(List<Animal> listOfAnimals) {



    }


}
