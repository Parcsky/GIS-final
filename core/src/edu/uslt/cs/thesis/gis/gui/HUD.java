package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class HUD implements HudInterface {

    private NavigationBar navigationBar;
    private Container<Image> container;
    private ScrollPanel scrollPanel;
    private Label description;
    private ImageButton hidePanel;
    private Stage hudStage;
    private Window window;
    private Table table;


    public HUD(Skin skin, int width, int height) {
        description = new Label("", skin, "default");
        description.setText("Information: \n blah blah blah blah blah.....");
        description.setAlignment(Align.topLeft);
        description.setFontScale(.8f);
        description.setWrap(true);

        container = new Container<Image>();
        container.setActor(new Image(new Texture("doge.jpeg")));

        window = new Window("Building Name", skin, "dialog");
        window.getTitleTable().top().left().row();
        window.getTitleLabel().setFontScale(1f);
        window.getTitleLabel().setWrap(true);

        window.add(container).padTop(window.getTitleTable().getPrefHeight()).minSize(0, 0).prefSize(window.getPrefWidth(), 150).top().left().row();
        window.add(description).minSize(0, 0).prefSize(window.getPrefWidth(), 100).top().left();
        window.pad(0, 5, 5, 5);

        scrollPanel = new ScrollPanel(skin, "default");
        scrollPanel.vScrollSize(15, 15);
        scrollPanel.vKnobSize(5, 5);

        hidePanel = new ImageButton(skin, "default");

        navigationBar = new NavigationBar(skin, width, height);
        hudStage = new Stage(new StretchViewport(width, height));

        table = new Table();
        table.setFillParent(true);
        table.add(navigationBar).minSize(0, 0).colspan(2).prefSize(width, 40).pad(3).top().row();
        table.add(hidePanel).minSize(0, 0).prefSize(20, 20).left().row();
        table.add(window).minSize(0, 0).prefSize(200, 250).top().left().pad(5);
        table.add(scrollPanel.pane).minSize(width / 2, height / 2).prefSize(width / 2, height / 2).top().right().pad(0, 5, 5, 5);
        table.top();

        hudStage.addActor(table);
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
        if (table.getChildren().contains(actor, false)) {
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

    public ImageButton getHidePanel() {
        return hidePanel;
    }

    public Window getWindowPanel() {
        return window;
    }
}
