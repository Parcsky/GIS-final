package edu.uslt.cs.thesis.gis.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FillViewport;

import edu.uslt.cs.thesis.gis.control.CameraListener;
import edu.uslt.cs.thesis.gis.control.GisListener;
import edu.uslt.cs.thesis.gis.control.HudListener;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.manager.BuildingManager;
import edu.uslt.cs.thesis.gis.map.GisMap;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;
import edu.uslt.cs.thesis.gis.object.LocationMarker;
import edu.uslt.cs.thesis.gis.resource.Assets;
import edu.uslt.cs.thesis.gis.screen.LogInState;
import edu.uslt.cs.thesis.gis.screen.SplashState;
import edu.uslt.cs.thesis.gis.util.JsonObjectBuilder;
import edu.uslt.cs.thesis.gis.util.SaveMap;

public class GIS extends Game {

    private GisMap gisMap;

    public BuildingManager buildingManager;
    public Skin hudSkin;
    public int height;
    public SaveMap saveMap;
    private List<String> list;
    public int width;
    private TiledMapStage mapStage;
    public LocationMarker marker;
    public JsonObjectBuilder jsonBuilder;
    public CameraListener cameraListener;
    public HUD HUD;
    public GisListener gisListener;
    private Game game;

    public GIS() {
        game = this;
    }

    @Override
    public void create() {
        Assets.instance().init(new AssetManager());
        hudSkin = Assets.instance().skin;

        buildingManager = new BuildingManager();

        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        list = new List<String>(hudSkin, "default");

        HUD = new HUD(hudSkin, width, height);
        HUD.addListener(new HudListener(this, HUD));
        HUD.display();

        gisMap = new GisMap("map/edit-map.tmx");
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();

        marker = new LocationMarker(hudSkin, "location-marker");
        marker.setBounds(gisMap.getWidth() / 2, gisMap.getHeight() / 2, 50, 50);
        marker.setPosition(0, 0);

        cameraListener = new CameraListener(gisMap);
        mapStage = new TiledMapStage(gisMap, "main-layer");
        mapStage.addListener(cameraListener);
        mapStage.setViewport(new FillViewport(width, height, camera));
        mapStage.getStage().getViewport().update(width, height);
        mapStage.addActor(marker.getObject());

        gisListener = new GisListener(this, mapStage, gisMap, HUD);

        gisMap.setCam(camera);

        jsonBuilder = new JsonObjectBuilder("data/info.json");

        setScreen(new SplashState(this));
    }

    public GisListener getGisListener() {
        return gisListener;
    }

    public List<String> getList() {
        return list;
    }

    public TiledMapStage getMapStage() {
        return mapStage;
    }

    @Override
    public void render() {
       super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        Assets.instance().dispose();
        screen.dispose();
    }
    public BuildingManager getBuildingManager() {
        return buildingManager;
    }

    public GisMap getGisMap() {
        return gisMap;
    }
}
