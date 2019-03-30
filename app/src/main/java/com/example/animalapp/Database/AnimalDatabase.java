package com.example.animalapp.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {Animal.class}, version = 1, exportSchema = false)
public abstract class AnimalDatabase extends RoomDatabase {

    private static AnimalDatabase INSTANCE;

    public synchronized static AnimalDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static AnimalDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                AnimalDatabase.class,
                "animal_list")
                .allowMainThreadQueries()
                .build();
        }

    public abstract AnimalDAO animalDAO();

}
