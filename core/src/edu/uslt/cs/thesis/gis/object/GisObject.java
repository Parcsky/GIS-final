package edu.uslt.cs.thesis.gis.object;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public interface GisObject {

    void setDrawable(String imageName);

    void setPosition(float x, float y);


    void setVisible(boolean visible);

    void setBounds(float x, float y, float width, float height);

    float getX();

    float getY();

    float getWidth();

    float getHeight();

    String getName();

    Image getObject();
}
