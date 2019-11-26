package Dar.core;

import Dar.TipTapToeClientServer.core.Coordinate;
import Dar.TipTapToeClientServer.core.Player;
import Dar.TipTapToeClientServer.core.WinVersion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerTest {
    WinVersion defWinVers;
    WinVersion[] defWinVersions;

    char defSymb = 'X';

    Player defPlayer;

    @BeforeEach
    public void initializeDefPlayer(){
        defWinVers = Mockito.mock(WinVersion.class);
        defWinVersions = new WinVersion[]{defWinVers};

        defPlayer = new Player(defSymb, defWinVersions);
    }
    @Test
    public void createPlayer(){
        Player player = new Player('X', defWinVersions);
    }
    @Test
    public void createPlayerWinVersionNull(){
        Assertions.assertThrows(NullPointerException.class, ()->{
            Player player = new Player('X', null);
        });
    }
    @Test
    public void createPlayerWithNullWinVersion(){
        Assertions.assertThrows(NullPointerException.class, ()->{
            Player player = new Player('X', null);
        });
    }
    @Test
    public void createPlayerWithEmptyWinVersion(){
        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            Player player = new Player('X', new WinVersion[]{});
        });
    }
    @Test
    public void createPlayerWithEmptyWinVersio(){
        Assertions.assertThrows(NullPointerException.class, ()->{
            Player player = new Player('X', new WinVersion[]{null});
        });
    }

    @Nested
    class getSymbol{
        @Test
        public void getInizializedSymbol(){
            Assertions.assertEquals(defPlayer.getSymbol(), defSymb);
        }
    }

    @Nested
    class isWin{
        @Test
        public void winEnoughMatches(){
            Mockito.when(defWinVers.getAmountCoinciden()).thenReturn(WinVersion.AMOUNT_CELLS_FOR_WIN);

            Assertions.assertTrue(defPlayer.isWin());
        }
        @Test
        public void notWinEnoughMatches(){
            Mockito.when(defWinVers.getAmountCoinciden()).thenReturn(WinVersion.AMOUNT_CELLS_FOR_WIN - 1);

            Assertions.assertFalse(defPlayer.isWin());
        }
    }
}
