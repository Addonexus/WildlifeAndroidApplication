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

//    Bird Small Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Min Body Length Cm` <= :minBody " +
            "AND `Min Wingspan Cm` <= :minWing")
    List<Animal> getBirdSmallMinBodyWingSmall(String bird, int minBody, int minWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Min Body Length Cm` <= :minBody " +
            "AND `Min Wingspan Cm` BETWEEN :minWing AND :maxMinWing")
    List<Animal> getBirdSmallMinBodyWingMedium(String bird, int minBody, int minWing, int maxMinWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Min Body Length Cm` <= :minBody " +
            "AND `Min Wingspan Cm` >= :minWing")
    List<Animal> getBirdSmallMinBodyWingLarge(String bird, int minBody, int minWing);

//    Bird Medium Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Min Body Length Cm` BETWEEN :minBody AND :maxMinBody " +
            "AND `Min Wingspan Cm` <= :minWing")
    List<Animal> getBirdMediumMinBodyWingSmall(String bird, int minBody, int maxMinBody, int minWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Min Body Length Cm` BETWEEN :minBody AND :maxMinBody " +
            "AND `Min Wingspan Cm` BETWEEN :minWing AND :maxMinWing ")
    List<Animal> getBirdMediumMinBodyWingMedium(String bird, int minBody, int maxMinBody, int minWing, int maxMinWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Min Body Length Cm` BETWEEN :minBody AND :maxMinBody " +
            "AND `Min Wingspan Cm` >= :minWing")
    List<Animal> getBirdMediumMinBodyWingLarge(String bird, int minBody, int maxMinBody, int minWing);

//    Bird Large Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Min Body Length Cm` >= :minBody " +
            "AND `Min Wingspan Cm` <= :minWing")
    List<Animal> getBirdLargeMinBodyWingSmall(String bird, int minBody, int minWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Min Body Length Cm` >= :minBody " +
            "AND `Min Wingspan Cm` BETWEEN :minWing AND :maxMinWing")
    List<Animal> getBirdLargeMinBodyWingMedium(String bird, int minBody, int minWing, int maxMinWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Min Body Length Cm` >= :minBody " +
            "AND `Min Wingspan Cm` >= :minWing")
    List<Animal> getBirdLargeMinBodyWingLarge(String bird, int minBody, int minWing);

//    Bird Small Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Max Body Length Cm` <= :maxBody " +
            "AND `Min Wingspan Cm` <= :minWing")
    List<Animal> getBirdSmallMaxBodyWingSmall(String bird, int maxBody, int minWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Max Body Length Cm` <= :maxBody " +
            "AND `Min Wingspan Cm` BETWEEN :minWing AND :maxMinWing")
    List<Animal> getBirdSmallMaxBodyWingMedium(String bird, int maxBody, int minWing, int maxMinWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Max Body Length Cm` <= :maxBody " +
            "AND `Min Wingspan Cm` >= :minWing")
    List<Animal> getBirdSmallMaxBodyWingLarge(String bird, int maxBody, int minWing);

//    Bird Medium Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Max Body Length Cm` BETWEEN :maxBody AND :maxMaxBody " +
            "AND `Min Wingspan Cm` <= :minWing")
    List<Animal> getBirdMediumMaxBodyWingSmall(String bird, int maxBody, int maxMaxBody, int minWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Max Body Length Cm` BETWEEN :maxBody AND :maxMaxBody " +
            "AND `Min Wingspan Cm` BETWEEN :minWing AND :maxMinWing ")
    List<Animal> getBirdMediumMaxBodyWingMedium(String bird, int maxBody, int maxMaxBody, int minWing, int maxMinWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Max Body Length Cm` BETWEEN :maxBody AND :maxMaxBody " +
            "AND `Min Wingspan Cm` >= :minWing")
    List<Animal> getBirdMediumMaxBodyWingLarge(String bird, int maxBody, int maxMaxBody, int minWing);

