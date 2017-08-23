package edu.uslt.cs.thesis.gis.util;

import edu.uslt.cs.thesis.gis.map.GisMap;

public class SaveMap {

    private GisMap gisMap;

    public SaveMap(GisMap gisMap) {
        this.gisMap = gisMap;
    }

    public GisMap loadMap() {
        return gisMap;
    }
}
