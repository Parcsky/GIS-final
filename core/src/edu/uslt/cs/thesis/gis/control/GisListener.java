package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import edu.uslt.cs.thesis.gis.algorithm.AStarPathFinder;
import edu.uslt.cs.thesis.gis.algorithm.DiagonalHeuristic;
import edu.uslt.cs.thesis.gis.algorithm.Path;
import edu.uslt.cs.thesis.gis.algorithm.PathFinder;
import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.BuildingInfoPanel;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.gui.NavigationPanel;
import edu.uslt.cs.thesis.gis.manager.BuildingManager;
import edu.uslt.cs.thesis.gis.map.TileMap;
import edu.uslt.cs.thesis.gis.object.Building;
import edu.uslt.cs.thesis.gis.object.GisObject;
import edu.uslt.cs.thesis.gis.util.Math;

public class GisListener extends ActorGestureListener {

    private BuildingManager buildingManager;
    private PathFinder pathFinder;
    private List<String> list;
    private GisObject marker;
    private TileMap uslMap;

    private BuildingInfoPanel infoPanel;
    private NavigationPanel navigation;

    private int startX;
    private int startY;

    private int goalX;
    private int goalY;
    private Path path;

    private Vector3 tp = new Vector3();

    public GisListener(GIS gis, HUD hud) {
        navigation = hud.getNavigationPanel();
        infoPanel = hud.getBuildingInfoPanel();
        buildingManager = gis.buildingManager;
        marker = gis.getMarker();
        uslMap = gis.getUslMap();
        list = hud.getScrollPanel().list;
        list.addListener(this);
        marker.setPosition(500, 500);

        pathFinder = new AStarPathFinder(gis.getMapStage(), new DiagonalHeuristic());
    }

    @Override
    public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
        startX = (int) (event.getTarget().getX() / 16 + marker.getWidth() / 2 / 16);
        startY = (int) (event.getTarget().getY() / 16 + marker.getHeight() / 2 / 16);

        float dx = x - marker.getWidth() / 2;
        float dy = y - marker.getHeight() / 2;

        if (event.getTarget().equals(marker.getObject())) {
            marker.setPosition(dx, dy);
            path = pathFinder.findPath(startX, startY, goalX, goalY);
            if (path != null) {
                navigation.setTimeAndDistance(path.getDistance(), Math.time(path.getDistance(), 1.4f));
            }
        }

        float posX = uslMap.getCam().position.x += deltaX * .6f;
        float posY = uslMap.getCam().position.y += deltaY * .6f;
        float viewPortHalfX = uslMap.getCam().viewportWidth / 2;
        float viewPortHalfY = uslMap.getCam().viewportHeight / 2;
        posX = MathUtils.clamp(posX, viewPortHalfX, uslMap.getWidth() - viewPortHalfX);
        posY = MathUtils.clamp(posY, viewPortHalfY, uslMap.getHeight() - viewPortHalfY);
        uslMap.getCam().position.lerp(tp.set(posX, posY, 0), 0.1f);
    }

    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {
        if (event.getTarget().equals(list) && list.getSelected() != null) {
            Building building = buildingManager.get(list.getSelected());
            goalX = (int) building.getX() / 16;
            goalY = (int) building.getY() / 16;
            path = pathFinder.findPath(startX, startY, goalX, goalY);
            infoPanel.setContainerInfo(building.getObject(), building.getName(), building.getFloor(), "info");
        }
    }
}


