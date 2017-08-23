package edu.uslt.cs.thesis.gis.gui.panels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

public class TileSetPanel {


    public Array<ImageButton> array = new Array<ImageButton>();
    public Table mainTable;

    private String[][] tileName = {
            {"blank", "path-1", "path-2", "path-3",
                    "path-4", "grass", "path-5", "path-6", "road"},
            {"trees",}};

    private int width;
    private int height;
    private Table table;

    public TileSetPanel(TiledMapTileSets tileSet, int width, int height) {
        this.width = width;
        this.height = height;
        table = new Table();
        table.top().left();

        setPanelData(tileSet.getTileSet("map"), "map/map.png", 18, 0, 18, 18);
        setPanelData(tileSet.getTileSet("trees"), "map/trees.png", 32, 0, 32, 32);

        mainTable = new Table();
        mainTable.add(table);
        mainTable.top().left();
    }

    private void setPanelData(TiledMapTileSet tileSet, String imageName, int x, int y, int tileWidth, int tileHeight) {
        for (int i = 0; i < tileSet.size(); i++) {
            TextureRegion region = new TextureRegion(new Texture(imageName), i * x, y, tileWidth, tileHeight);
            TextureRegionDrawable regionDrawable = new TextureRegionDrawable();
            regionDrawable.setRegion(region);

            ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
            style.pressedOffsetX = -2;
            style.pressedOffsetY = -2;
            style.up = regionDrawable;

            ImageButton imageButton = new ImageButton(style);
            imageButton.setName(tileName[0][i]);
            array.add(imageButton);
            table.add(imageButton).minSize(0).prefSize(width * .05f, height * .05f).pad(2);

            if (i % 2 == 0) table.row();
        }

    }

    public Table getMainTable() {
        return mainTable;
    }
}
