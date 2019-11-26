package Dar.TipTapToeClientServer.core;

import lombok.NonNull;

public class Coordinate {
    private int rowNum;
    private int colNum;

    public int getRowNum() {
        return rowNum;
    }

    public int getColNum() {
        return colNum;
    }

    public Coordinate(int rowNum, int colNum) {
        this.rowNum = rowNum;
        this.colNum = colNum;
    }

    public boolean equals(@NonNull Object obj){
        if (!(obj instanceof Coordinate))
            return false;

        Coordinate coord = (Coordinate) obj;

        if (coord.getRowNum() != rowNum || coord.getColNum() != colNum)
            return false;

        return true;
    }
}
