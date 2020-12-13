package com.asen.test.Services.ConstructionService;

import com.asen.test.Entities.AllBricks;
import com.asen.test.Entities.Brick;
import com.asen.test.Services.BrickCreationService.BricksCreation;
import com.asen.test.Services.MatrixService.Matrix;

import java.util.ArrayList;
import java.util.Scanner;

public class ConstructionWork implements ConstructionWorkMethods {
    //this is the class that create all other classes and services
    private Matrix matrix;
    private AllBricks allBricks;
    BricksCreation bricksCreation;

    public ConstructionWork(Scanner scanner) {
        this.matrix = new Matrix(scanner);
        this.allBricks = new AllBricks();

    }

    public void createConstruction(Scanner scanner) {
        //setting up data for all classes;
        int[][] bricks = this.matrix.readMa3x(scanner);
        this.allBricks.createBLocks(bricks);
        ArrayList<Brick> bricksArray = allBricks.getBricks();
        this.brickCreationWay(bricks, bricksArray);
        this.printConstruction();
    }

    private void brickCreationWay(int[][] bricks, ArrayList<Brick> bricksArray) {
        this.bricksCreation = new BricksCreation(bricks, bricksArray);
        //provide the data for the creation of the Two dimensional ArrayList
    }

    private void printConstruction() {
        this.bricksCreation.createTheBricksConstruction();
        //we are printing the result from the class that created the Two dimensional ArrayList
    }
}
