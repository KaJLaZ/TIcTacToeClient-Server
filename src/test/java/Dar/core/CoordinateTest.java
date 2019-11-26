package Dar.core;

import Dar.TipTapToeClientServer.core.Coordinate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class CoordinateTest {
    int defRowNum = 1;
    int detDefColNum = 1;
    Coordinate defCoord;
    @BeforeEach
    public void initializeDefaultCoord(){
        defCoord = new Coordinate(defRowNum, detDefColNum);
    }
    @Test
    public void createCoordinate(){
        Coordinate coordinate = new Coordinate(1, 1);
    }
    @Test
    public void createCoordinateWithNegativeParam(){
        Coordinate coordinate = new Coordinate(-1, -1);
    }

    @Nested
    class equals{
        @Test
        public void equalsSameObj(){
            Assertions.assertTrue(defCoord.equals(defCoord));
        }
        @Test
        public void equalsNotSameObjWithSameParam(){
            Coordinate coord = new Coordinate(defRowNum, detDefColNum);
            Assertions.assertTrue(defCoord.equals(coord));
        }
        @Test
        public void equalsDifObjDifParam(){
            Coordinate coord = new Coordinate(++defRowNum, ++detDefColNum);
            Assertions.assertFalse(defCoord.equals(coord));
        }
        @Test
        public void equalsNull(){
            Assertions.assertThrows(NullPointerException.class, ()->{
               defCoord.equals(null);
            });
        }
    }
}

