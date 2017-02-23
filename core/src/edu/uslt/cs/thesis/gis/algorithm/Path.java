package edu.uslt.cs.thesis.gis.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Path {

    private List<Node> steps = new ArrayList<Node>();

    public void add(Node node) {
        steps.add(node);
    }

    public int size() {
        return steps.size();
    }
    public Node getNode(int index) {
        return steps.get(index);
    }

    public boolean contain(Node node) {
        return steps.contains(node);
    }
}
