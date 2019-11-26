package Dar.TipTapToeClientServer.core;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Collections;

public class WinVersion {
    private int amountCoinciden;

    public int getAmountCoinciden() {
        return amountCoinciden;
    }

    private ArrayList<Coordinate> coordinates;

    public final static int AMOUNT_CELLS_FOR_WIN = 3;

    public WinVersion(@NonNull Coordinate... coordinates) {
        if (coordinates.length != AMOUNT_CELLS_FOR_WIN)
            throw new IllegalArgumentException("arguments less then necessary");

        for(Coordinate i : coordinates){
            if(i == null)
                throw new NullPointerException("Coordinate is null");
        }

        this.coordinates = new ArrayList<>();
        Collections.addAll(this.coordinates, coordinates);

        amountCoinciden = 0;
    }


    public void refresh(@NonNull Coordinate coordMove) {
        for (Coordinate j : coordinates) {

            if (j.equals(coordMove))
                amountCoinciden++;
        }
    }

    public static WinVersion[] refreshWinVersions(@NonNull WinVersion[] winVersions, @NonNull Coordinate coord){
        for(WinVersion i : winVersions)
            i.refresh(coord);

        return winVersions;
    }
}


