package com.asen.test.Entities;

public class Brick implements BrickGetters {
    //this is the class brick here we create every brick depending of the input data.
    private int type;
    //number of the brick
    private boolean flip;
    //is the brick vertical or horizontal
    private boolean isEven;
    // is the row even or odd
    private int row;
    //on which row is the brick
    private int column;
    //on which column is the brick

    public Brick(int type, boolean flip, boolean isEven, int row, int column) {
        this.type = type;
        this.flip = flip;
        this.isEven = isEven;
        this.row = row;
        this.column = column;

    }

    public int getType() {
        return type;
    }

    public boolean isFlip() {
        return flip;
    }

    public boolean isEven() {
        return isEven;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
