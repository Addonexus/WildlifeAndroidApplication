package com.example.animalapp.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AnimalDAO {

    @Query("SELECT * FROM Animal WHERE id = :animalID")
    List<Animal> getAnimalByID(int animalID);

    @Query("SELECT * FROM Animal")
    List<Animal> getAllAnimals();
    
    @Insert
    void insertAll(List<Animal> animal);

    @Query("DELETE FROM Animal")
    void clearAnimal();


}
