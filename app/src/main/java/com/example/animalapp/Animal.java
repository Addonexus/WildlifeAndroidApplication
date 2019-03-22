package com.example.animalapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Animal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Type")
    private String type;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Scientific_Name")
    private String scientificName;

    @ColumnInfo(name = "Body Length Cm")
    private String bodyLengthCm;

    @ColumnInfo(name = "Wingspan Cm")
    private String wingspanCm;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "Habitat")
    private String habitat;

    @ColumnInfo(name = "Best Time of Year to see")
    private String bestTime;

    @ColumnInfo(name = "Best Walk to see on")
    private String bestWalk;

    @ColumnInfo(name = "Food Source")
    private String foodSource;

    public Animal(String type, String name, String scientificName, String bodyLengthCm, String wingspanCm, String description, String habitat, String bestTime, String bestWalk, String foodSource) {
        this.type = type;
        this.name = name;
        this.scientificName = scientificName;
        this.bodyLengthCm = bodyLengthCm;
        this.wingspanCm = wingspanCm;
        this.description = description;
        this.habitat = habitat;
        this.bestTime = bestTime;
        this.bestWalk = bestWalk;
        this.foodSource = foodSource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getBodyLengthCm() {
        return bodyLengthCm;
    }

    public void setBodyLengthCm(String bodyLengthCm) {
        this.bodyLengthCm = bodyLengthCm;
    }

    public String getWingspanCm() {
        return wingspanCm;
    }

    public void setWingspanCm(String wingspanCm) {
        this.wingspanCm = wingspanCm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getBestTime() {
        return bestTime;
    }

    public void setBestTime(String bestTime) {
        this.bestTime = bestTime;
    }

    public String getBestWalk() {
        return bestWalk;
    }

    public void setBestWalk(String bestWalk) {
        this.bestWalk = bestWalk;
    }

    public String getFoodSource() {
        return foodSource;
    }

    public void setFoodSource(String foodSource) {
        this.foodSource = foodSource;
    }
}
