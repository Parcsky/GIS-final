package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import edu.uslt.cs.thesis.gis.algorithm.AStarPathFinder;
import edu.uslt.cs.thesis.gis.algorithm.DiagonalHeuristic;
import edu.uslt.cs.thesis.gis.algorithm.PathFinder;
import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.manager.BuildingManager;
import edu.uslt.cs.thesis.gis.map.TileMap;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;
import edu.uslt.cs.thesis.gis.object.Building;
import edu.uslt.cs.thesis.gis.object.GisObject;

public class GisListener extends ActorGestureListener {

    private BuildingManager buildingManager;
    private Container<Image> container;
    private PathFinder pathFinder;
    private List<String> list;
    private GisObject marker;
    private TileMap uslMap;
    private GIS gis;
    private HUD hud;

    private static int startX;
    private static int startY;

    private static int goalX;
    private static int goalY;

    public GisListener(GIS gis, HUD hud) {
        this.gis = gis;
        this.hud = hud;
        buildingManager = gis.buildingManager;
        marker = gis.getMarker();
        uslMap = gis.getUslMap();
        list = hud.getScrollPanel().list;
        // list.addListener(this);
        pathFinder = new AStarPathFinder(gis.getMapStage(), new DiagonalHeuristic());
        marker.setPosition(500, 500);
    }

    @Override
    public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {

        float posX = uslMap.getCam().position.x += deltaX * .5f;
        float posY = uslMap.getCam().position.y += deltaY * .5f;
        float viewPortHalfX = uslMap.getCam().viewportWidth / 2;
        float viewPortHalfY = uslMap.getCam().viewportHeight / 2;

        posX = MathUtils.clamp(posX, viewPortHalfX, uslMap.getWidth() - viewPortHalfX);
        posY = MathUtils.clamp(posY, viewPortHalfY, uslMap.getHeight() - viewPortHalfY);

        uslMap.getCam().position.x = posX;
        uslMap.getCam().position.y = posY;

        if (event.getTarget() == marker.getObject()) {
            float dx = x - marker.getWidth() / 2;
            float dy = y - marker.getHeight() / 2;

            //if (dx > 0 - marker.getWidth() / 2 && dx < mapStage.getMap().getWidth() - marker.getWidth() / 2 && dy > 0 && dy < 900) {
            if (!gis.getUslMap().isValidLocation(startX, startY)) return;
            startX = (int) (event.getTarget().getX() / 16 + marker.getWidth() / 2 / 16);
            startY = (int) (event.getTarget().getY() / 16 + marker.getHeight() / 2 / 16);
            marker.setPosition(dx, dy);
            pathFinder.findPath(startX, startY, goalX, goalY);
        }
    }


    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {

        if (list.getSelected() != null) {
            Building building = buildingManager.get(list.getSelected());
            goalX = (int) building.getX() / 16;
            goalY = (int) building.getY() / 16;
        }

    }

}


