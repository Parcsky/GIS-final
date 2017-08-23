package edu.uslt.cs.thesis.gis.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import edu.uslt.cs.thesis.gis.object.Building;

public class JsonObjectBuilder {

    private JsonValue jsonValue;
    private JsonValue classJson;
    private FileHandle fileHandle;
    private Json json;

    public JsonObjectBuilder(String path) {
        fileHandle = Gdx.files.local(path);
        String text = fileHandle.readString();
        jsonValue = new JsonReader().parse(text);
        json = new Json();

    }

    public Object read(Building building) {
        Json json = new Json();
        String text = json.toJson(building);
        Building newBuilding = json.fromJson(Building.class, text);
        System.out.println(newBuilding.getName());
        return newBuilding;
    }
    // list.add(building);

//            String room = objectTemp.getString("room");
//            String floor = objectTemp.getString("floor");
//            String info = objectTemp.getString("info");
//            int posX = objectTemp.getInt("x");
//            int posY = objectTemp.getInt("y");
//
//        Building building = new Building();
//        building.setRoom(room);
//        building.setFloor(floor);
//        building.setInfo(info);
//        building.setX(posX);
//        building.setY(posY);

    public void write(Building building) {
        json.addClassTag("Building", Building.class);
        json.setUsePrototypes(false);
        json.setIgnoreUnknownFields(true);
        fileHandle.writeString(json.prettyPrint(building), true);
        System.out.println(json.prettyPrint(building));
    }

}
