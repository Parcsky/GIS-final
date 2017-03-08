package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.gui.MenuPanel;
import edu.uslt.cs.thesis.gis.manager.BuildingManager;

public class HudListener extends ClickListener {

    private BuildingManager buildingManager;
    private ScrollPane scrollPane;
    private List<String> list;
    private TextField search;
    private Button menuList;
    private HUD hud;
    private MenuPanel menuPanel;
    private final int TAPCOUNT_MAX = 3;

    public HudListener(GIS gis, HUD hud) {
        this.hud = hud;
        this.scrollPane = hud.getScrollPanel().getTable();
        this.menuList = hud.getNavigationPanel().menuList;
        this.search = hud.getNavigationPanel().search;
        this.buildingManager = gis.buildingManager;
        this.menuPanel = hud.getMenuPanel();
        this.list = hud.getScrollPanel().list;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (event.getTarget().equals(list)) {
            if (getTapCount() == TAPCOUNT_MAX) {
                scrollPane.setVisible(false);
            }
            return true;
        }
        System.out.println(getTapCount());
        if (event.getTarget().equals(menuList)) {
            if (!menuList.isChecked()) hud.getMenuPanel().show();
            else if (menuList.isChecked()) hud.getMenuPanel().hide();
            return true;
        }
        menuController(event);
        return false;
    }

    private boolean menuController(InputEvent event) {
        ImageTextButton imageButton = (ImageTextButton) menuPanel.getChildren(event.getTarget().getName());
        if (event.getTarget().equals(imageButton)) {
            if (event.getTarget().getName().equals("Quit")) Gdx.app.exit();
            if (!imageButton.isChecked()) {
                hud.show(hud.getPanel(imageButton.getName()));
                return true;
            } else if (imageButton.isChecked()) {
                hud.hide(hud.getPanel(imageButton.getName()));
            }
        }
        return false;
    }

    @Override
    public boolean keyTyped(InputEvent event, char character) {
        buildingManager.clearList();
        if (event.getTarget().equals(search)) {
            buildingManager.contains(search.getText(), false);
            scrollPane.setVisible(true);
        }
        list.setItems(buildingManager.getBuildingName());
        return false;
    }
}
