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
import edu.uslt.cs.thesis.gis.gui.panels.MenuPanel;
import edu.uslt.cs.thesis.gis.manager.BuildingManager;
import edu.uslt.cs.thesis.gis.screen.LogInState;

public class HudListener extends ClickListener {

    private BuildingManager buildingManager;
    private ScrollPane scrollPane;
    private List<String> list;
    private TextField search;
    private Button menuList;
    private HUD hud;
    private MenuPanel menuPanel;
    private ImageTextButton btnAdmin;
    private GIS gis;

    public HudListener(GIS gis, HUD hud) {
        this.hud = hud;
        this.gis = gis;
        this.scrollPane = hud.getScrollPanel().getTable();
        this.menuList = hud.getNavigationPanel().menuList;
        this.search = hud.getNavigationPanel().search;
        this.buildingManager = gis.buildingManager;
        this.menuPanel = hud.getMenuPanel();
        this.list = gis.getList();
        this.btnAdmin = hud.getNavigationPanel().btnAdmin;
        list.setItems(gis.buildingManager.getBuildingName());
        scrollPane.setWidget(list);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (event.getTarget().equals(list)) {
            return super.touchDown(event, x, y, pointer, button);
        }
        if (event.getTarget().equals(btnAdmin)) {
            gis.setScreen(new LogInState(gis));
        }
        if (event.getTarget().equals(menuList)) {
            if (!menuList.isChecked()) hud.getMenuPanel().show();
            else if (menuList.isChecked()) hud.getMenuPanel().hide();

        }
        menuController(event);
        return false;
    }

    private boolean menuController(InputEvent event) {
        ImageTextButton imageButton = (ImageTextButton) menuPanel.getChildren(event.getTarget());
        if (event.getTarget().equals(imageButton)) {
            if (event.getTarget().getName().equals("Quit")) Gdx.app.exit();
            if (!imageButton.isChecked()) {
                hud.show(hud.getPanel(imageButton.getName()));
                return false;
            } else if (imageButton.isChecked()) {
                hud.hide(hud.getPanel(imageButton.getName()));
            }
        }
        return false;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (event.getTarget().equals(list)) {
            if (getTapCount() == 2) {
                hud.getScrollPanel().hide();
            }
        }
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
