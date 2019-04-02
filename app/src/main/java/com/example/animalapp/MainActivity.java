package com.example.animalapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    //TextView item;
    TextView textView;
    Button button;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalHelper.onAttach(newBase,"en"));

    }


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

}
