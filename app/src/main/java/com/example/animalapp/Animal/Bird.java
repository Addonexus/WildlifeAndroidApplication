package com.example.animalapp.Animal;

import com.example.animalapp.Database.Animal;

public class Bird extends Animal {

    public Bird(String type, String name, String scientificName, String minBodyLengthCm, String maxBodyLengthCm, String minWingspanCm, String maxWingspanCm, String description, String habitat, String bestTime, String bestWalk, String foodSource) {
        super(type, name, scientificName, minBodyLengthCm, maxBodyLengthCm, minWingspanCm, maxWingspanCm, description, habitat, bestTime, bestWalk, foodSource);
    }
}
