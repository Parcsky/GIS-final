package edu.uslt.cs.thesis.gis.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.uslt.cs.thesis.gis.core.GIS;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Geographical Information System: University of Saint Louis Campus";
        config.width = 673;
        config.height = 616;
        new LwjglApplication(new GIS(), config);
    }
}
