package com.asen.test.Services.BrickCreationService;

import com.asen.test.Entities.Brick;

import java.util.ArrayList;

public class BricksCreation {
    private int[][] bricks;
    private ArrayList<Brick> listBricks;
    //bricks we have
    private ArrayList<ArrayList<String>> arrayBricks;

    //constructed brick.
//This is the class where we are creating the constructions from the brick;
    public BricksCreation(int[][] bricks, ArrayList<Brick> listBricks) {
        this.bricks = bricks;
        this.listBricks = listBricks;
        this.arrayBricks = getArray();
    }

    public void createTheBricksConstruction() {
//this is the method where we build the bricks;
        try {
            for (int i = 0; i < listBricks.size(); i++) {
                //we go thru all the bricks in our collection.
                Brick brick = listBricks.get(i);
                if (brick.isEven()) {
                    //first we check on what type of row we are
                    if (brick.isFlip()) {
                        //if the brick is vertical
                        // we are going to put it on current row and the row above for even row
                        if (brick.getColumn() == 1) {
                            //special case the second brick on even row is flipped in the begining
                            arrayBricks.get(brick.getRow()).set(0, String.valueOf(brick.getType()));
                            arrayBricks.get(brick.getRow() + 1).set(0, String.valueOf(brick.getType()));
                        }
                        if (brick.getColumn() % 2 == 0) {

                            arrayBricks.get(brick.getRow()).set((brick.getColumn() * 2), String.valueOf(brick.getType()));
                            arrayBricks.get(brick.getRow() + 1).set((brick.getColumn() * 2), String.valueOf(brick.getType()));
                            ///  x->x*2... where x is the column number from the brick class
                            // and x*2 is where we are going to put the brick into the new Two dimension array;

                        }

                    } else {
                        if (brick.getColumn() == 0) {
                            arrayBricks.get(brick.getRow()).set(1, String.valueOf(brick.getType()));
                            arrayBricks.get(brick.getRow()).set(2, String.valueOf(brick.getType()));
                            //special case if brick is even row and is the first one her positions is 1 and 2
                            //into the new Two dimension array;
                        } else {
                            arrayBricks.get(brick.getRow()).set(brick.getColumn() * 2, String.valueOf(brick.getType()));
                            arrayBricks.get(brick.getRow()).set((brick.getColumn() * 2) - 1, String.valueOf(brick.getType()));

                            // Here we are setting horizontal bricks for even row this is the formula:
                            // x->x*2, x*2-1 ...... where x in the column from the brick class and
                            //x*2 is the the position  into the new Two dimension array and one position behind;

                        }
                    }
                } else {
                    //this are bricks with odd row
                    if (!brick.isFlip()) {
                        //horizontal bricks
                        arrayBricks.get(brick.getRow()).set((brick.getColumn() * 2) + 1, String.valueOf(brick.getType()));
                        arrayBricks.get(brick.getRow()).set((brick.getColumn() * 2) + 2, String.valueOf(brick.getType()));
                        // Here we are setting horizontal bricks for odd row this is the formula:
                        //x->x*2 +1 and x->x*2+2
                    } else {
                        //vertical bricks
                        //on the odd rows we need to put the bricks up covering part of the upper row
                        arrayBricks.get(brick.getRow()).set((brick.getColumn() * 2) + 1, String.valueOf(brick.getType()));
                        arrayBricks.get(brick.getRow() - 1).set((brick.getColumn() * 2) + 1, String.valueOf(brick.getType()));
                        //We are putting x->x*2+1 for our odd row also for the previous even row;

                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            //in case the construction can't be done we return error.

            System.out.println("-1");
            return;
        }


        ArrayList<ArrayList<String>> arrayLists = this.addStarts();
        System.out.println("Clean construction:");
        this.printFinalBricks(arrayBricks);

        System.out.println("\nWith stars:");


        this.printFinalBricks(arrayLists);
//
    }

    private ArrayList<ArrayList<String>> getArray() {

        int rows = this.bricks.length;
        int columns = this.bricks[0].length;
        ArrayList<ArrayList<String>> getFromCLass = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            ArrayList<String> row = new ArrayList<>();
            for (int c = 0; c < columns; c++) {
                row.add("0");
            }
            getFromCLass.add(row);
        }
        return getFromCLass;
    }
//First we create empty 2 dimension arrayList to be easier for us to
    // move between the rows

    private void printFinalBricks(ArrayList<ArrayList<String>> bricks) {
        //This is the method for printing a Two dimensional ArrayList
        for (int r = 0; r < bricks.size(); r++) {

            for (int col = 0; col < bricks.get(r).size(); col++) {
                System.out.print(bricks.get(r).get(col) + " ");
                //Print every col on same row.
            }
            //at the end of the row we go on the next line.
            System.out.println();
        }

    }


    private ArrayList<ArrayList<String>> addStarts() {
        //After the creation of the Two dimension ArrayList
        //(The construction) we are going to insert the stars.
        //On thing to keep in mind if a brick is with 2 digit numbers(14 14)
        //The method is going to create 3 stars above them not 5;
        ArrayList<ArrayList<String>> starWithBricks = new ArrayList<>();

        for (int row = 0; row < this.arrayBricks.size(); row++) {


            ArrayList<String> rowBricksStar = new ArrayList<>();
            for (int col = 0; col < this.arrayBricks.get(0).size(); col++) {
                if (col == 0) {
                    rowBricksStar.add("*");
                }
                if (col + 1 < this.arrayBricks.get(0).size()
                        && this.arrayBricks.get(row).get(col).equals(this.arrayBricks.get(row).get(col + 1))) {
//
                    rowBricksStar.add(this.arrayBricks.get(row).get(col));
                    rowBricksStar.add(" ");
                } else {
                    rowBricksStar.add(this.arrayBricks.get(row).get(col));
                    rowBricksStar.add("*");
                }
            }
            starWithBricks.add(rowBricksStar);
        }
        starWithBricks.add(0, getStarsRow(starWithBricks.get(0).size()));
        for (int rows = 0; rows < starWithBricks.size(); rows++) {
            if (rows % 2 == 0 && rows > 0) {
                starWithBricks.add(rows, getStarsRow(starWithBricks.get(0).size()));
            }
        }
        for (int rows = 0; rows < starWithBricks.size(); rows++) {
            for (int col = 0; col < starWithBricks.get(0).size(); col++) {
                if (rows + 2 < starWithBricks.size()) {
                    String currentBrickPart = starWithBricks.get(rows).get(col);
                    String downBrickPart = starWithBricks.get(rows + 2).get(col);
                    if (!currentBrickPart.equals("*") && !currentBrickPart.equals(" ") &&
                            !downBrickPart.equals("*") && !downBrickPart.equals(" ") &&
                            Integer.parseInt(currentBrickPart) == Integer.parseInt(downBrickPart)
                    ) {
                        starWithBricks.get(rows + 1).set(col, " ");
                    }
                }
            }

        }
        starWithBricks.add(getStarsRow(starWithBricks.get(0).size()));
        return starWithBricks;
    }

    private ArrayList<String> getStarsRow(int size) {
        //depending of the size we create a row only with stars;
        ArrayList<String> starts = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            starts.add("*");
        }

        return starts;
    }
}
