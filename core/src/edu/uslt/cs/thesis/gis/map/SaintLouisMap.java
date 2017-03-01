package edu.uslt.cs.thesis.gis.map;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import edu.uslt.cs.thesis.gis.algorithm.Node;
import edu.uslt.cs.thesis.gis.resource.Assets;

public class SaintLouisMap implements TileMap {

    private TiledMapRenderer mapRenderer;
    private MapProperties mapProperties;
    private OrthographicCamera camera;
    private TiledMap tiledMap;

    public SaintLouisMap(String path) {
        if (path.length() <= 0) throw new IllegalStateException(path + "is < 0");
        tiledMap = Assets.instance().get(path);

        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        mapProperties = tiledMap.getProperties();
    }

    @Override
    public void setCamView(OrthographicCamera camera, float width, float height) {
        this.camera = camera;
        if (camera == null) throw new NullPointerException("Camera is null");
        camera.setToOrtho(false, width, height);
        camera.update();
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
    public boolean isWalkable(Node node, int x, int y) {
        return node.cell != null || x == 0 && y == 0;
    }

    @Override
    public boolean isValidLocation(int dx, int dy) {
        return dx >= 0 && dx < getTileWidth() &&
                dy >= 0 && dy <= getTileHeight();
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

}
