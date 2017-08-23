package edu.uslt.cs.thesis.gis.gui;


import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import edu.uslt.cs.thesis.gis.util.Factory;
import edu.uslt.cs.thesis.gis.util.GuiFactory;

public class BuildingPanel implements Panel {

    private Table mainTable;
    public TextField txtRoom;
    public TextField txtFloor;
    public TextField txtDesc;
    public TextField txtPosX;
    public TextField txtPosY;

    public BuildingPanel(Skin skin, int width, int height) {
        Factory factory = new GuiFactory();

        Label lblRoom = new Label("Name", skin, "default");
        Label lblFloor = new Label("Floor", skin, "default");
        Label lblDesc = new Label("Description", skin, "default");
        Label lblWidth = new Label("Pos X", skin, "default");
        Label lblHeight = new Label("Pos Y", skin, "default");

        txtRoom = factory.createTextField(skin, "search-little", false);
        txtFloor = factory.createTextField(skin, "search-little", false);
        txtDesc = factory.createTextField(skin, "search-little", false);
        txtPosX = factory.createTextField(skin, "search-little", false);
        txtPosY = factory.createTextField(skin, "search-little", false);

        TextField.TextFieldFilter filter = new TextField.TextFieldFilter.DigitsOnlyFilter();
        txtPosX.setTextFieldFilter(filter);
        txtPosY.setTextFieldFilter(filter);

        Table table = new Table();
        table.add(lblRoom).minSize(0).prefSize(width / 10, 30).pad(1).left();
        table.add(txtRoom).minSize(0).prefSize(width / 5f, 30).pad(1).left().row();
        table.add(lblFloor).minSize(0).prefSize(width / 10, 30).pad(1).left();
        table.add(txtFloor).minSize(0).prefSize(width / 5f, 30).pad(1).left().row();
        table.add(lblWidth).minSize(0).prefSize(width / 10, 30).pad(1).left();
        table.add(txtPosX).minSize(0).prefSize(width / 5f, 30).pad(1).left().row();
        table.add(lblHeight).minSize(0).prefSize(width / 10, 30).pad(1).left();
        table.add(txtPosY).minSize(0).prefSize(width / 5f, 30).pad(1).left().row();
        table.add(lblDesc).minSize(0).prefSize(width / 10, 30).pad(1).left();
        table.add(txtDesc).minSize(0).prefSize(width / 5f, 30).pad(1).left().row();
        table.left();

        mainTable = new Table(skin);
        mainTable.setBackground("container");
        mainTable.add(table).left();
    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    public Table getTable() {
        return mainTable;
    }
}
