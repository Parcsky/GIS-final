package edu.uslt.cs.thesis.gis.algorithm;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import edu.uslt.cs.thesis.gis.object.ArrowDirection;

public class Node extends Actor implements Comparable<Node> {

    public TiledMapTileLayer.Cell cell;
    int x;
    int y;
    float h;
    float g;
    float f;

    public ArrowDirection arrow;
    Node parent;
    private TiledMapTile tile;
    private boolean debugMode;

    public Node(TiledMapTileLayer.Cell cell, int x, int y) {
        this.cell = cell;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node other) {
        if (this.f < other.f) return -1;
        else if (this.f > other.f) return 1;
        return 0;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
        this.setDebug(debugMode);
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setArrow(ArrowDirection arrow) {
        this.arrow = arrow;
    }
}
