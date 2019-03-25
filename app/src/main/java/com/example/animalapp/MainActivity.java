package com.example.animalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.animalapp.Database.Animal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readAnimalListFile();

    }

    private List<Animal> animalList = new ArrayList<>();

    private void readAnimalListFile() {

        InputStream is = getResources().openRawResource(R.raw.animal_list);
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");

                Animal animal = new Animal(tokens[0], tokens[1], tokens[2],
                        tokens[3], tokens[4],
                        tokens[5],tokens[6],
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

/*
*   Read CSV Resource File
*   Taken from Youtube by Brian Fraser 26/02/17
*   Accessed 25/03/2019
*   https://www.youtube.com/watch?v=i-TqNzUryn8
*   InputStream is = getResources().openRawResource(R.raw.animal_list);
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
*
*/
