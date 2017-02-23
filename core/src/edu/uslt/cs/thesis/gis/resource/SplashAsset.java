package edu.uslt.cs.thesis.gis.resource;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SplashAsset {

    public Image uslBanner;
    public Image uslLogo;

    SplashAsset(Skin skin) {
        uslBanner = new Image(skin.getRegion("usl-banner"));
        uslLogo = new Image(skin.getRegion("usl-logo"));
    }

}
