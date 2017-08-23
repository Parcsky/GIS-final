package edu.uslt.cs.thesis.gis.gui.panels;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;

import edu.uslt.cs.thesis.gis.control.TerrainOptionListener;
import edu.uslt.cs.thesis.gis.util.constant.Option;

public class TerrainPanel implements edu.uslt.cs.thesis.gis.gui.Panel {

    private Table table;
    public CheckBox building;
    public CheckBox trees;
    public CheckBox road;
    public CheckBox path;
    private Array<CheckBox> checkBoxList = new Array<CheckBox>();

    public TerrainPanel(Skin skin, int width, int height) {
        String[] listName = {"building", "trees", "road", "path"};

        table = new Table(skin);
        table.setBackground("container");
        table.add(new Label("Terrain Option", skin, "default")).row();

        for (String aListName : listName) {
            CheckBox checkbox = new CheckBox(aListName, skin, "default");
            checkbox.setName(aListName);
            checkbox.getImageCell().minSize(0).prefSize(width * .2f, height * .5f);
            checkbox.getImage().setScaling(Scaling.fit);
            checkbox.getImage().setAlign(Align.left);
            checkbox.getImageCell().space(10);
            checkBoxList.add(checkbox);
            table.add(checkbox).minSize(0).prefSize(width * .22f, height * .05f).pad(2).left().row();
        }
        setName(Option.TERRAIN_OPTION);
        table.bottom().center();
        table.setVisible(false);
        table.pad(5);
    }

    public CheckBox getChildren(String name) {
        for (int i = 0; i < size(); i++) {
            if (checkBoxList.get(i).getName().equals(name)) {
                return checkBoxList.get(i);
            }
        }
        return null;
    }

    @Override
    public void setName(String name) {
        table.setName(name);
    }

    @Override
    public String getName() {
        return table.getName();
    }

    public int size() {
        return checkBoxList.size;
    }

    public Table getTable() {
        return table;
    }

    @Override
    public void hide() {
        table.setVisible(false);
    }

    @Override
    public void show() {
        table.setVisible(true);
    }

    public void addListener(TerrainOptionListener terrainListener) {
        for (int i = 0; i < table.getChildren().size; i++) {
            table.getChildren().get(i).addCaptureListener(terrainListener);
        }
    }
}
