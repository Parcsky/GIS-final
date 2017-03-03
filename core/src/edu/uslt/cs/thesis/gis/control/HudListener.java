package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.manager.BuildingManager;

public class HudListener extends InputListener {

    private BuildingManager buildingManager;
    private ScrollPane scrollPane;
    private ImageButton hidePanel;
    private List<String> list;
    private TextField search;
    private Button hideBtn;
    private HUD hud;


    public HudListener(GIS gis, HUD hud) {
        this.hideBtn = hud.getNavigationBar().hideList;
        this.scrollPane = hud.getScrollPanel().pane;
        this.search = hud.getNavigationBar().search;
        this.buildingManager = gis.buildingManager;
        this.list = hud.getScrollPanel().list;
        this.hidePanel = hud.getHidePanel();
        this.hud = hud;

        hidePanel.addListener(this);
    }

    @Override
    public boolean handle(Event e) {
        if (e.getTarget() == hidePanel) {
            if (hidePanel.isChecked()) {
                hud.hide(hud.getWindowPanel());
            } else if (!hidePanel.isChecked()){
                hud.show(hud.getWindowPanel());
            }
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
        if (event.getTarget() == hideBtn) {
            return true;
        }
        return false;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        if (event.getTarget() == hideBtn) {
            System.out.println("TRUE");
            if (hideBtn.isChecked()) {
                hud.hide(scrollPane);
            } else if (!hideBtn.isChecked()) {
                hud.show(scrollPane);
            }
        }
    }
}
