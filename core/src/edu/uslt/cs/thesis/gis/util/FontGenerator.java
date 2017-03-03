package edu.uslt.cs.thesis.gis.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import edu.uslt.cs.thesis.gis.resource.Assets;

public class FontGenerator {
    public static BitmapFont generate(String pathName,float size) {
        BitmapFont bitmapFont = Assets.instance().get(pathName);
        bitmapFont.getData().scale(size);
        return bitmapFont;
    }
}
