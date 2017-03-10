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
        buildingName.setWrap(true);
        buildingInfo = new Label("", skin, "default");

        floor = new Label("1", skin, "default");
        Label floorLabel = new Label("Floor - ", skin, "default");

        container = new Container<Image>();
        container.setActor(new Image(new Texture("doge.jpeg")));

        Table containerTable = new Table(skin);
        containerTable.background("container");
        containerTable.add(buildingName).colspan(2).minSize(0).prefSize(width * .5f, height * .08f).left().row();
        containerTable.add(container).minSize(0).prefSize(width * .5f, height * .3f);
        containerTable.pad(4);

        Table labelTable = new Table(skin);
        labelTable.setBackground("container");
        labelTable.add(floorLabel).minSize(0).prefSize(width * .35f, height * .1f);
        labelTable.add(floor).minSize(0).prefSize(width * .35f, height * .1f).row();
        labelTable.add(buildingInfo).colspan(2).minSize(0).prefSize(width * .65f, height * .6f);
        labelTable.pad(5);

        infoTable = new Table(skin);
        setName(Option.BUILDING_OPTION);
        infoTable.add(containerTable).minSize(0).prefSize(width * .35f, height * .3f).padBottom(5).top().left().row();
        infoTable.add(labelTable).minSize(0).prefSize(width * .35f, height * .1f);
        infoTable.setVisible(false);
        infoTable.bottom().left();
        infoTable.setName(Option.BUILDING_OPTION);
        infoTable.pad(5);
    }

    public void setContainerInfo(Image image, String name, String floorName, String description) {
        container.setActor(image);
        floor.setText(floorName);
        buildingName.setText(name);
        buildingInfo.setText(description);
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
