package com.example.animalapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AnimalDAO {

    @Query("SELECT * FROM Animal")
    List<Animal> getAllAnimals();

    @Query("SELECT * FROM Animal WHERE type LIKE :bird")
    List<Animal> getAllBirds(String bird);

    @Query("SELECT * FROM Animal WHERE type LIKE :mammal")
    List<Animal> getAllMammals(String mammal);

    @Query("SELECT * FROM Animal WHERE type LIKE :reptile")
    List<Animal> getAllReptiles(String reptile);

    @Query("SELECT * FROM Animal WHERE type LIKE :amphibian")
    List<Animal> getAllAmphibian(String amphibian);

    @Insert
    void insertAll(Animal... animals);


}
