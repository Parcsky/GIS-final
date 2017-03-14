package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import edu.uslt.cs.thesis.gis.util.FontGenerator;
import edu.uslt.cs.thesis.gis.util.constant.Option;

public class NavigationPanel implements Panel {

    public TextField search;
    public Button menuList;
    public Label distanceLabel;
    public Label timeLabel;

    private Table table;

    public NavigationPanel(Skin skin, int width, int height) {
        if (skin == null) throw new NullPointerException("Skin is null");

        table = new Table(skin);
        table.setBackground("navigation");
        setName(Option.NAVIGATION_OPTION);

        search = new TextField("", skin, "search-little");
        search.setAlignment(Align.center);
        search.getStyle().cursor.setMinHeight(3);
        search.getStyle().cursor.setMinWidth(5);
        search.setBlinkTime(.55f);
        search.getStyle().font = FontGenerator.generate("font/molten.ttf", .05f);
        search.setAlignment(Align.left);

        menuList = new Button(skin, "hide-list");
        menuList.setChecked(false);

        timeLabel = new Label("Time: ", skin, "default");
        distanceLabel = new Label("Distance: ", skin, "default");

        Table distanceTable = new Table();
        distanceTable.add(distanceLabel).minSize(0).prefWidth(width * .3f).padLeft(4);
        distanceTable.add(timeLabel).minSize(0).prefWidth(width * .3f);
        distanceTable.left();


        table.add(menuList).top().left().minSize(5).prefSize(menuList.getPrefWidth(), 30).pad(5);
        table.add(distanceTable).minSize(5).prefSize(width * .5f, height * .2f).fillX().spaceRight(width * .1f);
        table.add(search).top().right().minSize(5).prefSize(width / 3, 40).pad(5);
    }

    public void setTimeAndDistance(float distance, float time) {
        distanceLabel.setText("Distance " + " " + (int) distance + " m");
        String strTime = (int) time + " sec";
        if (time >= 60) {
            time = time / 60;
            strTime = (int) time + " min";
        }
        timeLabel.setText("Time " + " " + strTime);
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
