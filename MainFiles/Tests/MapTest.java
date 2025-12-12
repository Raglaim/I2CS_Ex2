package assignments.Ex2.MainFiles.Tests;

import assignments.Ex2.MainFiles.Classes.Index2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Map2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Pixel2D;
import assignments.Ex2.MainFiles.Classes.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Intro2CS, 2026A, this is a very
 */
class MapTest {
    /**
     */
    private int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};
    private Map2D _m0, _m1, _m3_3;
    @BeforeEach
    void setUp() {
        _m0 = new Map();
        _m1 = new Map();
        _m3_3 = new Map(_map_3_3);
    }
    @Test
    @Timeout(value = 1, unit = SECONDS)
    void init() {
        int[][] bigarr = new int [500][500];
        _m1.init(bigarr);
        assertEquals(bigarr.length, _m1.getWidth());
        assertEquals(bigarr[0].length, _m1.getHeight());
        Pixel2D p1 = new Index2D(3,2);
        _m1.fill(p1,1, true);
    }

    @Test
    void testInit() {
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);
    }
    @Test
    void testEquals() {
        assertEquals(_m0,_m1);
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0,_m1);
    }


    // My Tests:
    private static final int [][] RESULT = {
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
    private static final Map2D M = new Map();

    @Test
    void testMapElad() {
        Map2D mapResult = new Map(RESULT);
        Map2D m1 = new Map(10,10,0);
        Map2D m2 = new Map(10);
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
        Map2D m = new Map();
        m.init(RESULT);
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
    void testGetPixelElad() {
        Pixel2D p1 = new Index2D(0,0);
        assertEquals(0,M.getPixel(p1));
    }

    @Test
    void testSetPixelElad() {
        Pixel2D p1 = new Index2D(0,0);
        M.setPixel(p1,1);
        assertEquals(1,M.getPixel(p1));
    }

    @Test
    void testIsInsideElad() {
        Pixel2D p1 = new Index2D(0,0);
        assertTrue(M.isInside(p1));
    }

    @Test
    void testSameDimensionsElad() {
        Map2D m = new Map(RESULT);
        assertTrue(M.sameDimensions(m));
    }

    @Test
    void testAddMap2DElad() {
        Map2D m = new Map(RESULT);
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
        assertThrows(RuntimeException.class, () -> m.addMap2D(new Map(map)));
    }
    @Test
    void testMulElad() {
        int [][] map = {
                {1,2},
                {3,4}
        };
        Map2D m = new Map(map);
        m.mul(2);
        int [][] mapR = {
                {2,4},
                {6,8}
        };
        Map2D mR = new Map(mapR);
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
        Map2D m = new Map(src);
        m.rescale(0.5, 0.5);
        assertEquals(2, m.getWidth());
        assertEquals(2, m.getHeight());
        assertEquals(1, m.getPixel(0, 0));
        assertEquals(3, m.getPixel(1, 0));
        assertEquals(9, m.getPixel(0, 1));
        assertEquals(11, m.getPixel(1, 1));
    }
}