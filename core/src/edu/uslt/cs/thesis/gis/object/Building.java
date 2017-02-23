package edu.uslt.cs.thesis.gis.object;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Building implements GisObject {

    private String room;
    private int floor;
    public int x;
    public int y;

    public Building(String room, int floor, int x, int y) {
        this.room = room;
        this.floor = floor;
        this.x = x;
        this.y = y;
    }

    @Override
    public void setDrawable(String imageName) {

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
        return null;
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
        return 0;
    }

    @Override
    public float getHeight() {
        return 0;
    }
}
