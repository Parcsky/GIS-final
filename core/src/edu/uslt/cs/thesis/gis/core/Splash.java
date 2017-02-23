package edu.uslt.cs.thesis.gis.core;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;

public class Splash{
    private Image image;
    private boolean runnable;
    private int x;
    private int y;

    public Splash(Image image, int x, int y, float width, float height) {
        this.image = image;
        this.x = x;
        this.y = y;
        image.setSize(width, height);
        image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
        image.setPosition(x, y);
    }

    public void doSplash(int delay, Runnable run) {
        SequenceAction sequenceAction = new SequenceAction();
        sequenceAction.addAction(alpha(0f));
        sequenceAction.addAction(delay(delay));
        sequenceAction.addAction(Actions.parallel(fadeIn(3), scaleTo(1.0f, 1.0f, 3f, Interpolation.pow5)));
        sequenceAction.addAction(delay(1f));
        sequenceAction.addAction(fadeOut(2));
        sequenceAction.addAction(delay(1f));

        if(isRunnable())
            sequenceAction.addAction(Actions.run(run));
        image.addAction(sequenceAction);

    }

    public void setRunnable(boolean runnable) {
        this.runnable = runnable;
    }

    private boolean isRunnable() {
        return runnable;
    }

    public Image getImage() {
        return image;
    }


}
