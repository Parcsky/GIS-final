package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import edu.uslt.cs.thesis.gis.util.FontGenerator;

public class SearchPanel extends Table {

    public ScrollPanel scrollPanel;
    public TextField search;
    public Button button;

    public SearchPanel(Skin skin, int width, int height) {
        if (skin == null) throw new NullPointerException("Skin is null");

        search = new TextField("", skin, "default");
        search.setAlignment(Align.center);
        search.getStyle().cursor.setMinHeight(3);
        search.getStyle().cursor.setMinWidth(10);
        search.setBlinkTime(.55f);
        search.getStyle().font = FontGenerator.generate("font/molten.ttf", .05f);

        button = new Button(skin, "play");

        scrollPanel = new ScrollPanel(skin, "default");
        scrollPanel.vKnobSize(5, 5);
        scrollPanel.vScrollSize(15, 15);

        add(button).top().left().pad(5);
        add(search).top().left().minSize(5, 5).prefSize(width / 4, 30).pad(5).row();
        add(scrollPanel.pane).top().left().colspan(3).minSize(5, 5).prefSize(width / 4, height / 2).pad(5).fillX();

        top().left();
    }
}
