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

    public static void listAnalyzer(List<String> list, boolean inverted)
    {
        int commonBit1Counter = 0;
        int commonBit0Counter = 0;
        String commonBit;

        for(int i = 0; i < list.get(0).length(); i++)
        {
            //Iterate through bit values at index i, determining common bit
            for (int j = 0; j < list.size(); j++)
            {
                if (list.get(j).charAt(i) == '1')
                {
                    commonBit1Counter++;
                }
                else
                {
                    commonBit0Counter++;
                }
            }

            if (!inverted)
            {
                if (commonBit1Counter >= commonBit0Counter)
                {
                    commonBit = "1";
                }
                else
                {
                    commonBit = "0";
                }
            }
            else
            {
                if (commonBit1Counter >= commonBit0Counter)
                {
                    commonBit = "0";
                }
                else
                {
                    commonBit = "1";
                }
            }

            //Iterate through bit values at index i, determining if it equals common bit
            for (int j = 0; j < list.size() && list.size() != 1; j++)
            {
                if (list.get(j).charAt(i) != commonBit.charAt(0))
                {
                    list.remove(j);
                    j = 0;
                }
            }

            //Reset counters
            commonBit1Counter = 0;
            commonBit0Counter = 0;
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        int oxygenGenerationRate, co2scrubberRating, lifeSupportRating;

        List<String> puzzleInputList = readFileInList("D:\\Coding\\Advent Of Code\\day3puzzleInput.txt");
        List<String> puzzleInputListCopy = new ArrayList<>(puzzleInputList);
        //To access element at i position - puzzleInputList.get(i)

        listAnalyzer(puzzleInputList, false);
        listAnalyzer(puzzleInputListCopy, true);

        oxygenGenerationRate = Integer.valueOf(puzzleInputList.get(0), 2);
        co2scrubberRating = Integer.valueOf(puzzleInputListCopy.get(0), 2);
        lifeSupportRating = oxygenGenerationRate * co2scrubberRating;

        System.out.println("Multiplying the oxygen generator rating (" + oxygenGenerationRate + ") by the CO2 scrubber rating (" + co2scrubberRating + ") life support rating of " + lifeSupportRating);
    }
}
