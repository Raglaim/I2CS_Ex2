package assignments.Ex2.MainFiles.Classes.Interfaces;

public interface Circle {

    /**
     * Creates and returns an independent copy (deep copy) of this shape.
     * <p>
     * Implementations must ensure the returned GeoShapes is structurally independent
     * of the original: all mutable fields and contained objects should be copied,
     * not shared. After calling copy(), mutating the copy must not affect the original,
     * and vice versa.
     * </p>
     *
     * <p><b>Example semantics:</b>
     * <ul>
     *   <li>Circle: copy center coordinates and radius into a new Circle instance.</li>
     *   <li>Rectangle: copy origin/size (or vertices) into a new Rectangle instance.</li>
     *   <li>Polygon: allocate a new list/array and copy all vertices into it (deep copy of points).</li>
     * </ul>
     * </p>
     *
     * @return a new GeoShapes instance that is an independent copy of this shape
     */
    Circle copy(assignments.Ex2.MainFiles.Classes.Circle other);

    /**
     * Computes the area (in square units) of this shape.
     * @return the area of the shape
     */
    double area();

    /**
     * Computes the perimeter (total boundary length) of this shape.
     *
     * @return the perimeter of the shape
     */
    double perimeter();

    /**
     * Tests whether the integer-coordinate point (x, y) lies inside this shape.
     * <p>Implementations should define whether boundary points are considered inside
     * (commonly true). Coordinates are interpreted in the same coordinate system
     * as the shape.</p>
     *
     * @param x the x-coordinate of the point (integer)
     * @param y the y-coordinate of the point (integer)
     * @return true if the point is inside or on the boundary; false otherwise
     */
    boolean contains(int x, int y);

    /**
     * Tests whether the given Pixel2D is inside this shape.
     * <p>Typically equivalent to contains(p.getX(), p.getY()).</p>
     *
     * @param p the point to test
     * @return true if the point is inside or on the boundary, otherwise false
     * @throws NullPointerException if p is null (recommended)
     */
    boolean contains(Pixel2D p);

    /**
     * Returns a human-readable description of this shape (type and key parameters).
     *
     * @return a string representation of the shape
     */
    String toString();

    /**
     * Tests semantic equality between this shape and another object.
     * <p>Must obey the general contract of equals in Java (reflexive, symmetric,
     * transitive, consistent, and non-nullity). If equals is overridden, hashCode
     * should also be overridden consistently.</p>
     *
     * @param other another object to compare
     * @return true if both represent the same shape; false otherwise
     */
    boolean equals(Object other);

}
