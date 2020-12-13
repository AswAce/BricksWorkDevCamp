package com.asen.test.Entities;

import java.util.ArrayList;

public class AllBricks implements AllBLocksMethods {
    private ArrayList<Brick> bricks;

    //A class that contains all brick in arrayList from where
    // we are going to take them when we start the constructions
    public AllBricks() {
        this.bricks = new ArrayList<>();
    }

    private void addBlock(Brick block) {
        this.bricks.add(block);
    }

    @Override
    public void createBLocks(int[][] matrix) {

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c += 2) {
                if (r % 2 == 0) {
                    if ((c % 4 != 0 || c == 0) && c != 2) {
                        Brick block = new Brick(matrix[r][c], false, true, r, c / 2);
                        this.addBlock(block);
                    } else {
                        Brick block = new Brick(matrix[r][c], true, true, r, c / 2);
                        this.addBlock(block);
                    }

                } else {
                    if (c % 4 == 0) {
                        Brick block = new Brick(matrix[r][c], false, false, r, c / 2);
                        this.addBlock(block);
                    } else {
                        Brick block = new Brick(matrix[r][c], true, false, r, c / 2);
                        this.addBlock(block);
                    }

                }
            }
        }

    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }
}
