package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import edu.uslt.cs.thesis.gis.map.GisMap;

public class CameraListener extends ActorGestureListener {
    private GisMap gisMap;
    private Vector3 tp = new Vector3();
    private Label x, y;

    public CameraListener(GisMap gisMap) {
        this.gisMap = gisMap;
    }

    public void setPosition(Label x, Label y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {
        if (this.x == null || this.y == null) return;
        this.x.setText("X: " + (int) event.getTarget().getX());
        this.y.setText("Y: " + (int) event.getTarget().getY());
    }

    @Override
    public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {

        float viewPortHalfX = gisMap.getCam().viewportWidth / 2;
        float viewPortHalfY = gisMap.getCam().viewportHeight / 2;
        float posX = gisMap.getCam().position.x += deltaX * .6f;
        float posY = gisMap.getCam().position.y += deltaY * .6f;
        posX = MathUtils.clamp(posX, viewPortHalfX, gisMap.getWidth() - viewPortHalfX);
        posY = MathUtils.clamp(posY, viewPortHalfY, gisMap.getHeight() - viewPortHalfY);
        gisMap.getCam().position.lerp(tp.set(posX, posY, 0), 0.5f);
    }
}
