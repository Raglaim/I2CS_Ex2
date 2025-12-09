package assignments.Ex2.MainFiles;

public class Index2D implements Pixel2D {

    private int X = 0;
    private int Y = 0;

    public Index2D(int w, int h) {
        X = w;
        Y = h;
    }
    public Index2D(Pixel2D other) {
        X = other.getX();
        Y = other.getY();
    }
    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public double distance2D(Pixel2D p2) {
        return Math.sqrt(Math.pow(p2.getX() - this.X, 2) + Math.pow(p2.getY() - this.Y, 2));
    }

    @Override
    public String toString() {
        String ans = null;
        ans = "(" + X + "," + Y + ")";
        return ans;
    }

    @Override
    public boolean equals(Object p) {
        boolean ans = true;
        if (!(p instanceof Pixel2D)) {
            return false;
        } else {
            Pixel2D other = (Pixel2D) p;
            if (this.X != other.getX() || this.Y != other.getY()) {
                return false;
            }
        }
        return ans;
    }
}
