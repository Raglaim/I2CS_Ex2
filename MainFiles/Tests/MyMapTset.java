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
        Pixel2D p1 = new Index2D(0,0);
        assertTrue(M.isInside(p1));
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
        Map2D mR = new MyMap(mapR);
        assertEquals(mR,m);
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
    void testDrawElad() {

    }
}

