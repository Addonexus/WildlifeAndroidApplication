package com.example.animalapp.SearchByBird;

import android.util.Log;

import com.example.animalapp.Database.Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReptileAmphibianSearchTools {
    public static ArrayList<Animal> searchByBellyColour(ArrayList<Animal> animalList, List<String> bellyColours){
        ArrayList<Animal> resultList = new ArrayList<>();
        Log.d("STARTING", "THIS SEARCH BY BELLY COLOUR HAS STARTED "+ bellyColours);
        for (Animal animal :
                animalList) {
            ArrayList<String> colours = new ArrayList<String>(Arrays.asList(animal.getBellyColour().split(";")));
            if (colours.containsAll(bellyColours)) {
                resultList.add(animal);
            }
        }
        return resultList;
    }
    public static ArrayList<Animal> searchBySize(ArrayList<Animal> animalList, List<Integer> sizesList){
        Log.d("STARTING", "THIS SEARCH BY SIZE HAS STARTED "+ sizesList);
        ArrayList<Animal> resultList = new ArrayList<>();
        for (Animal animal :
                animalList) {
            if (sizesList.size() > 2){
                if((sizesList.get(0) > animal.getMinBodyLengthCm()) && (sizesList.get(1) < animal.getMaxBodyLengthCm()) && (animal.getType().equalsIgnoreCase("Reptile") || animal.getType().equalsIgnoreCase("Amphibian"))) {
                    resultList.add(animal);
                }
                Log.d("MID", "THIS PROCESS HAS WORKED");

            } else{
                if(sizesList.get(1).equals(0)){
                    if((sizesList.get(0) > animal.getMinBodyLengthCm())&& (animal.getType().equalsIgnoreCase("Reptile") || animal.getType().equalsIgnoreCase("Amphibian"))){
                        resultList.add(animal);
                    }
                }
                if(sizesList.get(1).equals(1)){
                    if((sizesList.get(0) < animal.getMaxBodyLengthCm()) && (animal.getType().equalsIgnoreCase("Reptile") || animal.getType().equalsIgnoreCase("Amphibian"))){
                        resultList.add(animal);
                    }
                }

                Log.d("MID", "THIS PROCESS HAS WORKED");
            }
        }
        return resultList;

    }
    public static ArrayList<Animal> searchBySkinColour(ArrayList<Animal> animalList, List<String> skinColours){
        ArrayList<Animal> resultList = new ArrayList<>();
        Log.d("STARTING", "THIS SEARCH BY SKIN COLOUR HAS STARTED "+ skinColours);
        for (Animal animal :
                animalList) {
            ArrayList<String> colours = new ArrayList<String>(Arrays.asList(animal.getSkinColour().split(";")));
            if (colours.containsAll(skinColours)) {
                resultList.add(animal);
            }
        }
        return resultList;

    }
    public static ArrayList<Animal> searchByWingColour(ArrayList<Animal> animalList, List<String> wingColours){
        ArrayList<Animal> resultList = new ArrayList<>();
        Log.d("STARTING", "THIS SEARCH BY WING COLOUR HAS STARTED "+ wingColours);
        for (Animal animal :
                animalList) {
            ArrayList<String> colours = new ArrayList<String>(Arrays.asList(animal.getWingColour().split(";")));
            if (colours.containsAll(wingColours)) {
                resultList.add(animal);
            }
        }
        return resultList;

    }




}
