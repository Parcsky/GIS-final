package edu.uslt.cs.thesis.gis.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import edu.uslt.cs.thesis.gis.algorithm.Node;

public interface TileMap {

    TiledMapTileLayer getLayer(String name);

    boolean isValidLocation(int dx, int dy);

    boolean isWalkable(Node node, int x, int y);

    int getTileWidth();

    int getTileHeight();

    int getPixelWidth();

    int getPixelHeight();

    float getWidth();

    float getHeight();

    void setCamView(OrthographicCamera mapCam, float tileWidth, float tileHeight);

    void render();

    void dispose();

    OrthographicCamera getCam();
}
