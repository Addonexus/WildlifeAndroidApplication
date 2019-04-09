package com.example.animalapp;

import android.util.Log;

import com.example.animalapp.Database.Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class InvertebrateSearchTools {

    static ArrayList<Animal> searchByNumberOfLegs(ArrayList<Animal> animalList, List<Integer> sizesList){
       Log.d("STARTING", "THIS SEARCH BY SIZE HAS STARTED "+ sizesList);
       ArrayList<Animal> resultList = new ArrayList<>();
       for (Animal animal :
               animalList) {
           if(sizesList.get(1).equals(0)){
               if((sizesList.get(0) > animal.getNumberOfLegs())&& (animal.getType().equalsIgnoreCase("Invertebrate"))){
                   resultList.add(animal);
               }
           }
           if(sizesList.get(1).equals(1)){
               if((sizesList.get(0) < animal.getNumberOfLegs()) && (animal.getType().equalsIgnoreCase("Invertebrate"))){
                   resultList.add(animal);
               }
           }
           if(sizesList.get(1).equals(2)){
               if((sizesList.get(0) == animal.getNumberOfLegs()) && (animal.getType().equalsIgnoreCase("Invertebrate"))){
                   resultList.add(animal);
               }
           }
       }
       return resultList;

   }
    static ArrayList<Animal> searchByWing(ArrayList<Animal> animalList, String wingType){
        Log.d("STARTING", "THIS PROCESS SEARCH BY TYPE HAS STARTED "+ wingType);
        ArrayList<Animal> resultList = new ArrayList<>();
        for (Animal animal :
                animalList) {

            if (animal.getWingType().equalsIgnoreCase(wingType)){
                resultList.add(animal);
                Log.d("MID", "THIS PROCESS HAS WORKED");
            }
        }
//        for (Animal animal: resultList){
//            Log.d("RSEULT ANIMAL", animal.toString());
//        }
        return resultList;
    }






}
