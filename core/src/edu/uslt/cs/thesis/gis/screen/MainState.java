package edu.uslt.cs.thesis.gis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import edu.uslt.cs.thesis.gis.control.GisListener;
import edu.uslt.cs.thesis.gis.control.HudListener;
import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.map.TileMap;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;

public class MainState extends State {

    private TiledMapStage mapStage;
    private HUD hud;

    public MainState(GIS gis) {
        super(gis);
        TileMap uslMap = gis.getUslMap();

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, uslMap.getWidth(), uslMap.getHeight() + 100);
        camera.update();

        mapStage = new TiledMapStage(gis.getUslMap());
        mapStage.setViewport(new StretchViewport(uslMap.getWidth(), uslMap.getHeight(), camera));
        mapStage.addActor(gis.getMarker().getObject());
        uslMap.setCam(mapStage.getCamera());


        hud = new HUD(gis.hudSkin, gis.width, gis.height);
        hud.addListener(new HudListener(gis, hud));
        hud.display();

        GisListener gisListener = new GisListener(gis, mapStage, hud);
        mapStage.addListener(gisListener);

        InputMultiplexer inputMultiplexer = new InputMultiplexer(hud.getHud(), mapStage.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void update() {
        mapStage.act();
        hud.act();
    }

    @Override
    public void render() {
        gis.getUslMap().render();

        mapStage.getViewport().apply();
        mapStage.draw();

        hud.getHud().getViewport().apply();
        hud.draw();
    }

    @Override
    public void resize(int width, int height) {
        hud.resize(width, height);
        mapStage.resize(width, height);
    }

    @Override
    public void dispose() {
        gis.getUslMap().dispose();
        hud.dispose();
        mapStage.dispose();
    }
}


