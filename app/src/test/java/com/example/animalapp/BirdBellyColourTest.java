package com.example.animalapp;
import com.example.animalapp.Database.Animal;
import com.example.animalapp.BirdSearchTools;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BirdBellyColourTest {
    private Animal animalOne;
    private Animal animalTwo;
    private Animal animalThree;
    private ArrayList<Animal> listOfAnimals = new ArrayList<>();
    private ArrayList<Animal> resultAnimals = new ArrayList<>();
    @Before
    public void setAnimals() {
        animalOne = new Animal();
        animalOne.setName("Hammond");
        animalOne.setType("Bird");
        animalOne.setBellyColour("Blue");
        animalTwo =  new Animal();
        animalTwo.setName("Test Bird");
        animalTwo.setType("Bird");
        animalTwo.setBellyColour("Blue;Black");
        animalThree =  new Animal();
        animalThree.setName("Parrot");
        animalThree.setType("Bird");
        animalThree.setBellyColour("Blue;White");
        listOfAnimals.add(animalOne);
        listOfAnimals.add(animalTwo);
        listOfAnimals.add(animalThree);
    }
    @Test
    public void searchingByValidColour(){
        List<String> chosenColour = new ArrayList<>();
        chosenColour.add("Blue");
        resultAnimals = BirdSearchTools.searchByBellyColour(listOfAnimals,chosenColour);
        assertEquals(3, resultAnimals.size());
    }

    @Test
    public void searchingByTwoValidColour(){
        List<String> chosenColour = new ArrayList<>();
        chosenColour.add("Blue");
        chosenColour.add("Black");
        resultAnimals = BirdSearchTools.searchByBellyColour(listOfAnimals,chosenColour);
        assertEquals(1, resultAnimals.size());
        assertEquals(animalTwo.getName(), resultAnimals.get(0).getName());
    }

    @Test
    public void searchingByInvalidColour(){
        List<String> chosenColour = new ArrayList<>();
        chosenColour.add("Green");
        resultAnimals = BirdSearchTools.searchByBellyColour(listOfAnimals,chosenColour);
        assertEquals(0, resultAnimals.size());
    }

    @Test
    public void searchingByThreeColours(){
        List<String> chosenColour = new ArrayList<>();
        chosenColour.add("Blue");
        chosenColour.add("Black");
        chosenColour.add("White");
        resultAnimals = BirdSearchTools.searchByBellyColour(listOfAnimals,chosenColour);
        assertEquals(0, resultAnimals.size());
    }



}
