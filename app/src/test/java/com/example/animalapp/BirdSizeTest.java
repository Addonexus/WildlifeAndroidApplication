package com.example.animalapp;

import com.example.animalapp.Database.Animal;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BirdSizeTest {
    private Animal animalOne;
    private Animal animalTwo;
    private Animal animalThree;
    private Animal animalFour;
    private ArrayList<Animal> listOfAnimals = new ArrayList<>();
    private ArrayList<Animal> resultAnimals = new ArrayList<>();
    @Before
    public void setAnimals() {
        animalOne = new Animal();
        animalOne.setName("Hammond");
        animalOne.setType("Bird");
        animalOne.setMaxBodyLengthCm(30);
        animalOne.setMinBodyLengthCm(5);

        animalTwo =  new Animal();
        animalTwo.setName("Not Bird");
        animalTwo.setType("Mammal");
        animalTwo.setMaxBodyLengthCm(110);
        animalTwo.setMinBodyLengthCm(105);

        animalThree =  new Animal();
        animalThree.setName("Parrot");
        animalThree.setType("Bird");
        animalThree.setMaxBodyLengthCm(100);
        animalThree.setMinBodyLengthCm(45);

        animalFour =  new Animal();
        animalFour.setName("Eagle");
        animalFour.setType("Bird");
        animalFour.setMaxBodyLengthCm(50);
        animalFour.setMinBodyLengthCm(10);

        listOfAnimals.add(animalOne);
        listOfAnimals.add(animalTwo);
        listOfAnimals.add(animalThree);
        listOfAnimals.add(animalFour);
    }
    @Test
    public void searchingByValidSize(){
        List<Integer> chosenSize = new ArrayList<>();
        chosenSize.add(15);
        chosenSize.add(15);// Placeholder value
        resultAnimals = BirdSearchTools.searchBySize(listOfAnimals,chosenSize);
        assertEquals(2, resultAnimals.size());
    }
    @Test
    public void searchingByValidRangeSize(){
        List<Integer> chosenSize = new ArrayList<>();
        chosenSize.add(15);
        chosenSize.add(50);
        chosenSize.add(0);// Placeholder value
        resultAnimals = BirdSearchTools.searchBySize(listOfAnimals,chosenSize);
        assertEquals(3, resultAnimals.size());
    }
    @Test
    public void testingIfInvalidSpeciesTypeIsSearched(){
        List<Integer> chosenSize = new ArrayList<>();
        chosenSize.add(106);
        chosenSize.add(106);// Placeholder value
        resultAnimals = BirdSearchTools.searchBySize(listOfAnimals,chosenSize);
        assertEquals(0, resultAnimals.size());
    }




}
