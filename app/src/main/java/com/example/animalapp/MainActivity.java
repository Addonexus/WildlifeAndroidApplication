package com.example.animalapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.animalapp.Database.Animal;
import com.example.animalapp.Database.AnimalDatabase;
//import com.opencsv.CSVReader;
import io.paperdb.Paper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalHelper.onAttach(newBase, "en"));


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        textView = (TextView) findViewById(R.id.text_view);
        textView = (TextView) findViewById(R.id.textview_login);
        textView = (TextView) findViewById(R.id.textview_reg);
        textView = (TextView) findViewById(R.id.textview_no_reg);
        textView = (TextView) findViewById(R.id.textview_cardiff);
        textView = (TextView) findViewById(R.id.textView_wild);
        textView = (TextView) findViewById(R.id.species_choice_comment);
        textView = (TextView) findViewById(R.id.animal_name);
        textView = (TextView) findViewById(R.id.animal_scientific_name);
        textView = (TextView) findViewById(R.id.animal_type);
        textView = (TextView) findViewById(R.id.choose_color);
        textView = (TextView) findViewById(R.id.filter_view);
        textView = (TextView) findViewById(R.id.choose_height);
        textView = (TextView) findViewById(R.id.filter_views);

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
        //textView = (TextView) findViewById(R.id.btn_profile);


        //Init paper first;
        Paper.init(this);

        //Default language is english
        String language = Paper.book().read("language");
        if (language == null)
            Paper.book().write("language", "en");

        updateView((String) Paper.book().read("language"));


        //code below is for the notifications
       /* findViewById(R.id.btn_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setting calender instance

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, 5);
                calendar.set(Calendar.MONTH, 4);
                calendar.set(Calendar.YEAR, 2019);
                calendar.set(Calendar.HOUR_OF_DAY, 10);
                calendar.set(Calendar.MINUTE, 50);
                calendar.set(Calendar.SECOND, 30);

                Intent intent = new Intent(getApplicationContext(), NotificationReciever.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            }
        });*/

    }
    //Top menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.language_en) {
            Paper.book().write("language", "en");
            updateView((String) Paper.book().read("language"));
            recreate();
        } else if (item.getItemId() == R.id.language_cy) {
            Paper.book().write("language", "cy");
            updateView((String) Paper.book().read("language"));
            recreate();
        }else if(item.getItemId() == R.id.btn_notification){
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DATE, 5);
            calendar.set(Calendar.MONTH, 4);
            calendar.set(Calendar.HOUR_OF_DAY, 11);
            calendar.set(Calendar.MINUTE, 07);
            calendar.set(Calendar.SECOND, 30);

            Intent intent = new Intent(getApplicationContext(), NotificationReciever.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        return true;
    }

    private void updateView(String lang) {
        Context context = LocalHelper.setLocale(this, lang);
        Resources resources = context.getResources();


    }

    public void browser1(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cardiffconservation.org.uk/"));
        startActivity(browserIntent);
    }

    public void browser2(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ww2.rspb.org.uk/groups/cardiff/"));
        startActivity(browserIntent);
    }

    public void browser3(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.rspb.org.uk/"));
        startActivity(browserIntent);
    }

    public void browser4(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.welshwildlife.org/my-wild-cardiff//"));
        startActivity(browserIntent);
    }

    public void browser5(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cardiff.ac.uk/software-academy/"));
        startActivity(browserIntent);
    }


    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

}
