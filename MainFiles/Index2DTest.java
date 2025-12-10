package assignments.Ex2.MainFiles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Index2DTest {

    private final Index2D p1 = new Index2D(1,2);
    private final Index2D p2 = new Index2D(5,5);

    @Test
    void testIndex2D() {
        Index2D p3 = new Index2D(p1);
        assertEquals(p1.getX(), p3.getX());
        assertEquals(p1.getY(), p3.getY());
    }

    @Test
    void testGetX() {
        int x1 = p1.getX();
        int x2 = p2.getX();
        assertEquals(1, x1);
        assertEquals(5, x2);
    }

    @Test
    void testGetY() {
        int y1 = p1.getY();
        int y2 = p2.getY();
        assertEquals(2, y1);
        assertEquals(5, y2);
    }

    @Test
    void testDistance2D() {
        double dist = p1.distance2D(p2);
        assertEquals(5, dist);
    }

    @Test
    void testToString() {
        String str1 = p1.toString();
        String str2 = p2.toString();
        assertEquals("(1,2)", str1);
        assertEquals("(5,5)", str2);
    }

    @Test
    void testEquals() {
        Index2D p3 = new Index2D(1, 2);
        assertEquals(p1, p3);
    }
}