package Dar.TipTapToeClientServer.core;

import lombok.NonNull;

import java.util.Collection;

public class Game {
    private char[][] field;

    public char[][] getField() { return field;}

    private Player firPlayer;
    private Player secPlayer;

    private Player currentPlayer;

    private int amountSteps;

    public static final int AMOUNT_ROWS = 3;
    public static final int AMOUNT_COLUMNS = 3;
    public static final int AMOUNT_WIN_VARIANTS = 8;

    public static int getAmountCells(){ return AMOUNT_ROWS * AMOUNT_COLUMNS; }

    public Game(@NonNull Player firPlayer, @NonNull Player secPlayer){
        if (firPlayer.getSymbol() == secPlayer.getSymbol())
            throw new IncorectInputException("symbols are same");

        if (firPlayer.getSymbol() == ' ' || secPlayer.getSymbol() == ' ')
            throw new IncorectInputException("symbol are reserved");

        this.firPlayer = firPlayer;
        this.secPlayer = secPlayer;

        field = new char[][]{{' ', ' ', ' '},
                             {' ', ' ', ' '},
                             {' ', ' ', ' '}};

        currentPlayer = firPlayer;
    }

    public boolean step(@NonNull Coordinate coord){
        if (coord.getRowNum() >= AMOUNT_ROWS || coord.getColNum() >= AMOUNT_COLUMNS)
            throw new IncorectInputException("going abroad");

        if (!isAvailable(field[coord.getRowNum()][coord.getColNum()]))
            throw new IncorectInputException("place is unavailable");

        if (firPlayer.isWin() || secPlayer.isWin())
            return false;

        if (amountSteps == getAmountCells())
            return false;

        WinVersion.refreshWinVersions(currentPlayer.getWinVersions(), new Coordinate(coord.getRowNum(), coord.getColNum()));
        field[coord.getRowNum()][coord.getColNum()] = currentPlayer.getSymbol();
        amountSteps++;

        nextPlayer();
        return true;
    }

    private boolean isAvailable(char symb){
        return symb == ' ';
    }

    private void nextPlayer(){
        if (currentPlayer == firPlayer)
            currentPlayer = secPlayer;

        else
            currentPlayer = firPlayer;
    }

    public class IncorectInputException extends RuntimeException{
        public IncorectInputException(String message){
            super(message);
        }

        @Override
        public IncorectInputException getCause(){
            return this;
        }
    }
}
