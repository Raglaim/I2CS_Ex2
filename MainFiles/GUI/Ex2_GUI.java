package assignments.Ex2.MainFiles.GUI;

import assignments.Ex2.MainFiles.Classes.Index2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Map2D;
import assignments.Ex2.MainFiles.Classes.Interfaces.Pixel2D;
import assignments.Ex2.MainFiles.Classes.MyMap;
import assignments.Ex2.MainFiles.Classes.PixelsContainer;
import assignments.Ex2.MainFiles.Classes.StdDraw;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

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
            for (int x = 0; x < map.getWidth(); x+=1) {
                if (!(map.getPixel(x, y) == 0)) {
                    setColor(map.getPixel(x,y));
                    StdDraw.filledSquare(x+0.5,y+0.5,0.502);
                }
            }
        }
    }

    /**
     * @param mapFileName
     * @return
     */
    public static MyMap loadMap(String mapFileName) {
        if (checkTxt(mapFileName)) {
            Path targetDir = Paths.get("C:\\Users\\Elad Nagar\\IdeaProjects\\I2CS_Ex2\\MainFiles\\GUI\\SavedMaps");
            Path path = targetDir.resolve(mapFileName);
            String content = "";
            try {
                content = Files.readString(path);
            }
            catch (IOException e) {e.printStackTrace();}
            return MyMap.mapFromString(content);
        } else {
            return new MyMap();
        }
    }

    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {
        Path targetDir = Paths.get("C:\\Users\\Elad Nagar\\IdeaProjects\\I2CS_Ex2\\MainFiles\\GUI\\SavedMaps");
        Path path = targetDir.resolve(mapFileName);
        String content = map.toString();
        try {
            Files.writeString(path, content);
        }
        catch (IOException e) {e.printStackTrace();}
    }

    public static void main(String[] a) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Doing some work...");
        Pixel2D start = new Index2D(0, 0);
        Map2D maze = mazeGen(20,start);
        drawMap(maze);
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
        System.out.println("Continuing...");
        start = new Index2D(1, 1);
        Pixel2D end = new Index2D(maze.getWidth()-2,maze.getHeight()-2);
        Pixel2D[] path = maze.shortestPath(start,end,1,false);
        System.out.println(path.length-1);
        for (Pixel2D p : path) {
            maze.setPixel(p,3);
        }
        drawMap(maze);
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

    public static Map2D mazeGen(int size, Pixel2D start){
        int obsColor = 1;
        MyMap mazeMap = new MyMap(size);

        // setting up the maze grid
        int mazeSize = ((size * 2) + 1);
        Map2D maze = new MyMap(mazeSize);
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
}