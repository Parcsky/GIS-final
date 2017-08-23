package edu.uslt.cs.thesis.gis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import edu.uslt.cs.thesis.gis.control.LogInListener;
import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.panels.LoginPanel;

public class LogInState implements Screen {

    private Stage stage;

    public LogInState(GIS gis) {
        LoginPanel loginPanel = new LoginPanel(gis.hudSkin, gis.width, gis.height);
        LogInListener logInListener = new LogInListener(loginPanel, gis);

        stage = new Stage(new FillViewport(gis.width, gis.height));
        stage.addActor(loginPanel.getTable());
        stage.addListener(logInListener);

        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.4f, .4f, .4f, .4f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
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
        stage.dispose();
    }
}
