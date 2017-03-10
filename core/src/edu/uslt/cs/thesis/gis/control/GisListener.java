package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import edu.uslt.cs.thesis.gis.algorithm.AStarPathFinder;
import edu.uslt.cs.thesis.gis.algorithm.DiagonalHeuristic;
import edu.uslt.cs.thesis.gis.algorithm.PathFinder;
import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.BuildingInfoPanel;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.manager.BuildingManager;
import edu.uslt.cs.thesis.gis.map.TileMap;
import edu.uslt.cs.thesis.gis.object.Building;
import edu.uslt.cs.thesis.gis.object.GisObject;

public class GisListener extends ActorGestureListener {

    private BuildingManager buildingManager;
    private PathFinder pathFinder;
    private List<String> list;
    private GisObject marker;
    private TileMap uslMap;
    private GIS gis;

    private BuildingInfoPanel infoPanel;

    private int startX;
    private int startY;

    private int goalX;
    private int goalY;

    private Vector3 tp = new Vector3();

    public GisListener(GIS gis, HUD hud) {
        this.gis = gis;
        infoPanel = hud.getBuildingInfoPanel();
        buildingManager = gis.buildingManager;
        list = hud.getScrollPanel().list;
        marker = gis.getMarker();
        uslMap = gis.getUslMap();
        list.addListener(this);
        marker.setPosition(500, 500);

        pathFinder = new AStarPathFinder(gis.getMapStage(), new DiagonalHeuristic());
    }

    @Override
    public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
        float posX = uslMap.getCam().position.x += deltaX * .6f;
        float posY = uslMap.getCam().position.y += deltaY * .6f;
        float viewPortHalfX = uslMap.getCam().viewportWidth / 2;
        float viewPortHalfY = uslMap.getCam().viewportHeight / 2;

        posX = MathUtils.clamp(posX, viewPortHalfX, uslMap.getWidth() - viewPortHalfX);
        posY = MathUtils.clamp(posY, viewPortHalfY, uslMap.getHeight() - viewPortHalfY);

        uslMap.getCam().position.lerp(tp.set(posX, posY, 0), 0.1f);

        if (event.getTarget().equals(marker.getObject())) {
            float dx = x - marker.getWidth() / 2;
            float dy = y - marker.getHeight() / 2;
            if (!gis.getUslMap().isValidLocation(startX, startY)) return;
            startX = (int) (event.getTarget().getX() / 16 + marker.getWidth() / 2 / 16);
            startY = (int) (event.getTarget().getY() / 16 + marker.getHeight() / 2 / 16);
            marker.setPosition(dx, dy);
            pathFinder.findPath(startX, startY, goalX, goalY);
        }
    }

    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {
        System.out.println("X: " + (int) x);
        System.out.println("Y: " + (int) y);
        if (event.getTarget().equals(list)) {
            Building building = buildingManager.get(list.getSelected());
            goalX = (int) building.getX() / 16;
            goalY = (int) building.getY() / 16;
            infoPanel.setContainerInfo(building.getObject(), building.getName(), building.getFloor(), "info");
        }
    }
}


