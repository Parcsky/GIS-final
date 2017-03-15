package edu.uslt.cs.thesis.gis.algorithm;


import java.util.ArrayList;
import java.util.List;

import edu.uslt.cs.thesis.gis.map.TileMap;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;
import edu.uslt.cs.thesis.gis.util.constant.Direction;

public class AStarPathFinder implements PathFinder {

    private List<Node> open = new ArrayList<Node>();
    private List<Node> close = new ArrayList<Node>();
    private Node[][] nodes;

    private TileMap map;
    private Heuristic heuristic;

    public AStarPathFinder(TiledMapStage mapStage, Heuristic heuristic) {
        this.heuristic = heuristic;
        nodes = mapStage.getNodes();
        map = mapStage.getMap();
    }

    @Override
    public Path findPath(int startX, int startY, int goalX, int goalY) {
        clearNodes();
        if (startX < 0 || startX > nodes.length || startY < 0 || startY > nodes[1].length)
            return null;
        Node current = nodes[startX][startY];
        Node goal = nodes[goalX][goalY];
        open.add(current);
        while (!open.isEmpty()) {

            current = getLowestFcost(open);
            open.remove(current);
            close.add(current);

            if (current == goal) {
                float distance = 0;
                Path path = new Path();

                while (current != null) {
                    distance += 2;
                    path.add(current);
                    if (current.arrow != null) current.arrow.setVisible(true);
                    current = current.parent;

                }
                path.setDistance(distance);
                return path;
            }
            // neighbors of current
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    int dx = current.x + x;
                    int dy = current.y + y;

                    if (map.isValidLocation(dx, dy)) {
                        if (!map.isWalkable(nodes[dx][dy], x, y) || close.contains(nodes[dx][dy]))
                            continue;

                        float newScore = movementCost(current.g, isDiagonal(x, y));

                        if (!open.contains(nodes[dx][dy])) {
                            open.add(nodes[dx][dy]);
                        } else if (newScore >= nodes[dx][dy].g) continue;

                        if (open.contains(nodes[dx][dy]) || newScore < nodes[dx][dy].g) {
                            setDirection(nodes[dx][dy], x, y);
                            nodes[dx][dy].g = newScore;
                            nodes[dx][dy].h = heuristic.estimate(nodes[dx][dy], goal);
                            nodes[dx][dy].f = nodes[dx][dy].g + nodes[dx][dy].h;
                            nodes[dx][dy].parent = current;
                        }
                    }
                }
            }
        }
        return null;
    }

    private void setDirection(Node node, int x, int y) {
        for (Direction dir : Direction.values()) {
            if (dir.offsetX == x && dir.offsetY == y) {
                node.arrow.setImage(dir.drawableValue);
                node.arrow.setVisible(false);
            }
        }
    }

    private Node getLowestFcost(List<Node> open) {
        Node lowestNode = open.get(0);
        for (int i = 0; i < open.size(); i++) {
            if (open.get(i).f <= lowestNode.f && open.get(i).h <= lowestNode.h) {
                lowestNode = open.get(i);
            }
        }
        return lowestNode;
    }

    private boolean isDiagonal(int x, int y) {
        return (x == -1 && y == 1 || x == 1 && y == 1 || x == 1 && y == -1 || x == -1 && y == -1);
    }

    private float movementCost(float cost, boolean diagonal) {
        return diagonal ? cost + 14 : cost + 10;
    }


    @Override
    public void clearNodes() {
        for (int i = 0; i < map.getTileWidth(); i++) {
            for (int j = 0; j < map.getTileHeight(); j++) {
                if (nodes[i][j].cell != null) {
                    nodes[i][j].f = 0;
                    nodes[i][j].h = 0;
                    nodes[i][j].g = 0;
                    nodes[i][j].arrow.setVisible(false);
                    nodes[i][j].parent = null;
                }
            }
        }
        close.clear();
        open.clear();
    }


}