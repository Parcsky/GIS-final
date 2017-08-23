package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;

import edu.uslt.cs.thesis.gis.gui.panels.BuildingInfoPanel;
import edu.uslt.cs.thesis.gis.gui.panels.MenuPanel;
import edu.uslt.cs.thesis.gis.gui.panels.NavigationPanel;
import edu.uslt.cs.thesis.gis.gui.panels.ScrollPanel;
import edu.uslt.cs.thesis.gis.gui.panels.TerrainPanel;
import edu.uslt.cs.thesis.gis.gui.panels.ZoomPanel;


public class HUD implements HudInterface {

    private BuildingInfoPanel buildingInfoPanel;
    private NavigationPanel navigationPanel;
    private TerrainPanel terrainPanel;
    private ScrollPanel scrollPanel;
    private MenuPanel menuPanel;
    private ZoomPanel zoomPanel;

    private Stage hudStage;
    private Table table;

    public HUD(Skin skin, int width, int height) {
        hudStage = new Stage(new FillViewport(width, height));

        buildingInfoPanel = new BuildingInfoPanel(skin, width, height);
        navigationPanel = new NavigationPanel(skin, width, height);
        terrainPanel = new TerrainPanel(skin, width, height);
        zoomPanel = new ZoomPanel(skin, width, height);
        scrollPanel = new ScrollPanel(skin, "default");
        scrollPanel.vScrollSize(15, 15);
        scrollPanel.vKnobSize(5, 5);

        String[][] buttonName = {{"Building Info", "Terrain Info", "Quit"}, {"default", "setting-btn", "exit-btn"}};

        menuPanel = new MenuPanel(skin, buttonName);

        Stack stack = new Stack();
        stack.setName("stack");
        stack.add(zoomPanel.getTable());
        stack.add(scrollPanel.getTable());

        table = new Table();
        table.setFillParent(true);
        table.add(navigationPanel.getTable()).minSize(0).colspan(2).prefSize(width, height * .1f).pad(2).top().row();
        table.add(menuPanel.getTable()).minSize(0).prefSize(width * .35f, height * .25f).pad(2).top().left();
        table.add(stack).minSize(0).prefSize(width * .35f, height * .45f).top().right().pad(0, 5, 5, 5).row();
        table.add(buildingInfoPanel.getTable()).minSize(0).prefSize(width * .5f, height * .9f).bottom().left();
        table.add(terrainPanel.getTable()).minSize(0).prefSize(width * .25f, height * .25f).pad(5).bottom().right();
        table.top();
    }

    @Override
    public void display() {
        hudStage.addActor(table);
    }

    @Override
    public void resize(int width, int height) {
        hudStage.getViewport().update(width, height, true);
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

    @Override
    public void hide(Actor actor) {
        for (int i = 0; i < table.getChildren().size; i++) {
            if (table.getChildren().get(i).equals(actor)) {
                table.getChildren().get(i).setVisible(false);
                return;
            }
        }
    }

    @Override
    public void show(Actor actor) {
        for (int i = 0; i < table.getChildren().size; i++) {
            if (table.getChildren().get(i).equals(actor)) {
                table.getChildren().get(i).setVisible(true);
                return;
            }
        }
    }

    public Actor getPanel(String name) {
        for (int i = 0; i < table.getChildren().size; i++) {
            if (table.getChildren().get(i).getName().equals(name))
                return table.getChildren().get(i);
        }
        return null;
    }

    @Override
    public void addListener(EventListener listener) {
        hudStage.addListener(listener);
    }

    public Stage getStage() {
        return hudStage;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public BuildingInfoPanel getBuildingInfoPanel() {
        return buildingInfoPanel;
    }

    public NavigationPanel getNavigationPanel() {
        return navigationPanel;
    }

    public ScrollPanel getScrollPanel() {
        return scrollPanel;
    }

    public TerrainPanel getTerrainPanel() {
        return terrainPanel;
    }

    public Table getTable() {
        return table;
    }

    public ZoomPanel getZoomPanel() {
        return zoomPanel;
    }

    public void debug() {
        table.debug();
    }

}
