package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.uslt.cs.thesis.gis.gui.panels.TerrainPanel;
import edu.uslt.cs.thesis.gis.map.GisMap;

public class TerrainOptionListener extends ChangeListener {

    private TerrainPanel terrainPanel;
    private GisMap gisMap;

    public TerrainOptionListener(GisMap gisMap, TerrainPanel terrainPanel) {
        this.terrainPanel = terrainPanel;
        this.gisMap = gisMap;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        CheckBox checkBox = terrainPanel.getChildren(actor.getName());
        if (actor.equals(checkBox)) {
            if (checkBox.isChecked()) {
                gisMap.setLayerVisible(actor.getName(), false);
            } else if (!checkBox.isChecked()) {
                gisMap.setLayerVisible(actor.getName(), true);
            }
        }
    }
}
