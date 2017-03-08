package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.TerrainPanel;

public class TerrainOptionListener extends ChangeListener {

    private TerrainPanel terrainPanel;
    private GIS GIS;

    public TerrainOptionListener(GIS GIS, TerrainPanel terrainPanel) {
        this.terrainPanel = terrainPanel;
        this.GIS = GIS;
    }

    @Override
    public void changed(ChangeEvent event, Actor actor) {
        CheckBox checkBox = terrainPanel.getChildren(actor.getName());
        if (actor.equals(checkBox)) {
            if (checkBox.isChecked()) {
                GIS.uslMap.setLayerVisible(actor.getName(), false);
            } else if (!checkBox.isChecked()) {
                GIS.uslMap.setLayerVisible(actor.getName(), true);
            }
        }
    }
}
