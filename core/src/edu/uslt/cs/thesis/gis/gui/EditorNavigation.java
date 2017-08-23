package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import edu.uslt.cs.thesis.gis.util.Factory;
import edu.uslt.cs.thesis.gis.util.GuiFactory;

public class EditorNavigation {

    private Skin skin;

    private Label lblTileWidth;
    private Label lblTileHeight;
    private Table mainTable;

    public ImageTextButton btnNewMap;
    public ImageTextButton btnSave;
    public TextField txtTileWidth;
    public TextField txtTileHeight;
    public Button btnMenu;

    public EditorNavigation(Skin skin, int width, int height) {
        this.skin = skin;

        Factory factory = new GuiFactory();

        TextField.TextFieldFilter.DigitsOnlyFilter digits = new TextField.TextFieldFilter.DigitsOnlyFilter();
        txtTileHeight = factory.createTextField(skin, "search-little", false);
        txtTileHeight.setTextFieldFilter(digits);
        txtTileWidth = factory.createTextField(skin, "search-little", false);
        txtTileWidth.setTextFieldFilter(digits);

        btnMenu = new Button(skin, "hide-list");
        btnMenu.setChecked(false);
        btnNewMap = new ImageTextButton("New Map", skin, "default");
        btnNewMap.getLabel().setAlignment(Align.right);
        btnSave = new ImageTextButton("New Building", skin, "exit-btn");
        btnSave.getLabel().setAlignment(Align.right);

        lblTileHeight = new Label("Height", skin);
        lblTileWidth = new Label("Width", skin);

        Table table = new Table();
        table.add(lblTileWidth).minSize(0).prefSize(width / 5, 30);
        table.add(txtTileWidth).minSize(0).prefSize(width / 5, 25).padRight(5);
        table.add(lblTileHeight).minSize(0).prefSize(width / 5, 30);
        table.add(txtTileHeight).minSize(0).prefSize(width / 5, 25).padRight(5);
        table.add(btnNewMap).minSize(0).prefSize(width * .4f, height * .05f);
        table.add(btnSave).minSize(0).prefSize(width * .4f, height * .05f);
        table.add(btnMenu).minSize(0).prefSize(50, 40).left().pad(5);
        table.pad(5);

        mainTable = new Table(skin);
        mainTable.setBackground("navigation");
        mainTable.add(table).minSize(0).prefSize(width, height);
        mainTable.left();

    }

    public Table getMainTable() {
        return mainTable;
    }

}
