package Dar.core;

import Dar.TipTapToeClientServer.core.Coordinate;
import Dar.TipTapToeClientServer.core.WinVersion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WinVersionTest {
    Coordinate defCoord;
    Coordinate[] defCoords;

    WinVersion defWinVersion;

    @BeforeEach
    public void inizializeDefWinVers(){
        defCoord = Mockito.mock(Coordinate.class);
        defCoords = new Coordinate[]{defCoord, defCoord, defCoord};

        defWinVersion = new WinVersion(defCoords);
    }
    @Test
    public void createWinVersion(){
        WinVersion winVers = new WinVersion(defCoords);
    }
    @Test
    public void createWinVersionFewerCoordinatesThenNecessary(){
        Coordinate[] coords = new Coordinate[WinVersion.AMOUNT_CELLS_FOR_WIN - 1];

        for (int i = 0 ; i < WinVersion.AMOUNT_CELLS_FOR_WIN - 1; i++)
            coords[i] = defCoord;

        Assertions.assertThrows(IllegalArgumentException.class, ()->{
            WinVersion winVersion = new WinVersion(coords);
        });
    }
    @Test
    public void createWinWithNull(){
        Assertions.assertThrows(NullPointerException.class, ()->{
            WinVersion winVersion = new WinVersion(null);
        });
    }
    @Test
    public void createWinWithEmptyCoordinates(){
        Coordinate[] coords = new Coordinate[]{null, null, null};
        Assertions.assertThrows(NullPointerException.class, ()->{
            WinVersion winVersion = new WinVersion(coords);
        });
    }

    @Nested
    class getAmountCoinciden{
        @Test
        public void getInitialAmountCoinciden(){
            int expected = 0;

            Assertions.assertEquals(expected, defWinVersion.getAmountCoinciden());
        }
        @Test
        public void getInitialAmountCoincidenAfterSucsecsfullRefresh(){
            Coordinate coord = new Coordinate(1,1);

            WinVersion winVersion = new WinVersion(coord, defCoord, defCoord);
            int expected = 1;

            winVersion.refresh(coord);

            Assertions.assertEquals(expected, winVersion.getAmountCoinciden());
        }
    }

    @Nested
    class refresh{
        @Test
        public void refreshWithUnsecsesfullCoord(){
            int expected = 0;

            defWinVersion.refresh(Mockito.mock(Coordinate.class));

            Assertions.assertEquals(expected, defWinVersion.getAmountCoinciden());
        }
        @Test
        public void refreshWithNullCoord(){
            Assertions.assertThrows(NullPointerException.class, ()->{
                defWinVersion.refresh(null);
            });
        }
    }
    @Nested
    class refreshWinVersions{
        WinVersion[] defWinVersions;

        @BeforeEach
        public void inizializeWinVersions(){
            defWinVersions = new WinVersion[]{defWinVersion};
        }
        @Test
        public void refreshWithSuccessfulCoord(){
            int expected = 3;

            WinVersion.refreshWinVersions(defWinVersions, defCoord);

            Assertions.assertEquals(expected, defWinVersion.getAmountCoinciden());
        }
        @Test
        public void refreshWithUnsuccessfulCoord(){
            int expected = 0;

            WinVersion.refreshWinVersions(defWinVersions, Mockito.mock(Coordinate.class));

            Assertions.assertEquals(expected, defWinVersion.getAmountCoinciden());
        }
        @Test
        public void refreshWithNullCoord(){
            Assertions.assertThrows(NullPointerException.class, ()->{
                WinVersion.refreshWinVersions(defWinVersions,null);
            });
        }
        @Test
        public void refreshWithEmptyWinVersion(){
            WinVersion.refreshWinVersions(new WinVersion[]{}, defCoord);
        }
        @Test
        public void refreshWithNullWinVersion(){
            Assertions.assertThrows(NullPointerException.class, ()->{
                WinVersion.refreshWinVersions(null,defCoord);
            });
        }
    }
}
