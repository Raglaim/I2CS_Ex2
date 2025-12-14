package assignments.Ex2.MainFiles.Classes;

import assignments.Ex2.MainFiles.Classes.Interfaces.Pixel2D;

public class Circle implements assignments.Ex2.MainFiles.Classes.Interfaces.Circle {

    private int X = 0;
    private int Y = 0;
    private double RAD = 0;

    public Circle(int x, int y, double rad) {
        X = x;
        Y = y;
        RAD = rad;
    }

    public Circle(Pixel2D center, double rad) {
        X = center.getX();
        Y = center.getY();
        RAD = rad;
    }

    public Circle copy(Circle other) {
        return new Circle(other.getCenter(),other.getRadius());
    }

    public int getCenterX() {
        return this.X;
    }
    private void setCenterX(int x) {
        this.X = x;
    }

    public int getCenterY() {
        return this.Y;
    }
    private void setCenterY(int y) {
        this.Y = y;
    }

    public Pixel2D getCenter() {
        return new Index2D(this.getCenterX(), this.getCenterY());
    }
    private void setCenter(Pixel2D center) {
        this.X = center.getX();
        this.Y = center.getY();
    }

    public double getRadius() {
        return this.RAD;
    }
    private void setRadius(double rad) {
        this.RAD = rad;
    }

    public double area() {
        return Math.pow(this.getRadius(), 2) * Math.PI;
    }

    public double perimeter() {
        return 2 * Math.PI * this.getRadius();
    }

    public double diameter() {
        return 2 * this.getRadius();
    }

    public boolean contains(int x, int y) {
        Pixel2D p = new Index2D(x,y);
        return this.contains(p);
    }

    public boolean contains(Pixel2D p) {
        return this.getCenter().distance2D(p) <= this.getRadius();
    }

    public String toString() {
        return ("Center: " + this.getCenter().toString() + ", Radius: " + this.getRadius());
    }

    public boolean equals(Circle other) {
        if (other == null) {
            return false;
        }
        return this.getCenterX() == other.getCenterX() && this.getCenterY() == other.getCenterY() && this.getRadius() == other.getRadius();

    }
}
