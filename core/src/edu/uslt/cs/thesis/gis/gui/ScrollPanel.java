package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class ScrollPanel implements Panel {

    public List<String> list;

    private Array<String> temp;
    private ScrollPane pane;

    ScrollPanel(Skin skin, String style) {
        if (skin == null) throw new NullPointerException("Scroll panel skin is null");
        temp = new Array<String>();
        list = new List<String>(skin, style);

        pane = new ScrollPane(list, skin, style);
        pane.setFlickScroll(true);
        pane.setupOverscroll(20, 30, 200f);
        pane.setOverscroll(false, true);
        pane.setVisible(false);
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

    @Override
    public void hide() {
        pane.setVisible(false);
    }

    @Override
    public void show() {
        pane.setVisible(true);
    }

    public ScrollPane getTable() {
        return pane;
    }

    @Override
    public String getName() {
        return pane.getName();
    }

    @Override
    public void setName(String name) {
        pane.setName(name);
    }



}
