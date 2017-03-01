package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class HUD implements HudInterface {

    private NavigationBar navigationBar;
    private Container<Image> container;
    private ScrollPanel scrollPanel;
    private Label descriptionLabel;
    private Stage hudStage;
    private Table table;


    public HUD(Skin skin, int width, int height) {
        Table display = new Table();
        Window window = new Window("Building name", skin, "dialog");
        container = new Container<Image>();
        window.add(container).top().left();
        container.pad(10);

        display.add(window).minSize(0, 0).prefSize(display.getPrefWidth() / 5, 55).top().left().row();
        container.setActor(new Image(new Texture("dog.JPG")));
        scrollPanel = new ScrollPanel(skin, "default");
        scrollPanel.vKnobSize(5, 5);
        scrollPanel.vScrollSize(15, 15);

        navigationBar = new NavigationBar(skin, width, height);
        hudStage = new Stage(new StretchViewport(width, height));
        table = new Table();
        table.setFillParent(true);
        table.add(navigationBar).minSize(0, 0).colspan(2).prefSize(width, 40).pad(3).row();
        table.add(display).minSize(0, 0).top().left().pad(5);
        table.add(scrollPanel.pane).minSize(width / 2, height / 2).prefSize(width / 2, height / 2).top().right().pad(3, 5, 5, 5);
        table.top();
    }

    public void debug() {
        table.debug();
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

    public void hide(Actor actor) {
        if (table.getChildren().contains(actor, true)) {
            actor.setVisible(false);
        }
    }

    @Override
    public void show(Actor actor) {
        if (table.getChildren().contains(actor, true)) {
            actor.setVisible(true);
        }
    }

    @Override
    public void addListener(EventListener listener) {
        hudStage.addListener(listener);
    }

    public Stage getHud() {
        return hudStage;
    }

    public ScrollPanel getScrollPanel() {
        return scrollPanel;
    }

    public NavigationBar getNavigationBar() {
        return navigationBar;
    }

    public Container<Image> getContainer() {
        return container;
    }
}
