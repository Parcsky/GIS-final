package edu.uslt.cs.thesis.gis.resource;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    private AssetManager assetManager;
    private TextureAtlas buildingAtlas;
    private static Assets instance = new Assets();

    public static Assets instance() {
        return instance;
    }

    private SplashAsset splashAsset;
    public Skin skin;

    private Assets() {
    }

    public void init(AssetManager manager) {
        this.assetManager = manager;
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(TiledMap.class, new TmxMapLoader(resolver));
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter moltenLoader = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        moltenLoader.fontFileName = "font/molten.ttf";

        manager.load("data/hud.json", Skin.class, new SkinLoader.SkinParameter("hud/gis.atlas"));
        manager.load("object/building.pack", TextureAtlas.class);
        manager.load("font/molten.ttf", BitmapFont.class, moltenLoader);
        manager.load("map/usl-map.tmx", TiledMap.class);

        manager.finishLoading();

        buildingAtlas = get("object/building.pack");
        skin = manager.get("data/hud.json");
        splashAsset = new SplashAsset(skin);
    }

    public <T> T get(String path) {
        if (path.length() <= 0) throw new NullPointerException("path is empty");
        return assetManager.get(path);
    }

    public SplashAsset getSplash() {
        return splashAsset;
    }

    public TextureAtlas getBuildingAtlas() {
        return buildingAtlas;
    }

    public void dispose() {
        assetManager.dispose();
        skin.dispose();
    }

}
