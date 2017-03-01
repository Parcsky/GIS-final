package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.manager.BuildingManager;

public class HudListener extends InputListener {

    private BuildingManager buildingManager;
    private ScrollPane scrollPane;
    private List<String> list;
    private TextField search;
    private Button hideBtn;
    private HUD hud;


    public HudListener(GIS gis, HUD hud) {
        this.hud = hud;
        this.buildingManager = gis.buildingManager;
        this.search = hud.getNavigationBar().search;
        this.hideBtn = hud.getNavigationBar().button;
        this.scrollPane = hud.getScrollPanel().pane;
        this.list = hud.getScrollPanel().list;
    }

    @Override
    public boolean scrolled(InputEvent event, float x, float y, int amount) {
        if (event.getTarget() == list) {
            System.out.println("YES");
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(InputEvent event, char character) {
        buildingManager.clearList();
        if (event.getTarget().equals(search)) {
            buildingManager.contains(search.getText(), false);
            hud.show(scrollPane);
        }
        list.setItems(buildingManager.getBuildingName());
        return false;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return event.getTarget() == hideBtn;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (event.getTarget() == hideBtn) {
            if (hideBtn.isChecked()) {
                hud.hide(scrollPane);
            } else if (!hideBtn.isChecked()) {
                hud.show(scrollPane);
            }
        }
    }
}
