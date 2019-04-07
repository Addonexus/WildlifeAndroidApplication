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

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtDescription;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;

    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper sqLiteHelper;

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
        setContentView(R.layout.animal_main);

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

    //Animal seen list code

    init();

    sqLiteHelper = new SQLiteHelper(this, "AnimalDB.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS Animal(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, description VARCHAR, image BLOB)");

        btnChoose.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_GALLERY
            );
        }
    });

        btnAdd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try{
                sqLiteHelper.insertData(
                        edtName.getText().toString().trim(),
                        edtDescription.getText().toString().trim(),
                        imageViewToByte(imageView)
                );
                Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                edtName.setText("");
                edtDescription.setText("");
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    });

        btnList.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, AnimalList.class);
            startActivity(intent);
        }
    });
}

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        edtName = (EditText) findViewById(R.id.edtName);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnList = (Button) findViewById(R.id.btnList);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    //End of animal seen list code

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
