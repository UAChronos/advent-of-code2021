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

    //Split list in sublists
    static List<List<String>> splitList(List<String> input, String separator)
    {
        List<List<String>> result = new ArrayList<>();
        int prev = 0;

        for (int cur = 0; cur < input.size(); cur++)
        {
            if (input.get(cur).equals(separator))
            {
                result.add(input.subList(prev, cur));
                prev = cur + 1;
            }
        }
        result.add(input.subList(prev, input.size()));

        return result;
    }

    public static Board createBoard(List<String> values)
    {
        int[][] board = new int[5][5];
        boolean[][] boardState = new boolean[5][5];

        for (int i = 0; i < values.size(); i++)
        {
            String[] row = values.get(i).trim().replaceAll("  ", " ").split(" ");
            for (int j = 0; j < row.length; j++)
            {
                try
                {
                    board[i][j] = Integer.parseInt(row[j]);
                }
                catch (NumberFormatException e)
                {
                    for (int k = 0; k < row.length; k++)
                    {
                        System.out.println(row[k]);
                    }
                }
                boardState[i][j] = false;
            }
        }

        Board boardObject = new Board(board, boardState);

        return boardObject;
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        List<List<String>> puzzleInputLists = splitList(readFileInList("D:\\Coding\\Advent Of Code\\day4puzzleInput.txt"), "");
        //To access element at i position - puzzleInputList.get(i)

        //Read draw order from file
        String[] drawOrderString = puzzleInputLists.get(0).get(0).split(",");

        int[] drawOrder = new int[drawOrderString.length];

        for(int i = 0; i < drawOrderString.length; i++)
        {
            drawOrder[i] = Integer.parseInt(drawOrderString[i]);
        }

        //Create list of boards from file
        List<Board> boards = new ArrayList<Board>();

        for(int i = 1; i < puzzleInputLists.size(); i++)
        {
            boards.add(createBoard(puzzleInputLists.get(i)));
        }

        //Check if number from draw order is on board
        for(int i = 0; i < drawOrder.length; i++)
        {
            //For every set of boards
            for(int j = 0; j < boards.size(); j++)
            {
                boards.get(j).checkForNumber(drawOrder[i]);

                if(boards.get(j).checkForCompletion())
                {
                    System.out.println(boards.get(j).countPoints(drawOrder[i]));
                    return;
                }
            }
        }

        //System.out.println("Horizontal: " + var1 + " Depth: " + var2 + " Distance: " + var3);
    }
}
