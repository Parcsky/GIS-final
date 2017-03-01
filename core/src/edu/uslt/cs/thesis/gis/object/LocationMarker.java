package edu.uslt.cs.thesis.gis.object;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class LocationMarker implements GisObject {

    public Image image;

    public LocationMarker(Skin skin, String imageName) {
        image = new Image();
        image.setDrawable(skin, imageName);
    }


    @Override
    public void setImage(String imageName) {

    }

    @Override
    public void setPosition(float x, float y) {
        image.setPosition(x,y);
    }

    @Override
    public void setVisible(boolean visible) {
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
        return null;
    }

    @Override
    public Image getObject() {
        return image;
    }

    public void debug(){
        image.debug();
    }

    public void setOrigin(float x, float y){
        image.setOrigin(x,y);
    }

}
