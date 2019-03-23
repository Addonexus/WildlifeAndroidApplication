package com.example.animalapp;

import android.app.ListFragment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
<<<<<<< HEAD
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.util.Log;
=======
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
>>>>>>> bottom-navigation-bar

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
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
=======
>>>>>>> bottom-navigation-bar

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);

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
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
