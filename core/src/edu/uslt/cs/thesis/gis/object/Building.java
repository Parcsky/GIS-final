package edu.uslt.cs.thesis.gis.object;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import edu.uslt.cs.thesis.gis.resource.Assets;

public class Building implements GisObject {

    private Image buildingImage;
    private String room;
    private int floor;
    public int x;
    public int y;

    public Building(String room, int floor, int x, int y) {
        this.floor = floor;
        this.room = room;
        this.x = x;
        this.y = y;
    }

    @Override
    public void setImage(String imageName) {
        TextureAtlas atlas = Assets.instance().getBuildingAtlas();
        TextureRegion region = atlas.findRegion(imageName);
        buildingImage = new Image(region);
    }

    @Override
    public void setPosition(float x, float y) {

    }

    @Override
    public String getName() {
        return room;
    }

    @Override
    public Image getObject() {
        return buildingImage;
    }

    @Override
    public void setVisible(boolean visible) {

    }

    @Override
    public void setBounds(float x, float y, float width, float height) {

    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getWidth() {
        return buildingImage.getWidth();
    }

    @Override
    public float getHeight() {
        return buildingImage.getHeight();
    }
}
