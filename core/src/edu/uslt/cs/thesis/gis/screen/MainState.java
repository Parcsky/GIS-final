package edu.uslt.cs.thesis.gis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import edu.uslt.cs.thesis.gis.control.TerrainOptionListener;
import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.map.GisMap;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;

public class MainState implements Screen {
    private TiledMapStage mapStage;
    private HUD HUD;
    private GisMap gisMap;

    public MainState(GIS gis) {
        HUD = gis.HUD;
        mapStage = gis.getMapStage();
        gisMap = gis.getGisMap();

        mapStage.addListener(gis.getGisListener());

        TerrainOptionListener terrainListener = new TerrainOptionListener(gisMap, HUD.getTerrainPanel());
        HUD.getTerrainPanel().addListener(terrainListener);

        mapStage.getStage().getViewport().update(gis.width, gis.height);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(HUD.getStage());
        inputMultiplexer.addProcessor(mapStage.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.5f, .5f, .5f, .5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapStage.act();
        HUD.act();

        gisMap.render();

        mapStage.getViewport().apply();
        mapStage.draw();

        HUD.getStage().getViewport().apply();
        HUD.draw();
    }

    @Override
    public void resize(int width, int height) {
        mapStage.resize(width, height);
        HUD.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gisMap.dispose();
        mapStage.dispose();
        HUD.dispose();

    }
}


