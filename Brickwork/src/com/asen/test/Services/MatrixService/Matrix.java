package com.asen.test.Services.MatrixService;

import java.util.Arrays;
import java.util.Scanner;

public class Matrix implements MatrixMethods {
    private int rows;
    private int columns;
    private int[] dimensions;

    public Matrix(Scanner scanner) {

        this.dimensions = readRowsAndColumns(scanner, "\\s+");
        this.setSize(scanner);

    }

    private void setSize(Scanner scanner) {
        this.rows = this.dimensions[0];
        this.columns = this.dimensions[1];
        checkProperDimensions(scanner);
//size of the Matrix;
    }

    private int[] readRowsAndColumns(Scanner scanner, String reggex) {
        return Arrays.stream(scanner.nextLine().split(reggex)).mapToInt(Integer::parseInt).toArray();
        //We read the input of rows and columns (N and M)
    }

    private void checkProperDimensions(Scanner scanner) {
        while (validateSize(this.rows, this.columns)) {
            System.out.println("Insert proper params for N and M. They must be even numbers");
            this.dimensions = readRowsAndColumns(scanner, "\\s+");
            this.rows = dimensions[0];
            this.columns = dimensions[1];
        }
        //validation if the size are correct
    }

    private boolean validateSize(int row, int columns) {
        return row % 2 != 0 || columns % 2 != 0 || row > 100 || row < 2 || columns < 2 || columns > 100;
        //Method for size validation for Rows and columns
    }


    private int[][] getInputMatrix(Scanner scanner) {
        //Here we create the matrix and we do validations of the bricks input
        int[][] ma3xOne = new int[this.rows][this.columns];
        for (int row = 0; row < this.rows; row++) {
            int[] line = readRowsAndColumns(scanner, "\\s+");
            for (int col = 0; col < this.columns; col++) {
                if (col >= 2 && validateBigBricks(line[col], line[col - 1], line[col - 2])) {
                    System.out.println("Bricks can not be bigger than two cells!" +
                            "\nInsert breaks data again:");
                    return null;
                }
                if (col % 2 == 0 && brickParts(line[col], line[col + 1])) {
                    System.out.println("Brick two parts should be same number!" +
                            "\nInsert breaks data again:");
                    return null;
                }


                ma3xOne[row][col] = line[col];

            }
        }
        return ma3xOne;
    }

    private boolean validateBigBricks(int previously, int current, int next) {
        return (previously == current && previously == next);
        //validation for the bricks(Brick must be not more than 2 same numbers )


    }

    private boolean brickParts(int first, int second) {
        return (first != second);
        //validation for the bricks(Brick must be 2 same numbers in row)
    }

    public int[][] readMa3x(Scanner scanner) {
        int[][] ints = this.getInputMatrix(scanner);
        while (ints == null) {
            ints = this.getInputMatrix(scanner);
        }
        return ints;
        //In case some of the breaks are not correct we read data again.
    }
}
