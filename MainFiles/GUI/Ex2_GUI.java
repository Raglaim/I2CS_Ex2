package assignments.Ex2.MainFiles.GUI;

import assignments.Ex2.MainFiles.Classes.Index2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Map2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Pixel2D;
import assignments.Ex2.MainFiles.Classes.Map;

/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * The class has save and load functions, and a GUI draw function.
 * You should implement this class, it is recommender to use the StdDraw class, as in:
 * https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 *
 *
 */
public class Ex2_GUI {
    public static void drawMap(Map2D map) {
        for (int y = 0; y < map.getHeight(); y+=1) {
            for (int x = 0; x < map.getWidth(); x++) {
                setColor(map.getPixel(x,y));
                StdDraw.filledSquare(x+0.5,y+0.5,0.5);
            }
        }
    }

    /**
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) {
        Map2D ans = null;

        return ans;
    }

    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {


    }
    public static void main(String[] a) {
//        String mapFile = "map.txt";
//        Map2D map = loadMap(mapFile);
//        drawMap(map);
        Map2D map = new Map();
        Pixel2D pixel1 = new Index2D(2,2);
        Pixel2D pixel2 = new Index2D(5,8);
        map.drawLine(pixel1,pixel2,2);
        StdDraw.setCanvasSize(500,500);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setXscale(0,10);
        StdDraw.setYscale(0,10);

        drawMap(map);

    }
    /// ///////////// Private functions ///////////////

    private static void setColor(int v) {
        switch (v){
            case 0 -> StdDraw.setPenColor(StdDraw.WHITE);
            case 1 -> StdDraw.setPenColor(StdDraw.BLACK);
            case 2 -> StdDraw.setPenColor(StdDraw.GREEN);
        }
    }
}