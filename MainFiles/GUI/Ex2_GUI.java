package assignments.Ex2.MainFiles.GUI;

import assignments.Ex2.MainFiles.Classes.Index2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Map2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Pixel2D;
import assignments.Ex2.MainFiles.Classes.Map;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Paths;

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
        StdDraw.setCanvasSize(500,500);
        StdDraw.setXscale(0,map.getWidth());
        StdDraw.setYscale(0,map.getHeight());
        StdDraw.clear(StdDraw.WHITE);
        for (int y = 0; y < map.getHeight(); y+=1) {
            for (int x = 0; x < map.getWidth(); x++) {
                if (!(map.getPixel(x, y) == 0)) {
                    setColor(map.getPixel(x,y));
                    StdDraw.filledSquare(x+0.5,y+0.5,0.5);
                }
            }
        }
    }

    /**
     * @param mapFileName
     * @return
     */
    public static Map loadMap(String mapFileName) {
        if (checkTxt(mapFileName)) {
            Path targetDir = Paths.get("C:\\Users\\User\\IdeaProjects\\I2CS_Ex2\\MainFiles\\GUI\\SavedMaps");
            Path path = targetDir.resolve(mapFileName);
            String content = "";
            try {
                content = Files.readString(path);
            }
            catch (IOException e) {e.printStackTrace();}
            return Map.mapFromString(content);
        } else {
            return new Map();
        }
    }

    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {
        Path targetDir = Paths.get("C:\\Users\\User\\IdeaProjects\\I2CS_Ex2\\MainFiles\\GUI\\SavedMaps");
        Path path = targetDir.resolve(mapFileName);
        String content = map.toString();
        try {
            Files.writeString(path, content);
        }
        catch (IOException e) {e.printStackTrace();}
    }

    public static void main(String[] a) {
        String mapFile = "map.txt";
        Map2D map = loadMap(mapFile);
        drawMap(map);
    }
    /// ///////////// Private functions ///////////////

    private static void setColor(int v) {
        switch (v){
            case 0 -> StdDraw.setPenColor(StdDraw.WHITE);
            case 1 -> StdDraw.setPenColor(StdDraw.BLACK);
            case 2 -> StdDraw.setPenColor(StdDraw.RED);
            case 3 -> StdDraw.setPenColor(StdDraw.GREEN);
            case 4 -> StdDraw.setPenColor(StdDraw.BLUE);
        }
    }

    private static boolean checkTxt(String fileName) {
        return fileName.toLowerCase().endsWith(".txt");
    }
}