package assignments.Ex2.MainFiles.Classes;
import assignments.Ex2.MainFiles.Classes.Interfaces.Map2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Pixel2D;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class MyMap implements Map2D, Serializable{

    private static final int DEFAULT_W = 10;
    private static final int DEFAULT_H = 10;
    private static final int DEFAULT_V = 0;
    public MyMap() {
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
	public MyMap(int w, int h, int v) {init(w, h, v);}
	/**
	 * Constructs a square map (size*size).
	 * @param size
	 */
	public MyMap(int size) {this(size,size, 0);}
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public MyMap(int[][] data) {
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
            // 1
            if (p1.equals(p2)) {this.setPixel(p1, color);}
            // 2
            if (dx >= dy && p1.getX() < p2.getX()) {
                for (int i = 0; i <= dx; i+=1) {
                    int x = p1.getX() + i;
                    int fx = (int) ((m * (x - p1.getX())) + p1.getY());
                    this.setPixel(x-1, fx-1, color);
                }
            }
            // 3
            if (dx >= dy && p1.getX() > p2.getX()) {drawLine(p2, p1, color);}
            // 4
            if (dy > dx && p1.getY() < p2.getY()) {
                for (int i = 0; i <= dy; i+=1) {
                    int y = p1.getY() + i;
                    int gy = (int) (((y - p1.getY()) / m) + p1.getX());
                    this.setPixel(gy-1, y-1, color);
                }
            }
            // 5
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
	public int fill(Pixel2D start, int new_v,  boolean cyclic) {
        int ans = 0; // making result
        int startingColor = this.getPixel(start);
        // checking if the starting pixel is already the new color
        if (startingColor == new_v) {return 0;}
        this.setPixel(start, new_v);
        ans += 1;

        // using a BFS method to set every pixel that is visited into the new color
        PixelsContainer q = new PixelsContainer();
        q.enqueue(start);
        if (!cyclic) {
            while (!q.isEmpty()) {
                Pixel2D node = q.dequeue();
                PixelsContainer neighbours = this.checkNeighboursNotCyclic(node, startingColor);
                for (Pixel2D next : neighbours.getList()) {
                    if (this.getPixel(next) != new_v) {
                        q.enqueue(next);
                        this.setPixel(next, new_v);
                        ans += 1;
                    }
                }
            }

        }
        else {
            while (!q.isEmpty()) {
                Pixel2D node = q.dequeue();
                PixelsContainer neighbours = this.checkNeighboursCyclic(node, startingColor);
                for (Pixel2D next : neighbours.getList()) {
                    if (this.getPixel(next) != new_v) {
                        q.enqueue(next);
                        this.setPixel(next, new_v);
                        ans += 1;
                    }
                }
            }
        }
        return ans;
    }

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D start, Pixel2D end, int obsColor, boolean cyclic) {
        Pixel2D[] ans = null;  // the result.

        // making a copy for the maze
        Map2D maze = new MyMap(getMap());

        // setting obsColor to -1
        if (obsColor != -1) {
            for (int y = 0; y < this.getHeight(); y+=1) {
                for (int x = 0; x < this.getWidth(); x+=1) {
                    Pixel2D p = new Index2D(x,y);
                    if (maze.getPixel(p) == obsColor) {
                        maze.setPixel(p,-1);
                    }
                }
            }
            obsColor = -1;
        }

        // setting every else pixel to 0
        for (int y = 0; y < this.getHeight(); y+=1) {
            for (int x = 0; x < this.getWidth(); x+=1) {
                Pixel2D p = new Index2D(x,y);
                if (maze.getPixel(p) != obsColor) {
                    maze.setPixel(p,0);
                }
            }
        }
        // checking if there is a way to get from s pixel to e pixel
        maze.fill(start,1,cyclic);
        if (maze.getPixel(end) != 1) {return null;}

        // making a BFS dictionary
        Map<Pixel2D, Pixel2D> prev = this.solve(start,cyclic);

        // using the dictionary to get from e pixel to s pixel
        ans = reconstructPath(start, end, prev).getList();

		return ans;
	}

    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        // making a copy for the maze
        Map2D maze = new MyMap(getMap());

        // setting obsColor to -1
        if (obsColor != -1) {
            for (int y = 0; y < this.getHeight(); y+=1) {
                for (int x = 0; x < this.getWidth(); x+=1) {
                    Pixel2D p = new Index2D(x,y);
                    if (maze.getPixel(p) == obsColor) {
                        maze.setPixel(p,-1);
                    }
                }
            }
            obsColor = -1;
        }

        // setting every else pixel to 0
        for (int y = 0; y < this.getHeight(); y+=1) {
            for (int x = 0; x < this.getWidth(); x+=1) {
                Pixel2D p = new Index2D(x,y);
                if (maze.getPixel(p) != obsColor) {
                    maze.setPixel(p,0);
                }
            }
        }

        // setting every reachable pixel to 1
        maze.fill(start,1,cyclic);

        // setting every unreachable pixel to -1
        for (int y = 0; y < this.getHeight(); y+=1) {
            for (int x = 0; x < this.getWidth(); x+=1) {
                Pixel2D p = new Index2D(x,y);
                if (maze.getPixel(p) == 0) {
                    maze.setPixel(p,-1);
                }
            }
        }

        ans = new MyMap(maze.getMap());
        // setting every pixel to its distance from the start
        for (int y = 0; y < this.getHeight(); y+=1) {
            for (int x = 0; x < this.getWidth(); x+=1) {
                Pixel2D p = new Index2D(x,y);
                if (maze.getPixel(p) == 1) {
                    PixelsContainer path = new PixelsContainer(maze.shortestPath(start, p, -1, cyclic));
                    int pathLength = path.getLength();
                    ans.setPixel(p,pathLength - 1);
                }
            }
        }

        return ans;
    }

    public static void main() {
        MyMap map = new MyMap(10,10,1);
        Pixel2D p1 = new Index2D(1, 1);
        Pixel2D p2 = new Index2D(8, 8);
        map.drawRect(p1,p2,0);
        Pixel2D p3 = new Index2D(0, 0);
        Map2D maze = map.allDistance(p3,0,false);
        System.out.println(maze.toString());
    }

	////////////////////// Private Methods ///////////////////////

    private void copy(int[][] ans, int w, int h) {
        this.MAP = new int[h][w];
        for (int i = 0; i < ans.length; i+=1) {
            MAP[i] = new int[ans[i].length];
            System.arraycopy(ans[i], 0, MAP[i], 0, ans[i].length);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < this.H; y+=1) {
            for (int x = 0; x < this.W; x+=1) {
                sb.append(this.MAP[y][x]).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public static MyMap mapFromString(String mapS) {
        String[] rows = mapS.split("\n");
        int h = rows.length;
        int w = rows[0].replace(" ", "").length();
        int[][] m = new int[h][w];
        for (int y = 0; y < h; y++) {
            String[] pixels = rows[y].trim().split(" ");
            for (int x = 0; x < w; x+=1) {
                m[y][x] = Integer.parseInt(pixels[x]);
            }
        }
        return new MyMap(m);
    }

    private Map<Pixel2D, Pixel2D> solve(Pixel2D s, Boolean cyclic) {
        int v = this.getPixel(s);
        PixelsContainer q = new PixelsContainer();
        q.enqueue(s);
        Map<Pixel2D,Boolean> visited = new HashMap<>();
        for (int y = 0; y < this.H; y+=1) {
            for (int x = 0; x < this.W; x+=1) {
                Pixel2D p = new Index2D(x,y);
                if (getPixel(p) == v) {
                    visited.put(p, false);
                }
            }
        }
        visited.replace(s,true);

        Map<Pixel2D,Pixel2D> prev = new HashMap<>();
        if (!cyclic) {
            while (!q.isEmpty()){
                Pixel2D node = q.dequeue();
                PixelsContainer neighbours = this.checkNeighboursNotCyclic(node,v);

                for (Pixel2D next : neighbours.getList()) {
                    if (!visited.get(next)) {
                        q.enqueue(next);
                        visited.put(next, true);
                        prev.put(next,node);
                    }
                }
            }
        }
        else {
            while (!q.isEmpty()){
                Pixel2D node = q.dequeue();
                PixelsContainer neighbours = this.checkNeighboursCyclic(node,v);

                for (Pixel2D next : neighbours.getList()) {
                    if (!visited.get(next)) {
                        q.enqueue(next);
                        visited.put(next, true);
                        prev.put(next,node);
                    }
                }
            }
        }


        return prev;
    }

    private PixelsContainer checkNeighboursNotCyclic(Pixel2D node, int v) {
        PixelsContainer neighbours = new PixelsContainer();

        // Right
        Pixel2D rNode = new Index2D (node.getX()+1,node.getY());
        if (this.isInside(rNode) && this.getPixel(rNode) == v) {
            neighbours.enqueue(rNode);
        }

        // Left
        Pixel2D lNode = new Index2D (node.getX()-1,node.getY());
        if (this.isInside(lNode) && this.getPixel(lNode) == v) {
            neighbours.enqueue(lNode);
        }

        // Up
        Pixel2D uNode = new Index2D (node.getX(),node.getY()+1);
        if (this.isInside(uNode) && this.getPixel(uNode) == v) {
            neighbours.enqueue(uNode);
        }

        // Down
        Pixel2D dNode = new Index2D (node.getX(),node.getY()-1);
        if (this.isInside(dNode) && this.getPixel(dNode) == v) {
            neighbours.enqueue(dNode);
        }

        return neighbours;
    }

    private PixelsContainer checkNeighboursCyclic(Pixel2D node, int v) {
        PixelsContainer neighbours = new PixelsContainer();

        // Right
        Pixel2D rNode = new Index2D (node.getX()+1,node.getY());
        if (node.getX() == this.getWidth()-1) {
            rNode = new Index2D (0,node.getY());
        }
        if (this.getPixel(rNode) == v) {
            neighbours.enqueue(rNode);
        }

        // Left
        Pixel2D lNode = new Index2D (node.getX()-1,node.getY());
        if (node.getX() == 0) {
            lNode = new Index2D (this.getWidth()-1,node.getY());
        }
        if (this.getPixel(lNode) == v) {
            neighbours.enqueue(lNode);
        }

        // Up
        Pixel2D uNode = new Index2D (node.getX(),node.getY()+1);
        if (node.getY() == 0) {
            uNode = new Index2D (node.getX(),this.getHeight()-1);
        }
        if (this.getPixel(uNode) == v) {
            neighbours.enqueue(uNode);
        }

        // Down
        Pixel2D dNode = new Index2D (node.getX(),node.getY()-1);
        if (node.getY() == this.getHeight()-1) {
            dNode = new Index2D (node.getX(),0);
        }
        if (this.getPixel(dNode) == v) {
            neighbours.enqueue(dNode);
        }

        return neighbours;
    }

    public PixelsContainer reconstructPath(Pixel2D s, Pixel2D e, Map<Pixel2D, Pixel2D> prev){
        PixelsContainer path = new PixelsContainer();
        for (Pixel2D at = e; at != null ; at = prev.get(at)) {
            path.enqueue(at);
        }

        path.reverse();

        return path;
    }
}
