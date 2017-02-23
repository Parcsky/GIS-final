package edu.uslt.cs.thesis.gis.screen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;

import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.core.Splash;
import edu.uslt.cs.thesis.gis.resource.Assets;

public class SplashState extends State implements Runnable {

    private Stage stage;

    public SplashState(final GIS gis) {
        super(gis);
        Image uslBanner = Assets.instance().getSplash().uslBanner;
        Image uslLogo = Assets.instance().getSplash().uslLogo;

        Splash bannerSplash = new Splash(uslBanner, 0, 0, gis.width, gis.height);
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
    public void update() {
        stage.act();
    }

    @Override
    public void render() {
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void run() {
        gis.stateManager().add(new MainState(gis));
    }
}
