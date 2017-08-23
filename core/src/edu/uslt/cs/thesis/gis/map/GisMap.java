package edu.uslt.cs.thesis.gis.map;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import edu.uslt.cs.thesis.gis.algorithm.Node;
import edu.uslt.cs.thesis.gis.resource.Assets;

public class GisMap implements TileMap {

    private TiledMapRenderer mapRenderer;
    private MapProperties mapProperties;
    private OrthographicCamera camera;
    private MapLayers mapLayers;
    private TiledMap tiledMap;

    public GisMap(String path) {
        if (path.length() <= 0) throw new IllegalStateException(path + "is < 0");
        tiledMap = Assets.instance().get(path);
        mapLayers = tiledMap.getLayers();
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        mapProperties = tiledMap.getProperties();
    }

    @Override
    public void render() {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    @Override
    public void dispose() {
        tiledMap.dispose();
    }

    @Override
    public boolean isWalkable(Node node) {
        return node.cell.getTile().getId() == 1;
    }

    @Override
    public boolean isValidLocation(int dx, int dy) {
        return dx >= 0 && dx < getTileWidth() && dy >= 0 && dy <= getTileHeight();
    }

    @Override
    public TiledMapTileLayer getLayer(String name) {
        return (TiledMapTileLayer) tiledMap.getLayers().get(name);
    }

    @Override
    public int getTileWidth() {
        return mapProperties.get("width", Integer.class);
    }

    @Override
    public int getTileHeight() {
        return mapProperties.get("height", Integer.class);
    }

    @Override
    public int getPixelWidth() {
        return mapProperties.get("tilewidth", Integer.class);
    }

    @Override
    public int getPixelHeight() {
        return mapProperties.get("tileheight", Integer.class);
    }

    @Override
    public float getWidth() {
        return getPixelWidth() * getTileWidth();
    }

    @Override
    public float getHeight() {
        return getPixelHeight() * getTileHeight();
    }

    public OrthographicCamera getCam() {
        return camera;
    }

    @Override
    public void setCam(Camera camera) {
        this.camera = (OrthographicCamera) camera;
    }

    @Override
    public TiledMapTile getTile(int id) {
        return tiledMap.getTileSets().getTile(id);
    }

    public void setLayerVisible(String layerName, boolean visible) {
        mapLayers.get(layerName).setVisible(visible);
    }

    @Override
    public TiledMapTileSet getTileSet(String name) {
        return tiledMap.getTileSets().getTileSet(name);
    }

    @Override
    public TiledMapTileSets getTileSets() {
        return tiledMap.getTileSets();
    }

    @Override
    public TiledMap getTiledMap() {
        return tiledMap;
    }
}
