package com.example.animalapp.Database;

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

    @ColumnInfo(name = "Min Body Length Cm")
    private int minBodyLengthCm;

    @ColumnInfo(name = "Max Body Length Cm")
    private int maxBodyLengthCm;

    @ColumnInfo(name = "Min Wingspan Cm")
    private Integer minWingspanCm;

    @ColumnInfo(name = "Max Wingspan Cm")
    private Integer maxWingspanCm;

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

    public Animal(String type, String name, String scientificName, int minBodyLengthCm, int maxBodyLengthCm, Integer minWingspanCm, Integer maxWingspanCm, String description, String habitat, String bestTime, String bestWalk, String foodSource) {
        this.type = type;
        this.name = name;
        this.scientificName = scientificName;
        this.minBodyLengthCm = minBodyLengthCm;
        this.maxBodyLengthCm = maxBodyLengthCm;
        this.minWingspanCm = minWingspanCm;
        this.maxWingspanCm = maxWingspanCm;
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

    public int getMinBodyLengthCm() {
        return minBodyLengthCm;
    }

    public void setMinBodyLengthCm(int minBodyLengthCm) {
        this.minBodyLengthCm = minBodyLengthCm;
    }

    public int getMaxBodyLengthCm() {
        return maxBodyLengthCm;
    }

    public void setMaxBodyLengthCm(int maxBodyLengthCm) {
        this.maxBodyLengthCm = maxBodyLengthCm;
    }

    public Integer getMinWingspanCm() {
        return minWingspanCm;
    }

    public void setMinWingspanCm(Integer minWingspanCm) {
        this.minWingspanCm = minWingspanCm;
    }

    public Integer getMaxWingspanCm() {
        return maxWingspanCm;
    }

    public void setMaxWingspanCm(Integer maxWingspanCm) {
        this.maxWingspanCm = maxWingspanCm;
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

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", minBodyLengthCm=" + minBodyLengthCm +
                ", maxBodyLengthCm=" + maxBodyLengthCm +
                ", minWingspanCm=" + minWingspanCm +
                ", maxWingspanCm=" + maxWingspanCm +
                ", description='" + description + '\'' +
                ", habitat='" + habitat + '\'' +
                ", bestTime='" + bestTime + '\'' +
                ", bestWalk='" + bestWalk + '\'' +
                ", foodSource='" + foodSource + '\'' +
                '}';
    }
}
