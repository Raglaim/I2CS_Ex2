package assignments.Ex2.MainFiles.GUI;

import assignments.Ex2.MainFiles.Classes.Interfaces.Map2D;
import assignments.Ex2.MainFiles.Classes.MyMap;

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
        JFrame frame = new JFrame();
        frame.setTitle("Map GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(map.getWidth(), map.getHeight()));

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                JPanel cell = new JPanel();
                int val = map.getPixel(x, y);
                switch (val) {
                    case 0 -> cell.setBackground(Color.white);
                    case 1 -> cell.setBackground(Color.black);
                    case 2 -> cell.setBackground(Color.red);
                    case 3 -> cell.setBackground(Color.green);
                    case 4 -> cell.setBackground(Color.blue);
                    default -> cell.setBackground(Color.gray);
                }
                frame.add(cell);
            }
        }
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
    private static boolean checkTxt(String fileName) {
        return fileName.toLowerCase().endsWith(".txt");
    }
}