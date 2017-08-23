package edu.uslt.cs.thesis.gis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import edu.uslt.cs.thesis.gis.control.EditorListener;
import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.EditorHud;
import edu.uslt.cs.thesis.gis.map.GisMap;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;

public class EditorState implements Screen {
    private TiledMapStage mapStage;
    private GisMap map;
    private EditorHud editorHud;

    public EditorState(GIS gis) {
        map = gis.getGisMap();
        mapStage = gis.getMapStage();

        editorHud = new EditorHud(gis, map.getTileSets(), gis.hudSkin, gis.width, gis.height);
        editorHud.display();
        editorHud.addListener(new EditorListener(gis, map, editorHud, mapStage));

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(editorHud.getStage());
        multiplexer.addProcessor(mapStage.getStage());
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void show() {

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(.5f, .5f, .5f, .5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        editorHud.act();
        mapStage.act();
        map.render();

        mapStage.getViewport().apply();
        mapStage.draw();

        editorHud.getStage().getViewport().apply();
        editorHud.draw();

    }

    @Override
    public void resize(int width, int height) {
        mapStage.getViewport().update(width, height);
        editorHud.getStage().getViewport().update(width, height);
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
        mapStage.dispose();
        editorHud.dispose();
    }

}
