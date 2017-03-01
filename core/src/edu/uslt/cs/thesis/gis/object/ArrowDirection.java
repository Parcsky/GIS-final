package edu.uslt.cs.thesis.gis.object;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import edu.uslt.cs.thesis.gis.resource.Assets;

public class ArrowDirection implements GisObject {

    public Image image;

    public ArrowDirection() {
        image = new Image();
        image.setScale(.8f);
    }

    @Override
    public void setImage(String imageName) {
        image.setDrawable(Assets.instance().skin, imageName);
    }

    @Override
    public void setPosition(float x, float y) {

    }

    @Override
    public void setVisible(boolean visible) {
        image.setVisible(visible);
    }

    @Override
    public void setBounds(float x, float y, float width, float height) {
        image.setBounds(x, y, width, height);
    }

    @Override
    public float getX() {
        return image.getX();
    }

    @Override
    public float getY() {
        return image.getY();
    }

    @Override
    public float getWidth() {
        return image.getWidth();
    }

    @Override
    public float getHeight() {
        return image.getHeight();
    }

    @Override
    public String getName() {
        return image.getName();
    }

    public Image getObject() {
        return image;
    }

}
