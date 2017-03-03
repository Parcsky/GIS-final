package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import edu.uslt.cs.thesis.gis.util.FontGenerator;

public class NavigationBar extends Table {

    public TextField search;
    public Button hideList;

    public NavigationBar(Skin skin, int width, int height) {
        if (skin == null) throw new NullPointerException("Skin is null");
        setSkin(skin);
        setBackground("navigation");

        search = new TextField("", skin, "search-little");
        search.setAlignment(Align.center);
        search.getStyle().cursor.setMinHeight(3);
        search.getStyle().cursor.setMinWidth(5);
        search.setBlinkTime(.55f);
        search.getStyle().font = FontGenerator.generate("font/molten.ttf", .05f);

        hideList = new Button(skin, "hide-list");

        add(search).top().left().center().minSize(5, 5).prefSize(width / 3, 30).pad(5);
        add(hideList).top().right().center().minSize(5, 5).prefSize(hideList.getPrefWidth(), 30).pad(5).row();

        right().row();
    }
}
