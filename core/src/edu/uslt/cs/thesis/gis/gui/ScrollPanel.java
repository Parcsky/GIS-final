package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class ScrollPanel {

    public ScrollPane pane;
    public List<String> list;

    private Array<String> temp;

    ScrollPanel(Skin skin, String style) {
        if (skin == null) throw new NullPointerException("Scroll panel skin is null");
        temp = new Array<String>();
        list = new List<String>(skin, style);

        pane = new ScrollPane(list, skin, style);
        pane.setFlickScroll(true);
        pane.setupOverscroll(20, 30, 200f);
        pane.setOverscroll(false, true);
    }

    public void addItem(String name) {
        temp.add(name);
    }

    public void setItems(Array<String> items) {
        list.setItems(items);
    }

    public void listFont(BitmapFont font, float size) {
        list.getStyle().font = font;
        list.getStyle().font.getData().scale(size);
    }

    public void setVisible(boolean visible) {
        pane.setVisible(visible);
    }

    void vKnobSize(int minHeight, int topHeight) {
        pane.getStyle().vScrollKnob.setMinHeight(minHeight);
        pane.getStyle().vScrollKnob.setTopHeight(topHeight);
    }

    void vScrollSize(int minWidth, int topWidth) {
        pane.getStyle().vScroll.setMinWidth(minWidth);
        pane.getStyle().vScroll.setLeftWidth(topWidth);
    }
}
