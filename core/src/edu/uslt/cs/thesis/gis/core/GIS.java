package edu.uslt.cs.thesis.gis.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import edu.uslt.cs.thesis.gis.manager.BuildingManager;
import edu.uslt.cs.thesis.gis.manager.StateManager;
import edu.uslt.cs.thesis.gis.map.SaintLouisMap;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;
import edu.uslt.cs.thesis.gis.object.LocationMarker;
import edu.uslt.cs.thesis.gis.resource.Assets;
import edu.uslt.cs.thesis.gis.screen.MainState;
import edu.uslt.cs.thesis.gis.screen.SplashState;
import edu.uslt.cs.thesis.gis.util.JsonObjectBuilder;

public class GIS extends ApplicationAdapter {

    private StateManager stateManager;
    private TiledMapStage mapStage;
    private LocationMarker marker;
    public SaintLouisMap uslMap;

    public BuildingManager buildingManager;
    public Skin hudSkin;
    public int height;
    public int width;

    @Override
    public void create() {
        Assets.instance().init(new AssetManager());
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        buildingManager = new BuildingManager();
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder("data/info.json");
        jsonObjectBuilder.build(buildingManager.getBuildings(), "buildings");

        uslMap = new SaintLouisMap("map/usl-map.tmx");
        mapStage = new TiledMapStage(uslMap);

        hudSkin = Assets.instance().skin;
        marker = new LocationMarker(hudSkin, "location-marker");
        marker.setBounds(uslMap.getWidth() / 2, uslMap.getHeight() / 2, 50, 50);

        stateManager = new StateManager();
        stateManager.add(new SplashState(this));
    }

    @Override
    public void render() {
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

    public SaintLouisMap getUslMap() {
        return uslMap;
    }

    public LocationMarker getMarker() {
        return marker;
    }

    public TiledMapStage getMapStage() {
        return mapStage;
    }
}
