package edu.uslt.cs.thesis.gis.map;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;

import edu.uslt.cs.thesis.gis.algorithm.Node;

public interface TileMap {

    TiledMapTileLayer getLayer(String name);

    boolean isValidLocation(int dx, int dy);

    boolean isWalkable(Node node);

    int getTileWidth();

    int getTileHeight();

    int getPixelWidth();

    int getPixelHeight();

    float getWidth();

    float getHeight();

    TiledMapTileSet getTileSet(String name);

    TiledMapTileSets getTileSets();

    TiledMap getTiledMap();

    void render();

    void dispose();

    OrthographicCamera getCam();

    void setCam(Camera camera);

    TiledMapTile getTile(int id);
}
