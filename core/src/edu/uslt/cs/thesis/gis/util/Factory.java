package edu.uslt.cs.thesis.gis.util;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;


public interface  Factory {

    TextField createTextField(Skin skin, String styleName, boolean password);
}
