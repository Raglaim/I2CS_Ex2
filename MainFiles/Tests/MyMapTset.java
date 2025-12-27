package assignments.Ex2.MainFiles.Tests;

import assignments.Ex2.MainFiles.Classes.Index2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Map2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Pixel2D;
import assignments.Ex2.MainFiles.Classes.MyMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MyMapTset {

    int [][] RESULT;
    Map2D M;

    @BeforeEach
    void setUp() {
        RESULT = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        M = new MyMap();
    }


    @Test
    void testMapElad() {
        Map2D mapResult = new MyMap(RESULT);
        Map2D m0 = new MyMap();
        Map2D m1 = new MyMap(10,10,0);
        Map2D m2 = new MyMap(10);
        assertEquals(m0, mapResult);
        assertEquals(m1, mapResult);
        assertEquals(m2, mapResult);
    }

    @Test
    void testInitElad() {
        int[][] m1 = {};
        int[][] m2 = {{1,2},{1}};
        assertThrows(RuntimeException.class, () -> M.init(null));
        assertThrows(RuntimeException.class, () -> M.init(m1));
        assertThrows(RuntimeException.class, () -> M.init(m2));
        Map2D m = new MyMap();
        m.init(RESULT);
        assertEquals(M, m);
        m.init(10,10,0);
        assertEquals(M, m);
    }

    @Test
    void testGetMapElad() {
        assertTrue(Arrays.deepEquals(RESULT,M.getMap()));
    }

    @Test
    void testGetWidthElad() {
        assertEquals(10,M.getWidth());
    }

    @Test
    void testGetHeightElad() {
        assertEquals(10,M.getHeight());
    }

    @Test
    void testIsInsideElad() {
        Pixel2D p = new Index2D(0,0);
        assertTrue(M.isInside(p));
        p = new Index2D(10,10);
        assertFalse(M.isInside(p));
    }

    @Test
    void testSameDimensionsElad() {
        Map2D m = new MyMap(RESULT);
        assertTrue(M.sameDimensions(m));
    }

    @Test
    void testGetPixelElad() {
        Pixel2D p1 = new Index2D(0,0);
        assertEquals(0,M.getPixel(p1));
        assertEquals(0,M.getPixel(0,0));
    }

    @Test
    void testSetPixelElad() {
        Pixel2D p1 = new Index2D(0,0);
        M.setPixel(p1,1);
        assertEquals(1,M.getPixel(p1));
        M.setPixel(0,0,2);
        assertEquals(2,M.getPixel(p1));
    }

    @Test
    void testAddMap2DElad() {
        Map2D m = new MyMap(RESULT);
        m.addMap2D(M);
        assertEquals(M,m);
        Pixel2D p1 = new Index2D(0,0);
        M.setPixel(p1,1);
        m.addMap2D(M);
        int [][] result = {
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        assertTrue(Arrays.deepEquals(result,M.getMap()));
        int [][] map = {
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        assertThrows(RuntimeException.class, () -> m.addMap2D(new MyMap(map)));
    }
    @Test
    void testMulElad() {
        int [][] map = {
                {1,2},
                {3,4}
        };
        Map2D m = new MyMap(map);
        m.mul(2);
        int [][] mapR = {
                {2,4},
                {6,8}
        };
        Map2D mResult = new MyMap(mapR);
        assertEquals(mResult,m);
    }

    @Test
    void testRescaleElad() {
        int[][] src = {
                { 1,  2,  3,  4},
                { 5,  6,  7,  8},
                { 9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Map2D m = new MyMap(src);
        m.rescale(0.5, 0.5);
        assertEquals(2, m.getWidth());
        assertEquals(2, m.getHeight());
        assertEquals(1, m.getPixel(0, 0));
        assertEquals(3, m.getPixel(1, 0));
        assertEquals(9, m.getPixel(0, 1));
        assertEquals(11, m.getPixel(1, 1));
    }

    @Test
    void testEqualsElad() {
        Map2D m1 = new MyMap(RESULT);
        Map2D m2 = new MyMap(RESULT);
        assertEquals(m1, m2);
        Pixel2D p1 = new Index2D(0,0);
        m1.setPixel(p1,1);
        assertTrue(!m1.equals(m2));
    }

    @Test
    void testDrawCircleElad() {
        M.drawCircle(new Index2D(5,5),4,1);
        RESULT = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        assertArrayEquals(RESULT, M.getMap());
    }

    @Test
    void testDrawLineElad() {
        M.drawLine(new Index2D(2,0), new Index2D(6,9),1);
        RESULT = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0}
        };
        assertArrayEquals(RESULT, M.getMap());
    }

    @Test
    void testDrawRectElad() {
        M.drawRect(new Index2D(1,1), new Index2D(8,8),1);
        RESULT = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        assertArrayEquals(RESULT, M.getMap());
    }

    @Test
    void testFillElad() {
        MyMap m = new MyMap(10,10,1);
        m.fill(new Index2D(0,0),0,false);
        assertArrayEquals(RESULT,m.getMap());

        m.drawRect(new Index2D(0,1),new Index2D(9,8),1);
        m.fill(new Index2D(0,0),1,true);
        assertEquals(1,m.getPixel(0, 0));
        assertEquals(1,m.getPixel(9, 9));

        m = new MyMap(10,10,0);
        m.drawRect(new Index2D(0,1),new Index2D(9,8),1);
        m.fill(new Index2D(0,0),1,false);
        assertEquals(1,m.getPixel(0, 0));
        assertEquals(0,m.getPixel(9, 9));
    }

    @Test
    void testShortestPathElad() {
        Pixel2D[] shortestPath = M.shortestPath(new Index2D(0,0), new Index2D(9,0),1,false);
        Pixel2D[] result = new Pixel2D[10];
        result[0] = new Index2D(0,0);
        result[1] = new Index2D(1,0);
        result[2] = new Index2D(2,0);
        result[3] = new Index2D(3,0);
        result[4] = new Index2D(4,0);
        result[5] = new Index2D(5,0);
        result[6] = new Index2D(6,0);
        result[7] = new Index2D(7,0);
        result[8] = new Index2D(8,0);
        result[9] = new Index2D(9,0);
        assertArrayEquals(result,shortestPath);

        M.setPixel(new Index2D(0,0),1);
        M.setPixel(new Index2D(9,0),1);
        M.setPixel(new Index2D(9,9),1);
        shortestPath = M.shortestPath(new Index2D(0,0), new Index2D(9,9),0,true);
        result = new Pixel2D[3];
        result[0] = new Index2D(0,0);
        result[1] = new Index2D(9,0);
        result[2] = new Index2D(9,9);
        assertArrayEquals(result,shortestPath);
    }

    @Test
    void testAllDistanceElad() {
        Map2D m = new MyMap(5,5,0);
        m.setPixel(new Index2D(0,0),1);
        m.setPixel(new Index2D(4,0),1);
        m.setPixel(new Index2D(0,4),1);
        m.setPixel(new Index2D(4,4),1);
        m = m.allDistance(new Index2D(1,1),1,false);
        int[][] result = new int[][] {
                {-1,1,2,3,-1},
                {1,0,1,2,3},
                {2,1,2,3,4},
                {3,2,3,4,5},
                {-1,3,4,5,-1}
        };
        assertArrayEquals(result,m.getMap());

        m = new MyMap(5,5,0);
        m.setPixel(new Index2D(0,0),1);
        m.setPixel(new Index2D(4,0),1);
        m.setPixel(new Index2D(0,4),1);
        m.setPixel(new Index2D(4,4),1);
        m = m.allDistance(new Index2D(1,1),1,true);
        result = new int[][] {
                {-1,1,2,3,-1},
                {1,0,1,2,2},
                {2,1,2,3,3},
                {3,2,3,4,4},
                {-1,2,3,4,-1}
        };
        assertArrayEquals(result,m.getMap());
    }
}

