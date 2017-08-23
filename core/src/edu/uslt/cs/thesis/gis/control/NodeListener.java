package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.uslt.cs.thesis.gis.algorithm.Node;
import edu.uslt.cs.thesis.gis.map.TiledMapStage;
import edu.uslt.cs.thesis.gis.object.ArrowDirection;

public class NodeListener extends ClickListener {

    private Node node;
    private TiledMapStage mapStage;
    private ArrowDirection arrow;

    public NodeListener(TiledMapStage mapStage, Node node, ArrowDirection arrow) {
        this.node = node;
        this.mapStage = mapStage;
        this.arrow = arrow;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
      //  mapStage.getStage().getActors().removeValue(node, false);
        if (node.isDebugMode()) {
            node.cell.setTile(mapStage.getTile());

        }
    }
}
