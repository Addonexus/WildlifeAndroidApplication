package com.example.animalapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

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
        AppCompatButton animalButton = this.findViewById(R.id.animal);
        animalButton.setOnClickListener(this);

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

        String [] record = null;
        int num = 0;

            while ((record = reader.readNext()) != null) {
                Animal animal = new Animal();
                animal.setType(record[0]);
                animal.setName(record[1]);
                animal.setScientificName(record[2]);
                animal.setMinBodyLengthCm(Integer.parseInt(record[3]));
                animal.setMaxBodyLengthCm(Integer.parseInt(record[4]));

                try{
                    if (record[5] != null && record[6] != null) {
                        animal.setMinWingspanCm(Integer.parseInt(record[5]));
                        animal.setMaxWingspanCm(Integer.parseInt(record[6]));
                    } else {
                        animal.setMinWingspanCm(num);
                        animal.setMaxWingspanCm(num);
                    }
                }catch (NumberFormatException e) {
                    num = 0;
                }

//                animal.setMinWingspanCm(Integer.parseInt(record[5]));
//                animal.setMaxWingspanCm(Integer.parseInt(record[6]));

                animal.setDescription(record[7]);
                animal.setHabitat(record[8]);
                animal.setBestTime(record[9]);
                animal.setBestWalk(record[10]);
                animal.setFoodSource(record[11]);

                animalList.add(animal);
//                Log.d("Read file: ", "Animal List: " + animal);

            }
            return animalList;
        }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        final List<Animal> allAnimals = db.animalDAO().getAllAnimals();

        switch (viewId){

            case R.id.initialise:
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {


//                        Log.d("initialised","animals" + allanimals);

                        for (Animal list : allAnimals){
                            if (list.getName().equalsIgnoreCase("common toad")){
                                Log.d("Id 2 - ","Animal 2: " + list);
                            }
                        }

                    }
                });

                break;

            case R.id.animal:
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (Animal list : allAnimals){
                            if(list.getName().equalsIgnoreCase("common newt")){
                                Log.d("ID ANIMAL - ","null attribute: " + list);
                            }
                        }
                    }
                });

        }

    }
}

