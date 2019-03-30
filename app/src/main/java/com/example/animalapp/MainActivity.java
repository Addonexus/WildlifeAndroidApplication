package com.example.animalapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import com.example.animalapp.Animal.Amphibian;
import com.example.animalapp.Animal.Bird;
import com.example.animalapp.Animal.Mammal;
import com.example.animalapp.Animal.Reptile;
import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    AnimalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AnimalDatabase.getDatabase(getApplicationContext());

        addData();

        AppCompatButton initialiseButton = this.findViewById(R.id.initialise);
        initialiseButton.setOnClickListener(this);

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

                final List<Animal> allAnimals = db.animalDAO().getAllAnimals();

                Log.d("List All Animals: ", "AllAnimals: " + allAnimals);
            }
        });
    }

    private List<Animal> readAnimalListFile() throws IOException {

        List<Animal> animalList = new ArrayList<>();

        InputStream is = getResources().openRawResource(R.raw.animal_list);
        InputStreamReader csvStreamReader = new InputStreamReader(is);

        CSVReader reader = new CSVReader(csvStreamReader);
        reader.skip(1);

        String [] record = null;

            while ((record = reader.readNext()) != null) {
                Animal animal = new Animal(record[0], record[1], record[2],
                        record[3], record[4], record[5], record[6],
                        record[7], record[8], record[9], record[10],record[11]);

                animalList.add(animal);
//                Log.d("Read file: ", "Animal List: " + animal);

            }
            return animalList;
        }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        switch (viewId){

            case R.id.initialise:
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {

                        final List<Animal> allAnimals = db.animalDAO().getAllAnimals();
//                        Log.d("initialised","animals" + allanimals);

                        for (Animal list : allAnimals){
                            if (list.getName().equalsIgnoreCase("pintail")){
                                Log.d("Id 2","Animal 2" +list);
                            }
                        }

                    }
                });

                break;

        }

    }
}

