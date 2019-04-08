package com.example.animalapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;

import android.widget.TextView;

//import com.opencsv.CSVReader;
import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    //TextView item;
    TextView textView;
    Button button;
    AnimalDatabase db;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalHelper.onAttach(newBase,"en"));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AnimalDatabase.getDatabase(this);

        addData();
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.frame, new LoginFragment());
        fragmentTransaction.commit();

        final HomeFragment homeFragment = new HomeFragment();
        final AnimalFragment animalFragment = new AnimalFragment();
        final MapFragment mapFragment = new MapFragment();
        final NatureReserveFragment natureReserveFragment = new NatureReserveFragment();
        final LinksFragment linksFragment = new LinksFragment();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.navHome) {
                    setFragment(homeFragment);
                    return true;
                } else if (id == R.id.navList) {
                    setFragment(animalFragment);
                    return true;
                } else if (id == R.id.navMap) {
                    setFragment(mapFragment);
                    return true;
                } else if (id == R.id.navWalk) {
                    setFragment(natureReserveFragment);
                    return true;
                } else if (id == R.id.navLinks) {
                    setFragment(linksFragment);
                    return true;
                }
                return false;
            }
        });

        navigationView.setSelectedItemId(R.id.navHome);



        button = (Button) findViewById(R.id.species_bird_button);
        button = (Button) findViewById(R.id.species_mammal_button);
        button = (Button) findViewById(R.id.species_invertebrate_button);
        button = (Button) findViewById(R.id.species_reptile_button);
        button = (Button) findViewById(R.id.bird_colour_option_white);
        button = (Button) findViewById(R.id.bird_colour_option_blue);
        button = (Button) findViewById(R.id.bird_colour_option_black);
        button = (Button) findViewById(R.id.bird_colour_option_red);
        button = (Button) findViewById(R.id.bird_colour_option_grey);
        button = (Button) findViewById(R.id.bird_colour_option_yellow);
        button = (Button) findViewById(R.id.bird_height_option_1);
        button = (Button) findViewById(R.id.bird_height_option_2);
        button = (Button) findViewById(R.id.bird_height_option_3);

        textView = (TextView) findViewById(R.id.language_cy);
        textView = (TextView) findViewById(R.id.language_en);
        textView = (TextView) findViewById(R.id.btn_profile);


        //Init paper first;
        Paper.init(this);

        //Default language is english
        String language = Paper.book().read("language");
        if (language == null)
            Paper.book().write("language", "en");

        updateView((String) Paper.book().read("language"));

        //restart app when button is clicked
        /*Intent mStartActivity = new Intent(MainActivity.this, MainActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(MainActivity.this, mPendingIntentId, mStartActivity,
                PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.language_en)
        {
            Paper.book().write("language", "en");
            updateView((String)Paper.book().read("language"));
        }
        else if (item.getItemId() == R.id.language_cy)
        {
            Paper.book().write("language", "cy");
            updateView((String)Paper.book().read("language"));
        }
        recreate();
        return true;
    }

    private void updateView(String lang) {
        Context context = LocalHelper.setLocale(this, lang);
        Resources resources = context.getResources();


    }

    public void browser1(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cardiffconservation.org.uk/"));
        startActivity(browserIntent);
    }

    public void browser2(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://ww2.rspb.org.uk/groups/cardiff/"));
        startActivity(browserIntent);
    }

    public void browser3(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.rspb.org.uk/"));
        startActivity(browserIntent);
    }

    public void browser4(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.welshwildlife.org/my-wild-cardiff//"));
        startActivity(browserIntent);
    }

    public void browser5(View view){
        Intent browserIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cardiff.ac.uk/software-academy/"));
        startActivity(browserIntent);
    }


    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
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
        int num = 0;


        while ((record = reader.readNext()) != null) {
//            Animal animal = new Animal(record[0], record[1], record[2],
//                    record[3], record[4], record[5], record[6],
//                    record[7], record[8], record[9], record[10], record[11]);
            Animal animal = new Animal();
            animal.setType(record[0]);
            animal.setName(record[1]);
            animal.setScientificName(record[2]);
//            animal.setMinBodyLengthCm(record[3]);
//            animal.setMaxBodyLengthCm(record[4]);
//            animal.setMinWingspanCm(record[5]);
//            animal.setMaxWingspanCm(record[6]);
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
                Log.getStackTraceString(e);
            }

//                animal.setMinWingspanCm(Integer.parseInt(record[5]));
//                animal.setMaxWingspanCm(Integer.parseInt(record[6]));
            animal.setDescription(record[7]);
            animal.setHabitat(record[8]);
            animal.setBestTime(record[9]);
            animal.setBestWalk(record[10]);
            animal.setFoodSource(record[11]);
            animal.setAnimalImage(record[12]);
            animal.setHeadColour(record[14]);
            animal.setWingColour(record[15]);
            animal.setBellyColour(record[16]);

            animalList.add(animal);
//                Log.d("Read file: ", "Animal List: " + animal);

        }
        return animalList;
    }

}
