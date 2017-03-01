package edu.uslt.cs.thesis.gis.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.List;

import edu.uslt.cs.thesis.gis.object.Building;

public class JsonObjectBuilder {

    private JsonValue json;
    private JsonValue classJson;

    public JsonObjectBuilder(String path) {
        FileHandle fileHandle = Gdx.files.internal(path);
        String text = fileHandle.readString();
        json = new JsonReader().parse(text);
    }

    public void build(List<Building> list, String className) {
        classJson = json.get(className);
        for (JsonValue objectTemp : classJson.iterator()) {
            String room = objectTemp.getString("room");
            String imagePath = objectTemp.getString("visual");
            int floor = objectTemp.getInt("floor");
            int posX = objectTemp.getInt("X");
            int posY = objectTemp.getInt("Y");
            Building building = new Building(room, floor, posX, posY);
            building.setImage(imagePath);
            list.add(building);
        }
    }

}
