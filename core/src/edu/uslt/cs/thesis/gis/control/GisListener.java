package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import edu.uslt.cs.thesis.gis.algorithm.AStarPathFinder;
import edu.uslt.cs.thesis.gis.algorithm.DiagonalHeuristic;
import edu.uslt.cs.thesis.gis.algorithm.Path;
import edu.uslt.cs.thesis.gis.algorithm.PathFinder;
import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.gui.panels.BuildingInfoPanel;
import edu.uslt.cs.thesis.gis.gui.panels.NavigationPanel;
import edu.uslt.cs.thesis.gis.manager.BuildingManager;
import edu.uslt.cs.thesis.gis.map.GisMap;
import edu.uslt.cs.thesis.gis.map.TileMap;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;
import edu.uslt.cs.thesis.gis.object.Building;
import edu.uslt.cs.thesis.gis.object.LocationMarker;
import edu.uslt.cs.thesis.gis.util.Physics;

public class GisListener extends ActorGestureListener {

    private BuildingManager buildingManager;
    private PathFinder pathFinder;
    private List<String> list;
    private TileMap gisMap;
    private HUD hud;
    private TextField search;

    private BuildingInfoPanel infoPanel;
    private NavigationPanel navigation;

    private int startX;
    private int startY;

    private int goalX;
    private int goalY;
    private Path path;

    private LocationMarker marker;

    public GisListener(GIS gis, TiledMapStage mapStage, GisMap gisMap, HUD hud) {
        this.gisMap = gisMap;
        this.marker = gis.marker;
        navigation = hud.getNavigationPanel();
        infoPanel = hud.getBuildingInfoPanel();
        buildingManager = gis.buildingManager;
        search = hud.getNavigationPanel().search;
        list = gis.getList();
        list.addListener(this);

        pathFinder = new AStarPathFinder(mapStage, new DiagonalHeuristic());

        mapStage.getStage().getViewport().update(gis.width, gis.height);
    }


    @Override
    public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
        startX = (int) (marker.getX() / 16 + marker.getWidth() / 2 / 16);
        startY = (int) (marker.getY() / 16 + marker.getHeight() / 2 / 16);
        float dx = x - marker.getWidth() / 2;
        float dy = y - marker.getHeight() / 2;

        if (event.getTarget().equals(marker.getObject())) {
            marker.setPosition(dx, dy);
            path = pathFinder.findPath(startX, startY, goalX, goalY);
            if (path != null) {
                navigation.setTimeAndDistance(path.getDistance(), Physics.time(path.getDistance(), 1.4f));
            }
        }

    }

    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {
        if (event.getTarget().equals(list)) {
            Building building = buildingManager.get(list.getSelected());
            startX = (int) (marker.getX() / 16 + marker.getWidth() / 2 / 16);
            startY = (int) (marker.getY() / 16 + marker.getHeight() / 2 / 16);

            if(building == null) return;
            goalX = building.getX() / 16;
            goalY = building.getY() / 16;
            search.setText(building.getName());
            path = pathFinder.findPath(startX, startY, goalX, goalY);
            if (path == null) return;
            navigation.setTimeAndDistance(path.getDistance(), Physics.time(path.getDistance(), 1.4f));
            infoPanel.setContainerInfo(null, building.getName(), building.getFloor(), building.getInfo());
        }
    }
}


