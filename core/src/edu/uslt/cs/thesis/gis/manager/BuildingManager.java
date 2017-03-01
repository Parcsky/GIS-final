package edu.uslt.cs.thesis.gis.manager;


import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

import edu.uslt.cs.thesis.gis.object.Building;

public class BuildingManager {

    private Array<String> buildingName;
    private List<Building> buildings;

    public BuildingManager() {
        buildingName = new Array<String>();
        buildings = new ArrayList<Building>();
    }

    public void add(Building building) {
        buildings.add(building);
    }

    public void clearList() {
        buildingName.clear();
    }


    public int size() {
        return buildings.size();
    }

    public boolean contains(String name, boolean identity) {
        for (int i = 0; i < size(); i++) {
            if (buildings.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                buildingName.add(buildings.get(i).getName());
                if (identity) return true;
            }
        }
        return false;
    }

    public Building get(String name) {
        for (int i = 0; i < buildings.size(); i++) {
            if (buildings.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                return buildings.get(i);
            }
        }
        return null;
    }


    public List<Building> getBuildings() {
        return buildings;
    }

    public Array<String> getBuildingName() {
        return buildingName;
    }
}
