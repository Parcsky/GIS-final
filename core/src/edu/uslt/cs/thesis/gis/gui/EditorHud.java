package edu.uslt.cs.thesis.gis.gui;


import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;

import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.panels.MenuPanel;
import edu.uslt.cs.thesis.gis.gui.panels.TileSetPanel;

public class EditorHud implements HudInterface {

    private Stage stage;
    private Table table;
    private EditorNavigation editorNav;
    private TileSetPanel tileSetPanel;
    private MenuPanel menuPanel;
    private BuildingPanel buildingPanel;

    public BuildingPanel getBuildingPanel() {
        return buildingPanel;
    }

    public EditorHud(GIS gis, TiledMapTileSets tileSet, Skin skin, int width, int height) {

        tileSetPanel = new TileSetPanel(tileSet, width, height);
        editorNav = new EditorNavigation(skin, width, height);
        buildingPanel = new BuildingPanel(skin, width, height);
        String[][] buttonName = {{"Add Tile", "User Log In", "Debug"}, {"default", "setting-btn", "exit-btn"}};
        menuPanel = new MenuPanel(skin, buttonName);


        Label x = new Label("X: ", skin, "default");
        Label y = new Label("Y: ", skin, "default");
        gis.cameraListener.setPosition(x, y);


        Table posTable = new Table(skin);
        posTable.setBackground("rectangle");
        posTable.add(x).row();
        posTable.add(y);
        posTable.top().left();
        posTable.pad(5);

        table = new Table();
        table.setFillParent(true);
        table.add(editorNav.getMainTable()).colspan(3).minSize(0).prefSize(width, height * .07f).row();
        table.add(tileSetPanel.getMainTable()).minSize(0).prefSize(width * .2f, height * .4f).top().left();
        table.add(posTable).minSize(0).prefSize(width * .15f, height * .1f).padTop(2).top().left();
        table.add(menuPanel.getTable()).minSize(0).prefSize(width * .35f, height * .25f).pad(2).top().left().row();
        table.add(buildingPanel.getTable()).colspan(2).minSize(0).prefSize(width * .35f, height * .32f).left();
        table.pad(3);
        table.top();

        stage = new Stage(new FillViewport(width, height));
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void display() {
        stage.addActor(table);

    }

    public EditorNavigation getEditorNav() {
        return editorNav;
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void act() {
        stage.act();
    }

    public TileSetPanel getTileSetPanel() {
        return tileSetPanel;
    }

    @Override
    public void draw() {
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void hide(Actor actor) {

    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    @Override
    public void show(Actor actor) {

    }

    @Override
    public void addListener(EventListener listener) {
        stage.addListener(listener);
    }
}
