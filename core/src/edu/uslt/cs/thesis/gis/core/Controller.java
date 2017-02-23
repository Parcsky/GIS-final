package edu.uslt.cs.thesis.gis.core;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import edu.uslt.cs.thesis.gis.algorithm.AStarPathFinder;
import edu.uslt.cs.thesis.gis.algorithm.DiagonalHeuristic;
import edu.uslt.cs.thesis.gis.algorithm.PathFinder;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.gui.ScrollPanel;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;
import edu.uslt.cs.thesis.gis.object.LocationMarker;

public class Controller extends ClickListener {

    private GIS gis;
    private LocationMarker marker;
    private TextField search;
    private List<String> list;
    private Array<String> tempName;
    private PathFinder pathFinder;
    private Button button;
    private ScrollPanel scrollPane;
    private static int startX;
    private static int startY;
    private static int goalX;
    private static int goalY;

    public Controller(GIS gis, LocationMarker marker, HUD hud, TiledMapStage mapStage) {
        this.gis = gis;
        this.marker = marker;
        this.search = hud.searchPanel.search;
        this.list = hud.searchPanel.scrollPanel.list;
        this.button = hud.searchPanel.button;
        this.scrollPane = hud.searchPanel.scrollPanel;

        tempName = new Array<String>();
        pathFinder = new AStarPathFinder(mapStage, new DiagonalHeuristic());

    }

    public void update() {
        textFieldUpdate();
        if (button.isChecked()) {
            scrollPane.setVisible(true);
            search.setVisible(true);
        } else {
            scrollPane.setVisible(false);
            search.setVisible(false);
        }
        marker.setPosition(goalX * 16, goalY * 16);
    }


    private void textFieldUpdate() {
        if (!search.getText().equals("")) {
            tempName.clear();
            for (int i = 0; i < gis.buildings.size(); i++) {
                if (gis.buildings.get(i).getName().toLowerCase().contains(search.getText().toLowerCase())) {
                    tempName.add(gis.buildings.get(i).getName());
                }
            }
        }
        list.setItems(tempName);
        if (list.getSelected() != null) {
            for (int j = 0; j < gis.buildings.size(); j++) {
                if (gis.buildings.get(j).getName().toLowerCase().contains(list.getSelected().toLowerCase())) {
                    goalX = gis.buildings.get(j).x / 16;
                    goalY = gis.buildings.get(j).y / 16;
                }
            }
        }

        if (goalY > 0 && goalX > 0) pathFinder.findPath(startX, startY, goalX, goalY);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        startX = (int) event.getTarget().getX() / 16;
        startY = (int) event.getTarget().getY() / 16;



    }


}
