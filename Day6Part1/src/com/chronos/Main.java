package com.chronos;

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashMap;

public class Main {
    public static HashMap<String, Long> existingFishes = new HashMap<String, Long>();

    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            // do something
            e.printStackTrace();
        }
        return lines;
    }

    public static long createFish(int lifeCycle, int daysToBirth){
        Fish newFish = new Fish(lifeCycle, daysToBirth);
        return newFish.countDescendants(existingFishes);
    }

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Integer> fishPopulation = new ArrayList<>();


        int days = 256;

        String[] initialPopulation = readFileInList("D:\\Coding\\Advent Of Code\\day6puzzleInput.txt").get(0).split(",");

        for(int i = 0; i < initialPopulation.length; i++){
            fishPopulation.add(Integer.parseInt(initialPopulation[i]));
        }

        long entireFishPopulation = fishPopulation.size();

        for(int i = 0; i < fishPopulation.size(); i++){
            entireFishPopulation += createFish(days, fishPopulation.get(i));
        }

        System.out.println(entireFishPopulation);
    }
}