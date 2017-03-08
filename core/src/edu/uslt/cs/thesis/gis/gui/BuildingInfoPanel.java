package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.uslt.cs.thesis.gis.util.constant.Option;

public class BuildingInfoPanel implements Panel {

    public Container<Image> container;
    private Table infoTable;

    public Label buildingName;
    public Label buildingInfo;
    public Label floor;


    BuildingInfoPanel(Skin skin, int width, int height) {
        buildingName = new Label("Building name", skin, "default");
        buildingInfo = new Label("info", skin, "default");
        floor = new Label("1", skin, "default");
        Label floorLabel = new Label("Floor - ", skin, "default");

        container = new Container<Image>();
        container.setActor(new Image(new Texture("doge.jpeg")));

        Table containerTable = new Table(skin);
        containerTable.background("container");
        containerTable.add(buildingName).minSize(0).prefSize(width * .1f, height * .08f).left().row();
        containerTable.add(container).minSize(0).prefSize(width * .5f, height * .3f);
        containerTable.pad(4);

        Table labelTable = new Table(skin);
        labelTable.setBackground("container");
        labelTable.add(floorLabel).minSize(0).prefSize(width * .35f, height * .1f);
        labelTable.add(floor).minSize(0).prefSize(width * .35f, height * .1f).row();
        labelTable.add(buildingInfo).colspan(2).minSize(0).prefSize(width * .35f, height * .3f);
        labelTable.pad(5);

        infoTable = new Table(skin);
        infoTable.add(containerTable).minSize(0).prefSize(width * .35f, height * .3f).padBottom(5).top().left().row();
        infoTable.add(labelTable).minSize(0).prefSize(width * .35f, height * .1f);
        infoTable.setVisible(false);
        infoTable.bottom().left();
        infoTable.setName(Option.BUILDING_OPTION);
        infoTable.pad(5);
    }

    @Override
    public void hide() {
        infoTable.setVisible(false);
    }

    @Override
    public void show() {
        infoTable.setVisible(true);
    }

    @Override
    public String getName() {
        return infoTable.getName();
    }

    @Override
    public void setName(String name) {
        infoTable.setName(name);
    }

    public Table getTable() {
        return infoTable;
    }
}
