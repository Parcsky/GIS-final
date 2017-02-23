package edu.uslt.cs.thesis.gis.core;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;


import java.util.ArrayList;
import java.util.List;

import edu.uslt.cs.thesis.gis.object.Building;
import edu.uslt.cs.thesis.gis.object.GisObject;
import edu.uslt.cs.thesis.gis.resource.Assets;
import edu.uslt.cs.thesis.gis.screen.MainState;
import edu.uslt.cs.thesis.gis.screen.SplashState;
import edu.uslt.cs.thesis.gis.screen.StateManager;
import edu.uslt.cs.thesis.gis.util.JsonObjectBuilder;

public class GIS extends ApplicationAdapter {
    List<Building> buildings;

    public int width;
    public int height;
    private StateManager stateManager;

    @Override
    public void create() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        buildings = new ArrayList<Building>();
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder("data/info.json");
        jsonObjectBuilder.build(buildings, "buildings");

        Assets.instance().init(new AssetManager());

        stateManager = new StateManager();
        stateManager.add(new MainState(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(.128f, .128f, .128f, .128f);
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
}
