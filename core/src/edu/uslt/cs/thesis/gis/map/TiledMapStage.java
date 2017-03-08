package edu.uslt.cs.thesis.gis.map;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.uslt.cs.thesis.gis.algorithm.Node;
import edu.uslt.cs.thesis.gis.object.ArrowDirection;
import edu.uslt.cs.thesis.gis.resource.Assets;

public class TiledMapStage {

    private TileMap map;
    private Node[][] nodes;
    private Stage stage;

    public TiledMapStage(TileMap map) {
        this.map = map;
        stage = new Stage();
        createNodes(map.getTileWidth(), map.getTileHeight(), 16, 16);
    }

    private void createNodes(int width, int height, int tileWidth, int tileHeight) {
        nodes = new Node[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                TiledMapTileLayer.Cell cell = map.getLayer("path").getCell(x, y);
                nodes[x][y] = new Node(cell, x, y);

                if (cell != null) {
                    ArrowDirection direction = new ArrowDirection();
                    direction.setBounds(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                    nodes[x][y].setArrow(direction);
                    nodes[x][y].setBounds(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
                    stage.addActor(nodes[x][y]);
                    stage.addActor(direction.getObject());
                }
            }
        }
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

    public void debug() {
        stage.setDebugAll(true);
    }

    public Camera getCamera() {
        return stage.getCamera();
    }
}
