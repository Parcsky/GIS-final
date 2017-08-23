package edu.uslt.cs.thesis.gis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;

import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.core.Splash;
import edu.uslt.cs.thesis.gis.resource.Assets;

public class SplashState implements Runnable, Screen {

    private Stage stage;
    private GIS gis;

    public SplashState(final GIS gis) {
        this.gis = gis;
        Image uslBanner = Assets.instance().getSplash().uslBanner;
        Image uslLogo = Assets.instance().getSplash().uslLogo;

        float bannerHeight = gis.height / 2 - uslBanner.getHeight() / 2;
        Splash bannerSplash = new Splash(uslBanner, 0, bannerHeight, gis.width, uslBanner.getHeight());
        Splash logoSplash = new Splash(uslLogo, 0, 0, gis.width, gis.height);

        bannerSplash.setRunnable(false);
        logoSplash.setRunnable(true);

        bannerSplash.doSplash(0, this);
        logoSplash.doSplash(7, this);

        stage = new Stage(new FillViewport(gis.width, gis.height));
        stage.addActor(logoSplash.getImage());
        stage.addActor(bannerSplash.getImage());
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
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

    @Override
    public void run() {
        gis.setScreen(new LogInState(gis));
    }
}
