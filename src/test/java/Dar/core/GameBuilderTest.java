package Dar.core;

import Dar.TipTapToeClientServer.core.Coordinate;
import Dar.TipTapToeClientServer.core.Game;
import Dar.TipTapToeClientServer.core.GameBuilder;
import Dar.TipTapToeClientServer.core.WinVersion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameBuilderTest {
    GameBuilder gameBuilder = new GameBuilder();
    @BeforeEach
    public void createGameBuilder(){
        gameBuilder = new GameBuilder();
    }

    @Test
    public void afterInizializationCanBuildGame(){
        Game game = gameBuilder.build();

        Assertions.assertNotEquals(game, null);
    }

    @Test
    public void buildWithFirstPlayer(){
        char firPlaSymb = 'L';
        int defRowNum = 1;
        int defColNum = 1;
        Coordinate coord = Mockito.mock(Coordinate.class);

        Mockito.when(coord.getRowNum()).thenReturn(defRowNum);
        Mockito.when(coord.getColNum()).thenReturn(defColNum);
        gameBuilder.withFirPlaySymb(firPlaSymb);
        Game game = gameBuilder.build();

        game.step(coord);

        Assertions.assertEquals(firPlaSymb, game.getField()[defRowNum][defColNum]);
    }
}
