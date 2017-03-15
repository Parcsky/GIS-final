package edu.uslt.cs.thesis.gis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;

import edu.uslt.cs.thesis.gis.control.GisListener;
import edu.uslt.cs.thesis.gis.control.HudListener;
import edu.uslt.cs.thesis.gis.control.TerrainOptionListener;
import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.map.SaintLouisMap;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;

public class MainState extends State {

    private TiledMapStage mapStage;
    private HUD hud;

    public MainState(GIS gis) {
        super(gis);
        mapStage = gis.getMapStage();
        SaintLouisMap uslMap = gis.getUslMap();

        hud = new HUD(gis.hudSkin, gis.width, gis.height);
        hud.addListener(new HudListener(gis, hud));
        hud.display();

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, gis.width, gis.height);
        camera.update();

        TerrainOptionListener terrainListener = new TerrainOptionListener(gis, hud.getTerrainPanel());
        hud.getTerrainPanel().addListener(terrainListener);

        mapStage.setViewport(new FillViewport(gis.width, gis.height, camera));
        mapStage.addListener( new GisListener(gis, hud));
        mapStage.addActor(gis.getMarker().getObject());
        mapStage.getStage().getViewport().update(gis.width, gis.height);
        uslMap.setCam(mapStage.getCamera());

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hud.getStage());
        inputMultiplexer.addProcessor(mapStage.getStage());
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void update() {
        mapStage.act();
        hud.act();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.25f, .25f, .25f, .25f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gis.getUslMap().render();

        mapStage.getViewport().apply();
        mapStage.draw();

        hud.getStage().getViewport().apply();
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


