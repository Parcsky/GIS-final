package edu.uslt.cs.thesis.gis.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import edu.uslt.cs.thesis.gis.core.Controller;
import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.HUD;
import edu.uslt.cs.thesis.gis.gui.SearchPanel;
import edu.uslt.cs.thesis.gis.map.SaintLouisMap;
import edu.uslt.cs.thesis.gis.map.TileMap;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;
import edu.uslt.cs.thesis.gis.object.LocationMarker;
import edu.uslt.cs.thesis.gis.resource.Assets;

public class MainState extends State {

    private Controller controller;
    private TileMap uslMap;
    private TiledMapStage mapStage;
    private HUD hud;

    public MainState(GIS gis) {
        super(gis);
        Skin hudSkin = Assets.instance().skin;

        uslMap = new SaintLouisMap("map/usl-map-final.tmx");
        uslMap.setCamView(new OrthographicCamera(), uslMap.getWidth(), uslMap.getHeight());

        LocationMarker marker = new LocationMarker(hudSkin, "location-marker");
        marker.setOrigin(marker.getWidth() / 2, marker.getHeight() / 2);
        marker.setBounds(uslMap.getWidth() / 2 - 55, uslMap.getHeight() / 2 - 55, 50, 50);
        marker.setPosition(55, uslMap.getHeight() / 2);

        mapStage = new TiledMapStage(uslMap);
        mapStage.createNodes(uslMap.getTileWidth(), uslMap.getTileHeight(), 16, 16);
        mapStage.setViewport(new StretchViewport(uslMap.getWidth(), uslMap.getHeight(), uslMap.getCam()));
        mapStage.addActor(marker.getObject());

        SearchPanel searchPanel = new SearchPanel(hudSkin, gis.width, gis.height);

        hud = new HUD(gis.width, gis.height);
        hud.addGroup(searchPanel, 20);
        hud.display();

        controller = new Controller(gis, marker, hud, mapStage);
        mapStage.addListener(controller);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hud.getStage());
        inputMultiplexer.addProcessor(mapStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void update() {
        controller.update();
        hud.act();
        mapStage.act();
    }

    @Override
    public void render() {
        uslMap.render();

        mapStage.getViewport().apply();
        mapStage.draw();

        hud.getStage().getViewport().apply();
        hud.draw();
    }

    @Override
    public void resize(int width, int height) {
        hud.resize(width, height);
        mapStage.resize(width, height);
    }

    @Override
    public void dispose() {
        hud.dispose();
        uslMap.dispose();
        mapStage.dispose();
    }
}


