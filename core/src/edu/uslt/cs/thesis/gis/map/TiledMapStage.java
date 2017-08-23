package edu.uslt.cs.thesis.gis.map;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.uslt.cs.thesis.gis.algorithm.Node;
import edu.uslt.cs.thesis.gis.control.NodeListener;
import edu.uslt.cs.thesis.gis.object.ArrowDirection;

public class TiledMapStage {

    private TileMap map;
    private Node[][] nodes;
    private Stage stage;
    private String layerName;
    private TiledMapTile tile;

    public TiledMapStage(TileMap map, String layerName) {
        this.layerName = layerName;
        this.map = map;
        stage = new Stage();
        createNodes(map.getTileWidth(), map.getTileHeight(), 16, 16);
    }

    public void setTile(int id) {
        id++;
        this.tile = map.getTile(id);
    }

    public TiledMapTile getTile() {
        return tile;
    }

    public void createNodes(int width, int height, int tileWidth, int tileHeight) {
        nodes = new Node[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                TiledMapTileLayer.Cell cell = map.getLayer(layerName).getCell(x, y);
                nodes[x][y] = new Node(cell, x, y);

                if (cell != null) {
                    ArrowDirection direction = new ArrowDirection();
                    direction.setBounds(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                    nodes[x][y].setArrow(direction);
                    nodes[x][y].setBounds(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                    NodeListener nodeListener = new NodeListener(this, nodes[x][y], direction);
                    nodes[x][y].addListener(nodeListener);
                    nodes[x][y].debug();
                    nodes[x][y].setDebugMode(true);
                    stage.addActor(direction.getObject());
                    stage.addActor(nodes[x][y]);
                }
            }
        }
    }

    public void clearMap() {
        map.getLayer("");
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[0].length; j++) {
                stage.getActors().removeValue(nodes[i][j], false);
                if (nodes[i][j].cell.getTile() != map.getTile(0)) {
                    nodes[i][j].cell.setTile(map.getTile(0));

                }

            }
        }
    }

    public void eraseTile(TiledMapTile tile, Node node) {

    }

    public void act() {
        stage.act();
    }

    public void draw() {
        stage.draw();
    }

    public void setViewport(Viewport viewport) {
        stage.setViewport(viewport);
    }

    public void addActor(Actor actor) {
        stage.addActor(actor);
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    public void addListener(EventListener listener) {
        stage.addListener(listener);
    }

    public Stage getStage() {
        return stage;
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public TileMap getMap() {
        return map;
    }

    public Viewport getViewport() {
        return stage.getViewport();
    }

    public void dispose() {
        stage.dispose();
    }

    public void debug(boolean debug) {
        for (Node[] node : nodes) {
            for (int j = 0; j < nodes[0].length; j++) {
                node[j].setDebugMode(debug);
            }
        }
    }

    public Camera getCamera() {
        return stage.getCamera();
    }
}


