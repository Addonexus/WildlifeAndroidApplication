package com.example.animalapp;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView =  findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_animals);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()) {

                    case R.id.action_home:
                        Toast.makeText(MainActivity.this, "HomeFragment", Toast.LENGTH_SHORT).show();

                        manager.beginTransaction().replace(R.id.fragment_container,
                                new HomeFragment()).commit();


                        break;
                    case R.id.action_animals:
                        Toast.makeText(MainActivity.this, "Animals", Toast.LENGTH_SHORT).show();

                        manager.beginTransaction().replace(R.id.fragment_container,
                                new AnimalFragment()).commit();
                        break;
                    case R.id.action_map:
                        Toast.makeText(MainActivity.this, "MapFragment", Toast.LENGTH_SHORT).show();

                        manager.beginTransaction().replace(R.id.fragment_container,
                                new MapFragment()).commit();
                        break;
                    case R.id.action_reserves:
                        Toast.makeText(MainActivity.this, "Nature Reserve", Toast.LENGTH_SHORT).show();

                        manager.beginTransaction().replace(R.id.fragment_container,
                                new NatureReserveFragment()).commit();
                        break;
                    case R.id.action_links:
                        Toast.makeText(MainActivity.this, "External Links", Toast.LENGTH_SHORT).show();

                        manager.beginTransaction().replace(R.id.fragment_container,
                                new LinksFragment()).commit();
                        break;

                }
                return true;
            }

        });
    }
}