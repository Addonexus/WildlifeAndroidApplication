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

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Min Body Length Cm` <= :minBody " +
            "AND `Min Wingspan Cm` <= :minWing")
    List<Animal> getBirdMinMin(String bird, int minBody, int minWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Min Body Length Cm` <= :minBody " +
            "AND `Max Wingspan Cm` >= :maxWing")
    List<Animal> getBirdMinMax(String bird, int minBody, int maxWing);

    @Insert
    void insertAll(Animal... animals);


}
