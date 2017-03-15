package edu.uslt.cs.thesis.gis.util;

public class Physics {


    public static float computeSpeed(float distance, float time) {

        return distance / time;
    }
    public static float time(float distance, float speed) {
        return distance / speed;
    }

    public static float distance(float time, float speed) {
        return time * speed;
    }

}
