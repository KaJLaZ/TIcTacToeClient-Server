package Dar.TipTapToeClientServer.core;

import lombok.NonNull;

public class Player {
    private char symbol;
    public char getSymbol() {
        return symbol;
    }

    private WinVersion[] winVersions;

    public WinVersion[] getWinVersions() {
        return winVersions;
    }

    public Player(char symbol, @NonNull WinVersion[] winVersions) {
        if(winVersions.length == 0)
            throw new IllegalArgumentException("massive winVersions are empty");

        for(WinVersion i : winVersions){
            if(i == null)
                throw new NullPointerException();
        }

        this.symbol = symbol;
        this.winVersions = winVersions;
    }

    public boolean isWin() {

        for (WinVersion i : winVersions) {

            if (i.getAmountCoinciden() == WinVersion.AMOUNT_CELLS_FOR_WIN )
                return true;
        }

        return false;
    }
}