//    Bird Large Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Max Body Length Cm` >= :maxBody " +
            "AND `Min Wingspan Cm` <= :minWing")
    List<Animal> getBirdLargeMaxBodyWingSmall(String bird, int maxBody, int minWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Max Body Length Cm` >= :maxBody " +
            "AND `Min Wingspan Cm` BETWEEN :minWing AND :maxMinWing")
    List<Animal> getBirdLargeMaxBodyWingMedium(String bird, int maxBody, int minWing, int maxMinWing);

    @Query("SELECT * FROM Animal WHERE type LIKE :bird " +
            "AND `Max Body Length Cm` >= :maxBody " +
            "AND `Min Wingspan Cm` >= :minWing")
    List<Animal> getBirdLargeMaxBodyWingLarge(String bird, int maxBody, int minWing);

//    Mammal Small Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :mammal " +
            "AND `Min Body Length Cm` <= :minBody")
    List<Animal> getMammalSmallMinBody(String mammal, int minBody);

//    Mammal Medium Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :mammal " +
            "AND `Min Body Length Cm` BETWEEN :minBody AND :maxMinBody")
    List<Animal> getMammalMediumMinBody(String mammal, int minBody, int maxMinBody);

//    Mammal Large Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :mammal " +
            "AND `Min Body Length Cm` >= :minBody")
    List<Animal> getMammalLargeMinBody(String mammal, int minBody);

//    Mammal Small Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :mammal " +
            "AND `Max Body Length Cm` <= :maxBody")
    List<Animal> getMammalSmallMaxBody(String mammal, int maxBody);

//    Mammal Medium Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :mammal " +
            "AND `Max Body Length Cm` BETWEEN :maxBody AND :maxMaxBody")
    List<Animal> getMammalMediumMaxBody(String mammal, int maxBody, int maxMaxBody);

//    Mammal Large Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :mammal " +
            "AND `Max Body Length Cm` >= :maxBody")
    List<Animal> getMammalLargeMaxBody(String mammal, int maxBody);

//    Reptile Small Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :reptile " +
            "AND `Min Body Length Cm` <= :minBody")
    List<Animal> getReptileSmallMinBody(String reptile, int minBody);

//    Reptile Medium Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :reptile " +
            "AND `Min Body Length Cm` BETWEEN :minBody AND :maxMinBody")
    List<Animal> getReptileMediumMinBody(String reptile, int minBody, int maxMinBody);

//    Reptile Large Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :reptile " +
            "AND `Min Body Length Cm` >= :minBody")
    List<Animal> getReptileLargeMinBody(String reptile, int minBody);

//    Reptile Small Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :reptile " +
            "AND `Max Body Length Cm` <= :maxBody")
    List<Animal> getReptileSmallMaxBody(String reptile, int maxBody);

//    Reptile Medium Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :reptile " +
            "AND `Max Body Length Cm` BETWEEN :maxBody AND :maxMaxBody")
    List<Animal> getReptileMediumMaxBody(String reptile, int maxBody, int maxMaxBody);

//    Reptile Large Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :reptile " +
            "AND `Max Body Length Cm` >= :maxBody")
    List<Animal> getReptileLargeMaxBody(String reptile, int maxBody);

//    Amphibian Small Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :amphibian " +
            "AND `Min Body Length Cm` <= :minBody")
    List<Animal> getAmphibianSmallMinBody(String amphibian, int minBody);

//    Amphibian Medium Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :amphibian " +
            "AND `Min Body Length Cm` BETWEEN :minBody AND :maxMinBody")
    List<Animal> getAmphibianMediumMinBody(String amphibian, int minBody, int maxMinBody);

//    Amphibian Large Min Body

    @Query("SELECT * FROM Animal WHERE type LIKE :amphibian " +
            "AND `Min Body Length Cm` >= :minBody")
    List<Animal> getAmphibianLargeMinBody(String amphibian, int minBody);

//    Amphibian Small Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :amphibian " +
            "AND `Max Body Length Cm` <= :maxBody")
    List<Animal> getAmphibianSmallMaxBody(String amphibian, int maxBody);

//    Amphibian Medium Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :amphibian " +
            "AND `Max Body Length Cm` BETWEEN :maxBody AND :maxMaxBody")
    List<Animal> getAmphibianMediumMaxBody(String amphibian, int maxBody, int maxMaxBody);

//    Amphibian Large Max Body

    @Query("SELECT * FROM Animal WHERE type LIKE :amphibian " +
            "AND `Max Body Length Cm` >= :maxBody")
    List<Animal> getAmphibianLargeMaxBody(String amphibian, int maxBody);


    @Insert
    void insertAll(List<Animal> animal);

    @Query("DELETE FROM Animal")
    void clearAnimal();


}
