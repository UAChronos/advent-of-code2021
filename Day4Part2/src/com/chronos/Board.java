package com.chronos;

import java.util.Arrays;

public class Board
{
    private int[][] board;
    private boolean[][] boardState;
    private boolean won = false;

    public Board(int[][] playField, boolean[][] playFieldState)
    {
        board = playField;
        boardState = playFieldState;
    }

    public int[][] getBoard()
    {
        return board;
    }

    public boolean[][] getState()
    {
        return boardState;
    }

    public boolean getWinState()
    {
        return won;
    }

    public void checkForNumber(int number)
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(board[i][j] == number)
                {
                    boardState[i][j] = true;
                }
            }
        }
    }

    public boolean checkForCompletion()
    {
        for(int i = 0; i < board.length; i++)
        {
            int columnCounter = 0;

            for(int j = 0; j < board[i].length; j++)
            {
                if(boardState[j][i] == true)
                {
                    columnCounter++;
                }

                if(columnCounter == 5)
                {
                    return true;
                }
            }
        }

        for(int i = 0; i < board.length; i++)
        {
            int rowCounter = 0;

            for(int j = 0; j < board[i].length; j++)
            {
                if(boardState[i][j] == true)
                {
                    rowCounter++;
                }

                if(rowCounter == 5)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public int countPoints(int winningNumber)
    {
        int sum = 0;

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(boardState[i][j] == false)
                {
                    sum += board[i][j];
                }
            }
        }

        won = true;

        return sum * winningNumber;
    }

    public String toString()
    {
        String stringified = "Board State" + System.lineSeparator();

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                stringified += board[i][j] + " ";
            }
            stringified += System.lineSeparator();
        }

        stringified += System.lineSeparator();

        for(int i = 0; i < boardState.length; i++)
        {
            for(int j = 0; j < boardState[i].length; j++)
            {
                stringified += boardState[i][j] + " ";
            }
            stringified += System.lineSeparator();
        }

        return stringified;
    }
}