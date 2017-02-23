package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class HUD implements HudInterface {

    private Table table;
    private Stage hudStage;
    public SearchPanel searchPanel;

    public HUD(int width, int height) {
        hudStage = new Stage(new StretchViewport(width, height));
        table = new Table();
        table.setFillParent(true);
        table.top().left();
    }

    public void addGroup(SearchPanel searchPanel, int pad) {
        this.searchPanel = searchPanel;
        table.add(searchPanel).pad(pad);
        table.setTransform(false);
    }

    @Override
    public void display() {
        hudStage.addActor(table);
    }

    @Override
    public void resize(int width, int height) {
        hudStage.getViewport().update(width, height);
    }

    @Override
    public void act() {
        hudStage.act();
    }

    @Override
    public void draw() {
        hudStage.draw();
    }

    @Override
    public void dispose() {
        hudStage.dispose();
    }

    public Stage getStage() {
        return hudStage;
    }
}
