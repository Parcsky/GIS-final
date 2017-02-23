package edu.uslt.cs.thesis.gis.map;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import edu.uslt.cs.thesis.gis.algorithm.Node;
import edu.uslt.cs.thesis.gis.object.ArrowDirection;
import edu.uslt.cs.thesis.gis.resource.Assets;

public class TiledMapStage extends Stage {

    private TileMap map;
    private Node[][] nodes;

    public TiledMapStage(TileMap map) {
        this.map = map;
    }

    public void createNodes(int width, int height, int tileWidth, int tileHeight) {
        nodes = new Node[width][height];
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = Assets.instance().get("font/molten.ttf");

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                TiledMapTileLayer.Cell cell = map.getLayer("path").getCell(x, y);

                nodes[x][y] = new Node(cell, x, y);

                if (cell != null) {
                    ArrowDirection direction = new ArrowDirection();
                    direction.setBounds(x * tileWidth, y * tileHeight, tileWidth, tileHeight);

                    nodes[x][y].setArrow(direction);
                    nodes[x][y].setBounds(x * tileWidth, y * tileHeight, tileWidth, tileHeight);

                    addActor(nodes[x][y]);
                    addActor(direction.getObject());
                }
            }
        }
    }

    public void resize(int width, int height) {
        this.getViewport().update(width, height);
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public TileMap getMap() {
        return map;
    }
}
