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

    private int W = 0;
    private int H = 0;
    private int[][] MAP = {};
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
            this.MAP = new int [H][W];
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
        for (int i = 0; i < ans.length; i++) {
            MAP[i] = new int[ans[i].length];
            System.arraycopy(ans[i], 0, MAP[i], 0, ans[i].length);
        }

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
        for (int i = 0; i < arr.length; i++) {
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
        MAP = new int[h][w];
        for (int i = 0; i < ans.length; i++) {
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
            for (int i = 0; i < this.H; i++) {
                for (int j = 0; j < this.W; j++) {
                    this.MAP[j][i] += p.getPixel(j, i);
                }
            }
        }
    }

    @Override
    public void mul(double scalar) {
        for (int i = 0; i < this.H; i++) {
            for (int j = 0; j < this.W; j++) {
                this.MAP[j][i] = (int)(this.MAP[j][i] * scalar);
            }
        }
    }

    @Override
    public void rescale(double sx, double sy) {
        int newW = (int)(this.W * sx);
        int newH = (int)(this.H * sy);
        int [][] newMAP = new int[newH][newW];
        for (int i = 0; i < newH; i++) {
            for (int j = 0; j < newW; j++) {
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

    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public boolean equals(Object ob) {
        boolean ans = false;

        return ans;
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
