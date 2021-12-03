package com.chronos;
import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Main
{
    public static List<String> readFileInList(String fileName)
    {

        List<String> lines = Collections.emptyList();
        try
        {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }

        catch (IOException e)
        {
            // do something
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        int gamma, epsilon, powerConsumption;
        int commonBit1Counter = 0;
        int commonBit0Counter = 0;

        String gammaBinary = "";
        //String epsilonBinary = "";

        List<String> puzzleInputList = readFileInList("D:\\Coding\\Advent Of Code\\day3puzzleInput.txt");
        //To access element at i position - puzzleInputList.get(i)
        for(int i = 0; i < puzzleInputList.get(i).length(); i++)
        {
            for(int j = 0; j < puzzleInputList.size(); j++)
            {
                if(puzzleInputList.get(j).charAt(i) == '1')
                {
                    commonBit1Counter++;
                }
                else
                {
                    commonBit0Counter++;
                }
            }

            if(commonBit1Counter > commonBit0Counter)
            {
                gammaBinary += "1";
            }
            else
            {
                gammaBinary += "0";
            }

            commonBit1Counter = 0;
            commonBit0Counter = 0;
        }

        gamma = Integer.valueOf(gammaBinary, 2);
        epsilon = Integer.valueOf(gammaBinary.replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1"), 2);
        powerConsumption = gamma * epsilon;

        System.out.println("Multiplying the gamma rate (" + gamma + ") by the epsilon rate (" + epsilon + ") produces the power consumption, " + powerConsumption);
    }
}
