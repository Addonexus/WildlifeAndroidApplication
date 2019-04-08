package com.example.animalapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
import java.util.Locale;

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
        
        //MAIN BOTTOM NAV
        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //LOGIN FRAGMENT
        fragmentTransaction.add(R.id.fragment_container, new LoginFragment());
        fragmentTransaction.commit();

        final HomeFragment homeFragment = new HomeFragment();
        final AnimalFragment animalFragment = new AnimalFragment();
        final MapFragment mapFragment = new MapFragment();
        final NatureReserveFragment natureReserveFragment = new NatureReserveFragment();
        final LinksFragment linksFragment = new LinksFragment();


        //MAIN BOTTOM NAV IF STATEMENT FOR LOADING FRAGMENTS VIA BUTTONS PRESSED
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                if (id == R.id.navHome) {
                    fragmentTransaction.replace(R.id.fragment_container, homeFragment);
                    fragmentTransaction.commit();
                    return true;
                } else if (id == R.id.navList) {
                    fragmentTransaction.replace(R.id.fragment_container, animalFragment);
                    fragmentTransaction.commit();
                    return true;
                } else if (id == R.id.navMap) {
                    fragmentTransaction.replace(R.id.fragment_container, mapFragment);
                    fragmentTransaction.commit();
                    return true;
                } else if (id == R.id.navWalk) {
                    fragmentTransaction.replace(R.id.fragment_container, natureReserveFragment);
                    fragmentTransaction.commit();
                    return true;
                } else if (id == R.id.navLinks) {
                    fragmentTransaction.replace(R.id.fragment_container, linksFragment);
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
        });


        navigationView.setSelectedItemId(R.id.navHome);

// else if (item.getItemId() == R.id.btn_notification) { //NOTIFICATION
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.DATE, 5);
//                calendar.set(Calendar.MONTH, 4);
//                calendar.set(Calendar.HOUR_OF_DAY, 11);
//                calendar.set(Calendar.MINUTE, 07);
//                calendar.set(Calendar.SECOND, 30);
//
//                //NOTIFICATION INTENT
//                Intent intent = new Intent(getApplicationContext(), NotificationReciever.class);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//            }
        //return true;

    }

    //Top menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    //LANGUAGE DEFAULT ENGLISH AND SECONDARY LANGUAGE WELSH
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Configuration configuration;
        configuration = new Configuration(getResources().getConfiguration());
        if (item.getItemId() == R.id.language_en) {
            configuration.locale = Locale.ENGLISH;
        } else if (item.getItemId() == R.id.language_cy) {
            configuration.locale = new Locale("cy");
        }

        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        recreate();
        return true;
    }
}