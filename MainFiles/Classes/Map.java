package assignments.Ex2.MainFiles.Classes;
import assignments.Ex2.MainFiles.Classes.Interfaces.Map2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Pixel2D;

import java.io.Serializable;

/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{

    private static final int DEFAULT_W = 10;
    private static final int DEFAULT_H = 10;
    private static final int DEFAULT_V = 0;
    public Map() {
        init(DEFAULT_W, DEFAULT_H, DEFAULT_V);
    }

    private int W = 0;
    private int H = 0;
    private int[][] MAP = new int[W][H];
	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {init(w, h, v);}
	/**
	 * Constructs a square map (size*size).
	 * @param size
	 */
	public Map(int size) {this(size,size, 0);}
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
        if (data == null) {
            init(DEFAULT_W, DEFAULT_H, DEFAULT_V);
            return;
        }
        init(data);
	}
	@Override
	public void init(int w, int h, int v) {
        int [][] ans = null;
        W = w;
        H = h;
        ans = new int[h][w];
        for (int i = 0; i < h; i+=1) {
            for (int j = 0; j < w; j+=1) {
                ans[i][j] = v;
            }
        }
        copy(ans, w, h);
    }
	@Override
	public void init(int[][] arr) {
        int [][] ans = null;
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("Null or empty array");
        }
        if (arr.length != arr[0].length) {
            throw new RuntimeException("Non square array");
        }
        for (int i = 0; i < arr.length; i+=1) {
            if (arr[i].length != arr[0].length) {
                throw new RuntimeException("Ragged array");
            }
        }
        int w = arr[0].length ;
        W = arr[0].length ;
        int h = arr.length;
        H = arr.length;
        ans = new int[h][w];
        for (int i = 0; i < h; i+=1) {
            System.arraycopy(arr[i], 0, ans[i], 0, w);
        }
        copy(ans, w, h);
    }

    private void copy(int[][] ans, int w, int h) {
        this.MAP = new int[h][w];
        for (int i = 0; i < ans.length; i+=1) {
            MAP[i] = new int[ans[i].length];
            System.arraycopy(ans[i], 0, MAP[i], 0, ans[i].length);
        }
    }

    @Override
	public int[][] getMap() {return this.MAP;}
	@Override
	public int getWidth() {return this.W;}
	@Override
	public int getHeight() {return this.H;}
	@Override
	public int getPixel(int x, int y) {return this.MAP[y][x];}
	@Override
	public int getPixel(Pixel2D p) {return this.getPixel(p.getX(), p.getY());}
	@Override
	public void setPixel(int x, int y, int v) {this.MAP[y][x] = v;}
	@Override
	public void setPixel(Pixel2D p, int v) {this.MAP[p.getY()][p.getX()] = v;}

    @Override
    public boolean isInside(Pixel2D p) {return p.getX() >= 0 && p.getX() < this.W && p.getY() >= 0 && p.getY() < this.H;}

    @Override
    public boolean sameDimensions(Map2D p) {return this.W == p.getWidth() && this.H == p.getHeight();}

    @Override
    public void addMap2D(Map2D p) {
        if (this.sameDimensions(p)){
            for (int y = 0; y < this.H; y+=1) {
                for (int x = 0; x < this.W; x+=1) {
                    this.MAP[y][x] += p.getPixel(x, y);
                }
            }
        }
    }

    @Override
    public void mul(double scalar) {
        for (int y = 0; y < this.H; y+=1) {
            for (int x = 0; x < this.W; x+=1) {
                this.MAP[y][x] = (int)(this.MAP[y][x] * scalar);
            }
        }
    }

    @Override
    public void rescale(double sx, double sy) {
        int newW = (int)(this.W * sx);
        int newH = (int)(this.H * sy);
        int [][] newMAP = new int[newH][newW];
        for (int i = 0; i < newH; i+=1) {
            for (int j = 0; j < newW; j+=1) {
                int oldX = (int)(j / sx);
                int oldY = (int)(i / sy);
                newMAP[i][j] = this.MAP[oldY][oldX];
            }
        }
        this.W = newW;
        this.H = newH;
        this.MAP = newMAP;
    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {
        Circle c = new Circle(center, rad);
        for (int i = 0; i < this.getHeight(); i+=1) {
            for (int j = 0; j < this.getWidth(); j+=1) {
                Pixel2D p = new Index2D(j, i);
                if (c.contains(p)) {this.setPixel(p, color);}
            }
        }
    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {
        if (this.isInside(p1) && this.isInside(p2)) {
            int dx = Math.abs(p2.getX() - p1.getX());
            int dy = Math.abs(p2.getY() - p1.getY());
            double m = (double) dy /dx; // slope
            if (p1.equals(p2)) {this.setPixel(p1, color);}
            if (dx >= dy && p1.getX() < p2.getX()) {
                for (int i = 0; i <= dx; i+=1) {
                    int x = p1.getX() + i;
                    int fx = (int) ((m * (x - p1.getX())) + p1.getY());
                    this.setPixel(x-1, fx-1, color);
                }
            }
            if (dx >= dy && p1.getX() > p2.getX()) {drawLine(p2, p1, color);}
            if (dy > dx && p1.getY() < p2.getY()) {
                for (int i = 0; i <= dy; i+=1) {
                    int y = p1.getY() + i;
                    int gy = (int) (((y - p1.getY()) / m) + p1.getX());
                    this.setPixel(gy-1, y-1, color);
                }
            }
            if (dy > dx && p1.getY() > p2.getY()) {drawLine(p2, p1, color);}
        }

    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        if (this.isInside(p1) && this.isInside(p2)) {
            int xStart = Math.min(p1.getX(), p2.getX());
            int xEnd = Math.max(p1.getX(), p2.getX());
            int yStart = Math.min(p1.getY(), p2.getY());
            int yEnd = Math.max(p1.getY(), p2.getY());
            for (int i = yStart; i <= yEnd; i+=1) {
                for (int j = xStart; j <= xEnd; j+=1) {
                    this.setPixel(j, i, color);
                }
            }
        }
    }

    @Override
    public boolean equals(Object ob) {
        if (!(ob instanceof Map2D)) {return false;}
        else if (!(this.sameDimensions((Map2D)ob))) {return false;}
        else {
            for (int y = 0; y < this.H; y+=1) {
                for (int x = 0; x < this.W; x+=1) {
                    if (this.getPixel(x,y) != ((Map2D) ob).getPixel(x,y)) {return false;}
                }
            }
            return true;
        }
    }
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
		int ans = -1;

		return ans;
	}

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
		Pixel2D[] ans = null;  // the result.

		return ans;
	}
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        return ans;
    }
	////////////////////// Private Methods ///////////////////////

}
