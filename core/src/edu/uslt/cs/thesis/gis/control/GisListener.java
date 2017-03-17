package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
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
import edu.uslt.cs.thesis.gis.util.Physics;

public class GisListener extends ActorGestureListener {

    private BuildingManager buildingManager;
    private PathFinder pathFinder;
    private List<String> list;
    private GisObject marker;
    private TileMap uslMap;
    private TextField search;

    private BuildingInfoPanel infoPanel;
    private NavigationPanel navigation;

    private int startX;
    private int startY;

    private int goalX;
    private int goalY;
    private Path path;

    private Vector3 tp = new Vector3();
    private float viewPortHalfX;
    private float viewPortHalfY;

    public GisListener(GIS gis, HUD hud) {
        navigation = hud.getNavigationPanel();
        infoPanel = hud.getBuildingInfoPanel();
        buildingManager = gis.buildingManager;
        marker = gis.getMarker();
        search= hud.getNavigationPanel().search;
        uslMap = gis.getUslMap();
        list = hud.getScrollPanel().list;
        list.addListener(this);
        marker.setPosition(816, 1280);

        pathFinder = new AStarPathFinder(gis.getMapStage(), new DiagonalHeuristic());

        gis.getMapStage().getStage().getViewport().update(gis.width, gis.height);
        viewPortHalfX = gis.getMapStage().getCamera().viewportWidth / 2;
        viewPortHalfY = gis.getMapStage().getCamera().viewportHeight / 2;
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

        float posX = uslMap.getCam().position.x += deltaX * .6f;
        float posY = uslMap.getCam().position.y += deltaY * .6f;
        posX = MathUtils.clamp(posX, viewPortHalfX, uslMap.getWidth() - viewPortHalfX);
        posY = MathUtils.clamp(posY, viewPortHalfY, uslMap.getHeight() - viewPortHalfY);
        uslMap.getCam().position.lerp(tp.set(posX, posY, 0), 0.5f);
    }

    @Override
    public void tap(InputEvent event, float x, float y, int count, int button) {

        if (event.getTarget().equals(list)) {
            Building building = buildingManager.get(list.getSelected());
            startX = (int) (marker.getX() / 16 + marker.getWidth() / 2 / 16);
            startY = (int) (marker.getY() / 16 + marker.getHeight() / 2 / 16);
            goalX = (int) building.getX() / 16;
            goalY = (int) building.getY() / 16;
            search.setText(building.getName());
            path = pathFinder.findPath(startX, startY, goalX, goalY);
            if(path == null) return;
            navigation.setTimeAndDistance(path.getDistance(), Physics.time(path.getDistance(), 1.4f));
            infoPanel.setContainerInfo(building.getObject(), building.getName(), building.getFloor(),building.getInfo());
        }
    }
}


