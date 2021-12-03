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
        List<String> puzzleInputList = readFileInList("D:\\Coding\\Advent Of Code\\testFile.txt");

        int aim = 0;
        int depth = 0;
        int horizontalPos = 0;

        for(int i = 0; i < puzzleInputList.size(); i++)
        {
            String[] splitted = puzzleInputList.get(i).split(" ");

            if(splitted[0].equals("forward"))
            {
                horizontalPos += Integer.parseInt(splitted[1]);
                depth += aim * Integer.parseInt(splitted[1]);
            }
            else if(splitted[0].equals("down"))
            {
                aim += Integer.parseInt(splitted[1]);
            }
            else if(splitted[0].equals("up"))
            {
                aim -= Integer.parseInt(splitted[1]);
            }
        }

        int distance = depth * horizontalPos;
        System.out.println("Horizontal: " + horizontalPos + " Depth: " + depth + " Distance: " + distance);
    }
}