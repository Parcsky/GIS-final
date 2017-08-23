package edu.uslt.cs.thesis.gis.object;

public class Building {

    // private Image buildingImage;
    private String room;
    private String floor;
    private String info;
    private int x;
    private int y;

//
//    @Override
//    public void setImage(String imageName) {
//        TextureAtlas atlas = Assets.instance().getBuildingAtlas();
//        TextureRegion region = atlas.findRegion(imageName);
//        buildingImage = new Image(region);
//    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getFloor() {
        return floor;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return room;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
