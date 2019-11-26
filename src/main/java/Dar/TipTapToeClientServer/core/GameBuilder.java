package Dar.TipTapToeClientServer.core;

import java.util.Collections;

public class GameBuilder {
    private Player firPlayer;
    private Player secPlayer;

    public GameBuilder() {
        firPlayer = new Player('X', getWinVersion());
        secPlayer = new Player('O', getWinVersion());
    }

    public GameBuilder withFirPlaySymb(char firPlaySymb){
        firPlayer = new Player(firPlaySymb, getWinVersion());
        return this;
    }

    public GameBuilder withSecPlaySymb(char secPlaySymb){
        secPlayer = new Player(secPlaySymb, getWinVersion());
        return this;
    }

    public Game build(){
        return new Game(firPlayer, secPlayer);
    }

    private static WinVersion[] getWinVersion() {
        Coordinate[][] coordNet = getNetCoordinates(Game.AMOUNT_ROWS, Game.AMOUNT_COLUMNS);

        WinVersion[] winVersions = new WinVersion[Game.AMOUNT_WIN_VARIANTS];

        winVersions[0] = new WinVersion(coordNet[0][0], coordNet[0][1], coordNet[0][2]);
        winVersions[1] = new WinVersion(coordNet[1][0], coordNet[1][1], coordNet[1][2]);
        winVersions[2] = new WinVersion(coordNet[2][0], coordNet[2][1], coordNet[2][2]);
        winVersions[3] = new WinVersion(coordNet[0][0], coordNet[1][0], coordNet[2][0]);
        winVersions[4] = new WinVersion(coordNet[0][1], coordNet[1][1], coordNet[2][1]);
        winVersions[5] = new WinVersion(coordNet[0][2], coordNet[1][2], coordNet[2][2]);
        winVersions[6] = new WinVersion(coordNet[0][0], coordNet[1][1], coordNet[2][2]);
        winVersions[7] = new WinVersion(coordNet[2][0], coordNet[1][1], coordNet[0][2]);

        return winVersions;
    }

    private static Coordinate[][] getNetCoordinates(int amRows, int amColumns) {
        Coordinate[][] netCoordinates = new Coordinate[amRows][amColumns];

        for (int i = 0; i < amRows; i++) {

            for (int j = 0; j < amColumns; j++) {
                netCoordinates[i][j] = new Coordinate(i, j);
            }
        }

        return netCoordinates;
    }
}
