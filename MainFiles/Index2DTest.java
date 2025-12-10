package assignments.Ex2.MainFiles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test suite for the {@code Index2D} class.
 *
 * <p>This class contains unit tests designed to validate the correctness and reliability
 * of the {@code Index2D} implementation. The tests cover:
 * <ul>
 *   <li>Constructors, including the copy constructor</li>
 *   <li>Accessor methods: {@code getX()} and {@code getY()}</li>
 *   <li>Distance calculation via {@code distance2D()}</li>
 *   <li>String representation through {@code toString()}</li>
 *   <li>Equality comparison using {@code equals()}</li>
 * </ul>
 *
 * <p>These tests ensure that the {@code Index2D} class behaves as expected under normal conditions.
 *
 * @author Elad Nagar
 */
class Index2DTest {

    private final Index2D p1 = new Index2D(1,2);
    private final Index2D p2 = new Index2D(5,5);


    /** Tests that the copy constructor creates an object with the same coordinates as the original. */
    @Test
    void testIndex2D() {
        Index2D p3 = new Index2D(p1);
        assertEquals(p1.getX(), p3.getX());
        assertEquals(p1.getY(), p3.getY());
    }

    /** Tests that getX() returns the correct x-coordinate. */
    @Test
    void testGetX() {
        int x1 = p1.getX();
        int x2 = p2.getX();
        assertEquals(1, x1);
        assertEquals(5, x2);
    }

    /** Tests that getY() returns the correct y-coordinate. */
    @Test
    void testGetY() {
        int y1 = p1.getY();
        int y2 = p2.getY();
        assertEquals(2, y1);
        assertEquals(5, y2);
    }

    /** Tests that distance2D() correctly calculates the Euclidean distance between two points. */
    @Test
    void testDistance2D() {
        double dist = p1.distance2D(p2);
        assertEquals(5, dist);
    }

    /** Tests that toString() returns the coordinates in the format "(x,y)". */
    @Test
    void testToString() {
        String str1 = p1.toString();
        String str2 = p2.toString();
        assertEquals("(1,2)", str1);
        assertEquals("(5,5)", str2);
    }

    /** Tests that equals() returns true for two points with the same coordinates. */
    @Test
    void testEquals() {
        Index2D p3 = new Index2D(1, 2);
        assertEquals(p1, p3);
    }

}