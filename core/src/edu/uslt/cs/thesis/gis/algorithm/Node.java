package edu.uslt.cs.thesis.gis.algorithm;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import edu.uslt.cs.thesis.gis.object.ArrowDirection;

public class Node extends Actor implements Comparable<Node> {

    public TiledMapTileLayer.Cell cell;
    int x;
    int y;
    float h;
    float g;
    float f;

    public ArrowDirection arrow;
    public Label label;
    Node parent;

    public Node(TiledMapTileLayer.Cell cell, int x, int y) {
        this.cell = cell;
        this.x = x;
        this.y = y;
        this.label = label;
    }

    @Override
    public int compareTo(Node other) {
        if (this.f < other.f) return -1;
        else if (this.f > other.f) return 1;
        return 0;
    }

    public void setArrow(ArrowDirection arrow) {
        this.arrow = arrow;
    }
}
