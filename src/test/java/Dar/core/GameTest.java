package Dar.core;

import Dar.TipTapToeClientServer.core.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameTest {
    Player defFirPlayer;
    Player defSecPlayer;

    Game defGame;

    @BeforeEach
    public void initializeGame(){
        defFirPlayer = Mockito.mock(Player.class);
        defSecPlayer = Mockito.mock(Player.class);
        Mockito.when(defFirPlayer.getSymbol()).thenReturn('X');
        Mockito.when(defSecPlayer.getSymbol()).thenReturn('O');

        defGame = new Game(defFirPlayer, defSecPlayer);
    }
    @Test
    public void createGame(){
        Game game = new Game(defFirPlayer, defSecPlayer);
    }
    @Test
    public void createGameWithSamePlayersSymbols(){
        Mockito.when(defFirPlayer.getSymbol()).thenReturn('X');
        Mockito.when(defSecPlayer.getSymbol()).thenReturn('X');

        Assertions.assertThrows(Game.IncorectInputException.class, ()->{
            Game game = new Game(defFirPlayer, defSecPlayer);
        });
    }
    @Test
    public void createGameWithNullPlayer(){
        Assertions.assertThrows(NullPointerException.class, ()->{
            Game game = new Game(null, defSecPlayer);
        });
    }
    @Test
    public void createGameWithReservedSymbol(){
        Mockito.when(defFirPlayer.getSymbol()).thenReturn(' ');
        Mockito.when(defSecPlayer.getSymbol()).thenReturn('X');

        Assertions.assertThrows(Game.IncorectInputException.class, ()->{
            Game game = new Game(defFirPlayer, defSecPlayer);
        });
    }

    @Test
    public void getStartField(){
        char[][] expected = new char[][]{{' ', ' ', ' '},
                                         {' ', ' ', ' '},
                                         {' ', ' ', ' '}};

        Assertions.assertArrayEquals(expected, defGame.getField());
    }

    @Test
    public void getAmountCells(){
        int expected = Game.AMOUNT_COLUMNS * Game.AMOUNT_ROWS;

        Assertions.assertEquals(expected, Game.getAmountCells());
    }

    @Nested
    class Step{
        int defRowNum = 1;
        int defColNum = 1;
        Coordinate defCoord;

        Player defFirPlayer;
        Player defSecPlayer;

        Game defGame;

        @BeforeEach
        public void initializeGame(){
            defCoord = Mockito.mock(Coordinate.class);

            Mockito.when(defCoord.getRowNum()).thenReturn(defRowNum);
            Mockito.when(defCoord.getColNum()).thenReturn(defColNum);

            WinVersion[] winVersions = new WinVersion[]{new WinVersion(defCoord, defCoord, defCoord)};

            defFirPlayer = new Player('X', winVersions);
            defSecPlayer = new Player('O', winVersions);

            defGame = new Game(defFirPlayer, defSecPlayer);
        }


        @Test
        public void step(){
            defGame.step(defCoord);
            Assertions.assertEquals(defFirPlayer.getSymbol(), defGame.getField()[defRowNum][defColNum]);
        }
        @Test
        public void stepAbroad(){
            Mockito.when(defCoord.getRowNum()).thenReturn(100);
            Mockito.when(defCoord.getColNum()).thenReturn(100);

            Assertions.assertThrows(Game.IncorectInputException.class, ()->{
                defGame.step(defCoord);
            });
        }
        @Test
        public void stepInReservedPlace(){
            Mockito.when(defCoord.getRowNum()).thenReturn(1);
            Mockito.when(defCoord.getColNum()).thenReturn(1);

            Assertions.assertThrows(Game.IncorectInputException.class, ()->{
                defGame.step(defCoord);
                defGame.step(defCoord);
            });
        }
        @Test
        public void stepNullCoord(){
            Mockito.when(defCoord.getRowNum()).thenReturn(1);
            Mockito.when(defCoord.getColNum()).thenReturn(1);

            Assertions.assertThrows(NullPointerException.class, ()->{
                defGame.step(null);
            });
        }
        @Test
        public void ifCanStapReturnTrue(){
            Mockito.when(defCoord.getRowNum()).thenReturn(1);
            Mockito.when(defCoord.getColNum()).thenReturn(1);

            Assertions.assertTrue(defGame.step(defCoord));
        }
    }
    @Test
    public void draw(){
        Game game = new GameBuilder().build();

        Assertions.assertTrue(game.step(new Coordinate(0,0)));
        Assertions.assertTrue(game.step(new Coordinate(1,0)));

        Assertions.assertTrue(game.step(new Coordinate(0,1)));
        Assertions.assertTrue(game.step(new Coordinate(1,1)));

        Assertions.assertTrue(game.step(new Coordinate(1,2)));
        Assertions.assertTrue(game.step(new Coordinate(0,2)));

        Assertions.assertTrue(game.step(new Coordinate(2,0)));
        Assertions.assertTrue(game.step(new Coordinate(2,1)));

        Assertions.assertFalse(game.step(new Coordinate(2,2)));
    }

    @Test
    public void firstPlaywin(){
        Game game = new GameBuilder().build();

        Assertions.assertTrue(game.step(new Coordinate(0,0)));
        Assertions.assertTrue(game.step(new Coordinate(1,0)));

        Assertions.assertTrue(game.step(new Coordinate(0,1)));
        Assertions.assertTrue(game.step(new Coordinate(1,1)));

        Assertions.assertFalse(game.step(new Coordinate(0,2)));
    }
}
