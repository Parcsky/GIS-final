package edu.uslt.cs.thesis.gis.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import edu.uslt.cs.thesis.gis.manager.BuildingManager;
import edu.uslt.cs.thesis.gis.manager.StateManager;
import edu.uslt.cs.thesis.gis.map.SaintLouisMap;
import edu.uslt.cs.thesis.gis.map.TileMap;
import edu.uslt.cs.thesis.gis.object.LocationMarker;
import edu.uslt.cs.thesis.gis.resource.Assets;
import edu.uslt.cs.thesis.gis.screen.MainState;
import edu.uslt.cs.thesis.gis.util.JsonObjectBuilder;

public class GIS extends ApplicationAdapter {

    private StateManager stateManager;
    private LocationMarker marker;
    private TileMap uslMap;

    public BuildingManager buildingManager;
    public Skin hudSkin;
    public int height;
    public int width;

    @Override
    public void create() {
        Assets.instance().init(new AssetManager());

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        uslMap = new SaintLouisMap("map/usl-map-final.tmx");
        buildingManager = new BuildingManager();

        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder("data/info.json");
        jsonObjectBuilder.build(buildingManager.getBuildings(), "buildings");

        hudSkin = Assets.instance().skin;
        marker = new LocationMarker(hudSkin, "location-marker");
        marker.setBounds(getUslMap().getWidth() / 2, getUslMap().getHeight() / 2, 50, 50);

        stateManager = new StateManager();
        stateManager.add(new MainState(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateManager.update();
        stateManager.render();
    }

    @Override
    public void resize(int width, int height) {
        stateManager.resize(width, height);
    }

    @Override
    public void dispose() {
        Assets.instance().dispose();
        stateManager.dispose();
    }

    public StateManager stateManager() {
        return stateManager;
    }

    public TileMap getUslMap() {
        return uslMap;
    }

    public LocationMarker getMarker() {
        return marker;
    }
}
