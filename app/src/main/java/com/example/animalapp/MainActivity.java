package com.example.animalapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
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
        db.animalDAO().clearAnimal();
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

        /*ImageView img1 = (ImageView) findViewById(R.id.Image1);
        img1.setOnClickListener(onClick){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        };*/

        /*ImageView img2 = (ImageView) findViewById(R.id.Image2);
        img2.setOnClickListener(v){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        };

        ImageView img3 = (ImageView) findViewById(R.id.Image3);
        img3.setOnClickListener(v){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        };

        ImageView img4 = (ImageView) findViewById(R.id.Image4);
        img4.setOnClickListener(v){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        };*/
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
        final List<Animal> allAnimals = db.animalDAO().getAllAnimals();

        switch (viewId){

            case R.id.initialise:
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {


//                        Log.d("initialised","animals" + allanimals);

                        for (Animal list : allAnimals){
                            if (list.getName().equalsIgnoreCase("pintail")){
                                Log.d("Id 2","Animal 2" + list);
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
                            if(list.getMinBodyLengthCm().equals("44")){
                                Log.d("ID ANIMAL","ANIMAL WING 44" + list);
                            }
                        }
                    }
                });

        }

    }
}


    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

}
