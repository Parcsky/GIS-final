package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.EditorHud;
import edu.uslt.cs.thesis.gis.gui.panels.MenuPanel;
import edu.uslt.cs.thesis.gis.map.GisMap;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;
import edu.uslt.cs.thesis.gis.object.Building;
import edu.uslt.cs.thesis.gis.screen.MainState;


public class EditorListener extends ClickListener {


    private Array<ImageButton> array;
    private GisMap gisMap;
    private TiledMapStage mapStage;

    public ImageTextButton btnNewMap;
    public ImageTextButton btnAddBuilding;
    public TextField txtTileWidth;
    public TextField txtTileHeight;
    private MenuPanel menuPanel;
    private Button btnMenu;
    private GIS GIS;
    private EditorHud hud;

    public EditorListener(GIS GIS, GisMap gisMap, EditorHud hud, TiledMapStage mapStage) {
        this.GIS = GIS;
        this.hud = hud;
        this.gisMap = gisMap;
        this.mapStage = mapStage;
        this.txtTileHeight = hud.getEditorNav().txtTileHeight;
        this.txtTileWidth = hud.getEditorNav().txtTileWidth;
        this.btnNewMap = hud.getEditorNav().btnNewMap;
        this.array = hud.getTileSetPanel().array;
        this.menuPanel = hud.getMenuPanel();
        this.btnMenu = hud.getEditorNav().btnMenu;
        this.btnAddBuilding = hud.getEditorNav().btnSave;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (event.getTarget().equals(btnAddBuilding)) {
            String room = hud.getBuildingPanel().txtRoom.getText();
            String floor = hud.getBuildingPanel().txtFloor.getText();
            String desc = hud.getBuildingPanel().txtDesc.getText();


            int posX = Integer.parseInt(hud.getBuildingPanel().txtPosX.getText());
            int posY = Integer.parseInt(hud.getBuildingPanel().txtPosY.getText());
            Building building = new Building();
            building.setRoom(room);
            building.setFloor(floor);
            building.setInfo(desc);
            building.setX(posX);
            building.setY(posY);
            GIS.jsonBuilder.write(building);
            Building newBuilding = (Building) GIS.jsonBuilder.read(building);
            GIS.buildingManager.getBuildingName().add(newBuilding.getName());
            GIS.buildingManager.add(newBuilding);
        }

        if (event.getTarget().equals(btnMenu)) {
            if (!btnMenu.isChecked()) menuPanel.show();
            else if (btnMenu.isChecked()) menuPanel.hide();

            return false;
        }
        for (int i = 0; i < menuPanel.size(); i++) {
            if (event.getTarget().equals(menuPanel.getChildren(i)) && menuPanel.getChildren(i).getName().equals("User Log In")) {
                mapStage.debug(false);
                GIS.setScreen(new MainState(GIS));
                return false;
            }
            if (event.getTarget().equals(menuPanel.getChildren(i)) && menuPanel.getChildren(i).getName().equals("Debug")) {
                ImageTextButton btn = (ImageTextButton) menuPanel.getChildren(i);
                if (btn.isChecked()) {
                    mapStage.debug(true);
                }
                if (!btn.isChecked()) {
                    mapStage.debug(false);
                }
                return false;
            }
        }
        if (event.getTarget().equals(btnNewMap)) {
            if (txtTileWidth.getText().equals("") || txtTileHeight.getText().equals(""))
                return false;
            int tileWidth = Integer.parseInt(txtTileWidth.getText());
            int tileHeight = Integer.parseInt(txtTileHeight.getText());
            mapStage.clearMap();
            mapStage.createNodes(tileWidth, tileHeight, 16, 16);
            return false;
        }
        for (int i = 0; i < array.size; i++) {
            if (event.getTarget().equals(array.get(i))) {
                mapStage.setTile(i);
                return false;
            }
        }
        return false;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        super.touchUp(event, x, y, pointer, button);
    }
}
