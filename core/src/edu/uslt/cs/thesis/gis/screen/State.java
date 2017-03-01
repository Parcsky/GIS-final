package edu.uslt.cs.thesis.gis.screen;

import edu.uslt.cs.thesis.gis.core.GIS;

public abstract class State {

    public GIS gis;

    State(GIS gis) {
        this.gis = gis;
    }

    public abstract void update();

    public abstract void render();

    public abstract void resize(int width, int height);

    public abstract void dispose();
}
