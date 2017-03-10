package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;


interface HudInterface {

    void display();

    void resize(int width, int height);

    void act();

    void draw();

    void dispose();

    void hide(Actor actor);

    void show(Actor actor);

    void addListener(EventListener listener);
}
