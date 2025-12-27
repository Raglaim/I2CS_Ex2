# Intro2CS - Ex2: Basic Object-Oriented Programming & 2D Maze Algorithms

**Student:** Elad Nagar  
**ID:** 216770164  
**Course:** Introduction to Computer Science, Ariel University

## Project Overview
This project implements a set of algorithms on 2D arrays to represent mazes or images. The core logic relies on Breadth-First Search (BFS) to solve problems such as finding the shortest path between two points, calculating connected components, and filling areas with a specific color.

The project is based on the `Map2D` interface and includes a GUI for visualizing the algorithms using `Java Swing`.

## Project Structure
The project consists of the following main classes:

1.  **MyMap.java**:
    * The main logic class implementing the `Map2D` interface.
    * Handles the 2D array representation of the map.
    * Contains the implementations for BFS, shortest path, and other core algorithms.

2.  **Index2D.java**:
    * A helper class representing a specific `(x, y)` coordinate on the map.
    * Includes logic for converting 2D coordinates to 1D indices and vice versa.

3.  **Ex2_GUI.java**:
    * The graphical user interface class.
    * Uses `StdDraw` to draw the grid and visualize the algorithms (e.g., highlighting the shortest path).

4.  **Tests**:
    * `MyMapTest.java`: JUnit tests for verifying the correctness of the Map class.
    * `Index2DTest.java`: JUnit tests for the Index2D class.

## GUI Output Examples

**Screenshot 1:** map.txt that included with the project.

<img src="map.txt.png" height="200">

**Screenshot 2:** Shortest Path Result

<img src="map.txt.png" height="200">