package edu.uslt.cs.thesis.gis.algorithm;


public class DiagonalHeuristic implements Heuristic {
//
    @Override
    public float estimate(Node current, Node goal) {
        int dx = Math.abs(current.x - goal.x);
        int dy = Math.abs(current.y - goal.y);
        return 10 * (dx + dy) + (14 - 2 * 10) * Math.min(dx, dy);
    }


}
