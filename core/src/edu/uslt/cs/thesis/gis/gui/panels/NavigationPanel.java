package edu.uslt.cs.thesis.gis.gui.panels;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import edu.uslt.cs.thesis.gis.gui.Panel;
import edu.uslt.cs.thesis.gis.util.constant.Option;

public class NavigationPanel implements Panel {

    public TextField search;
    public Button menuList;
    public Label distanceLabel;
    public Label timeLabel;
    public ImageTextButton btnAdmin;
    private Table table;

    public NavigationPanel(Skin skin, int width, int height) {
        if (skin == null) throw new NullPointerException("Skin is null");

        search = new TextField("", skin, "search-little");
        search.setAlignment(Align.center);
        search.getStyle().cursor.setMinHeight(3);
        search.getStyle().cursor.setMinWidth(4);
        search.setBlinkTime(.55f);
        search.setAlignment(Align.left);

        btnAdmin = new ImageTextButton("Login admin", skin, "default");
        btnAdmin.getLabel().setAlignment(Align.right);

        menuList = new Button(skin, "hide-list");
        menuList.setChecked(false);

        timeLabel = new Label("Time: ", skin, "default");
        distanceLabel = new Label("Distance: ", skin, "default");

        Table distanceTable = new Table();
        distanceTable.add(distanceLabel).minSize(0).prefWidth(width * .3f).padLeft(4);
        distanceTable.add(timeLabel).minSize(0).prefWidth(width * .3f);
        distanceTable.add(btnAdmin).minSize(0).prefSize(width * .4f, height * .05f);
        distanceTable.left();

        table = new Table(skin);
        table.setBackground("navigation");
        table.add(menuList).top().left().minSize(5).prefSize(menuList.getPrefWidth(), 30).pad(5);
        table.add(distanceTable).minSize(5).prefSize(width * .5f, height * .2f).fillX().spaceRight(width * .1f);
        table.add(search).top().right().minSize(5).prefSize(width / 3, 40);
        table.pad(3);
        setName(Option.NAVIGATION_OPTION);

    }

    public void setTimeAndDistance(float distance, float time) {
        distanceLabel.setText("Distance " + " " + (int) distance + " m");
        String strTime = (int) time + " sec";
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
