package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {


        int [][] boardArray = new int [GRID_SIZE][GRID_SIZE];
        String filename = "puzzleName.csv";
        boardArray =readArrayFromFile(filename);

        System.out.println("Before solving the problem");
        printBoard(boardArray);

        if (solveBoard(boardArray)) {
            System.out.println("Solved successfully!");
        } else {
            System.out.println("Unsolvable board :(");
        }
        System.out.println("After solving the problem");
        printBoard(boardArray);

    }

    private static int[][] readArrayFromFile(String filename) {
        int[][] boardArray = new int[GRID_SIZE][GRID_SIZE];

        try {
            Scanner myFileReader = new Scanner(new File("C:\\Users\\SRamanjaneya\\Desktop\\Java Coding\\SodukuSolver\\src\\main\\java\\org\\example\\ puzzleName.csv"));
            int i = 0;
            while (myFileReader.hasNextLine()) {

                String line = myFileReader.nextLine();
                String[] tokens = line.split(",");

                for (int j = 0; j < tokens.length; j++) {
                    tokens[j] = tokens[j].trim();
                    boardArray[i][j] = Integer.valueOf(tokens[j]);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return boardArray;
    }


    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }


    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
                !isNumberInColumn(board, number, column) &&
                !isNumberInBox(board, number, row, column);
    }

    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) {
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            if (solveBoard(board)) {
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


}
