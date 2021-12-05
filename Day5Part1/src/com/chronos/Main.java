package com.chronos;

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class Main {
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

    public static void main(String[] args) throws FileNotFoundException {
        List<String> puzzleInputList = readFileInList("D:\\Coding\\Advent Of Code\\day5puzzleInput.txt");

        List<String> lines = new ArrayList<>();
        //line [startX, startY, endX, endY]

        for (int i = 0; i < puzzleInputList.size(); i++) {
            lines.add(puzzleInputList.get(i).replaceAll(" -> ", ","));
            //System.out.println(lines.get(i));
        }

        //Find biggest and smallest
        int[] xs = new int[lines.size() * 2];
        int[] ys = new int[lines.size() * 2];

        int maxX, minX, maxY, minY;

        for (int i = 0; i < lines.size() * 2; i += 2) {
            xs[i] = Integer.parseInt(lines.get(i / 2).split(",")[0]);
            xs[i + 1] = Integer.parseInt(lines.get(i / 2).split(",")[2]);

            ys[i] = Integer.parseInt(lines.get(i / 2).split(",")[1]);
            ys[i + 1] = Integer.parseInt(lines.get(i / 2).split(",")[3]);
        }

        maxX = xs[0];
        minX = xs[0];
        maxY = ys[0];
        minY = ys[0];

        for (int i = 0; i < xs.length; i++) {
            if (xs[i] > maxX) {
                maxX = xs[i];
            }
            if (xs[i] < minX) {
                minX = xs[i];
            }
            if (ys[i] > maxY) {
                maxY = ys[i];
            }
            if (ys[i] < minY) {
                minY = ys[i];
            }
        }

        int[][] map = new int[maxX - minX + 100][maxY - minY + 100];

        for (int i = 0; i < lines.size(); i++) {
            int x1 = Integer.parseInt(lines.get(i).split(",")[0]);
            int x2 = Integer.parseInt(lines.get(i).split(",")[2]);

            int y1 = Integer.parseInt(lines.get(i).split(",")[1]);
            int y2 = Integer.parseInt(lines.get(i).split(",")[3]);
            if (y1 == y2) {
                if (x1 > x2) {
                    for (int j = x2; j <= x1; j++) {
                        map[y1][j]++;
                    }
                } else {
                    for (int j = x1; j <= x2; j++) {
                        map[y1][j]++;
                    }
                }
            }
            if (x1 == x2) {
                if (y1 > y2) {
                    for (int j = y2; j <= y1; j++) {
                        map[j][x1]++;
                    }
                } else {
                    for (int j = y1; j <= y2; j++) {
                        map[j][x1]++;
                    }
                }
            }
        }

        int counter = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] >= 2) {
                    counter++;
                }
            }
        }

        System.out.println(counter);
    }
}