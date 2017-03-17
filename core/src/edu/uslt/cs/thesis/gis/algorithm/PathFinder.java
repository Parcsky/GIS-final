package edu.uslt.cs.thesis.gis.algorithm;

public interface PathFinder {

    Path findPath(int startX, int startY, int goalX, int goalY);


    void clearNodes();
}
