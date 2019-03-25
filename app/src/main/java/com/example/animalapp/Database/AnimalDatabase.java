package com.example.animalapp.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Animal.class}, version = 1, exportSchema = false)
public abstract class AnimalDatabase extends RoomDatabase {

    public abstract AnimalDAO animalDAO();

}
