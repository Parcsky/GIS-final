package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import edu.uslt.cs.thesis.gis.util.FontGenerator;

public class NavigationPanel implements Panel {

    public TextField search;
    public Button menuList;

    private Table table;

    NavigationPanel(Skin skin, int width, int height) {
        if (skin == null) throw new NullPointerException("Skin is null");
        table = new Table(skin);
        table.setBackground("navigation");

        search = new TextField("", skin, "search-little");
        search.setAlignment(Align.center);
        search.getStyle().cursor.setMinHeight(3);
        search.getStyle().cursor.setMinWidth(5);
        search.setBlinkTime(.55f);
        search.getStyle().font = FontGenerator.generate("font/molten.ttf", .05f);

        menuList = new Button(skin, "hide-list");
        menuList.setChecked(false);

        table.add(menuList).top().right().minSize(5, 5).space(width / 2 + 50).prefSize(menuList.getPrefWidth(), 30).pad(5);
        table.add(search).top().left().minSize(5, 5).prefSize(width / 3, 30).pad(5);
        table.left();
    }

    @Override
    public void hide() {
        table.setVisible(false);
    }

    @Override
    public void show() {
        table.setVisible(true);
    }

    @Override
    public void setName(String name) {
        table.setName(name);
    }

    @Override
    public String getName() {
        return table.getName();
    }

    public Table getTable() {
        return table;
    }
}
