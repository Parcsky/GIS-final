package edu.uslt.cs.thesis.gis.gui.panels;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import edu.uslt.cs.thesis.gis.gui.Panel;
import edu.uslt.cs.thesis.gis.util.constant.Option;

public class ScrollPanel implements Panel {

    private Array<String> temp;
    private ScrollPane pane;

    public ScrollPanel(Skin skin, String style) {
        if (skin == null) throw new NullPointerException("Scroll panel skin is null");
        temp = new Array<String>();

        pane = new ScrollPane(null,skin, style);
        pane.setFlickScroll(true);
        pane.setupOverscroll(20, 30, 200f);
        pane.setOverscroll(false, true);
        pane.setVisible(false);
        setName(Option.SCROLL_OPTION);
    }

    public void addItem(String name) {
        temp.add(name);
    }

    public void setVisible(boolean visible) {
        pane.setVisible(visible);
    }

    public void vKnobSize(int minHeight, int topHeight) {
        pane.getStyle().vScrollKnob.setMinHeight(minHeight);
        pane.getStyle().vScrollKnob.setTopHeight(topHeight);
    }

    public void vScrollSize(int minWidth, int topWidth) {
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
