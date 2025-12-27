package assignments.Ex2.MainFiles.GUI;

import assignments.Ex2.MainFiles.Classes.Index2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Map2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Pixel2D;
import assignments.Ex2.MainFiles.Classes.MyMap;
import assignments.Ex2.MainFiles.Classes.PixelsContainer;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * It provides functionality to visualize a 2D map using Java Swing,
 * as well as static utility methods to save and load map states from text files.
 */
public class Ex2_GUI {

    /**
     * Visualizes the given Map2D object in a Swing JFrame.
     * <p>
     * The map is rendered as a grid of colored panels:
     * <ul>
     * <li>0 -> White</li>
     * <li>1 -> Black</li>
     * <li>2 -> Red</li>
     * <li>3 -> Green</li>
     * <li>4 -> Blue</li>
     * <li>Other -> Gray</li>
     * </ul>
     * Note: This method creates a 500x500 non-resizable window. The grid layout
     * is populated row by row.
     * </p>
     *
     * @param map the Map2D object to visualize
     */
    public static void drawMap(Map2D map) {
        JFrame frame = new JFrame("Map GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel mapPanel = new JPanel();
        mapPanel.setPreferredSize(new Dimension(500, 500));
        mapPanel.setLayout(new GridLayout(map.getHeight(), map.getWidth()));
        for (int y = 0; y < map.getHeight(); y+=1) {
            for (int x = 0; x < map.getWidth(); x+=1) {
                JPanel cell = new JPanel();
                cell.setBackground(getColor(map.getPixel(x,y)));
                mapPanel.add(cell);
            }
        }
        frame.add(mapPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Loads a map from a text file located in the "SavedMaps" directory.
     * <p>
     * The method reads the file content and parses it using {@link MyMap#mapFromString(String)}.
     * The file path is currently hardcoded to a specific user directory.
     * </p>
     *
     * @param mapFileName the name of the file to load (must end with .txt)
     * @return a new MyMap instance populated with data from the file, or an empty map if the file extension is invalid.
     */
    public static MyMap loadMap(String mapFileName) {
        if (checkTxt(mapFileName)) {
            Path targetDir = Paths.get("MainFiles\\GUI\\SavedMaps");
            Path path = targetDir.resolve(mapFileName);
            try {
                String content = Files.readString(path);
                return MyMap.mapFromString(content);
            }
            catch (IOException e) {
                e.printStackTrace();
                return new MyMap();
            }

        } else {
            return new MyMap();
        }
    }

    /**
     * Saves the current state of a Map2D object to a text file.
     * <p>
     * The map is converted to a string representation and written to the
     * "SavedMaps" directory.
     * </p>
     *
     * @param map         the map object to save
     * @param mapFileName the name of the file to save as
     */
    public static void saveMap(Map2D map, String mapFileName) {
        Path targetDir = Paths.get("MainFiles\\GUI\\SavedMaps");
        Path path = targetDir.resolve(mapFileName);
        String content = map.toString();
        try {
            Files.writeString(path, content);
        }
        catch (IOException e) {e.printStackTrace();}
    }


    public static void main(String[] args) {
        String mapFile = "map.txt";
        Map2D map = loadMap(mapFile);
        drawMap(map);
    }

    ///////////////// Private functions ///////////////

    /**
     * Validates if the given filename has a .txt extension.
     *
     * @param fileName the name of the file to check
     * @return true if the filename ends with .txt (case-insensitive), false otherwise
     */
    private static boolean checkTxt(String fileName) {return fileName.toLowerCase().endsWith(".txt");}


    public static MyMap mazeGen(int size, Pixel2D start){
        int obsColor = 1;
        MyMap mazeMap = new MyMap(size);

        // setting up the maze grid
        int mazeSize = ((size * 2) + 1);
        MyMap maze = new MyMap(mazeSize);
        for (int y = 0; y < maze.getHeight(); y+=1) {
            for (int x = 0; x < maze.getWidth(); x+=1) {
                if (!(x % 2 != 0 && y % 2 != 0)) {
                    maze.setPixel(x,y,obsColor);
                }
            }
        }

        // creating the maze
        {
            PixelsContainer q = new PixelsContainer();
            q.enqueue(start);
            int visitedColor = -1;
            int unVisitedColor = 0;
            mazeMap.setPixel(start, visitedColor);
            while (!q.isEmpty()) {
                Pixel2D node = q.dequeue();
                PixelsContainer neighbours = mazeMap.checkNeighboursNotCyclic(node, unVisitedColor);
                if (!neighbours.isEmpty()) {
                    for (int i = 0; i < neighbours.getLength(); i += 1) {
                        int randomNeighbour = (int) ((Math.random() * neighbours.getLength()));
                        Pixel2D neighbour = neighbours.dequeue(randomNeighbour);
                        q.enqueue(neighbour);
                        mazeMap.setPixel(neighbour, visitedColor);
                        int x = ((node.getX() * 2) + 1) + (neighbour.getX() - node.getX());
                        int y = ((node.getY() * 2) + 1) + (neighbour.getY() - node.getY());
                        maze.setPixel(x, y, 0);
                    }
                }
            }
        }

        // making every un reachable pixel int an obstetrical
        start = new Index2D(1, 1);
        maze.fill(start,3,false);
        for (int y = 0; y < maze.getHeight(); y+=1) {
            for (int x = 0; x < maze.getWidth(); x+=1) {
                if (maze.getPixel(x,y) != 3) {
                    maze.setPixel(x,y,1);
                }
            }
        }

        maze.fill(start,0,false);

        if (maze.getPixel(1,1) == 1 || maze.getPixel(maze.getWidth()-2,maze.getHeight()-2) == 1) {return mazeGen(size,start);}
        else {return maze;}
    }

    public static Color getColor(int v) {
        return switch (v) {
            case 0 -> Color.white;
            case 1 -> Color.black;
            case 2 -> Color.red;
            case 3 -> Color.green;
            case 4 -> Color.blue;
            default -> null;
        };
    }
}