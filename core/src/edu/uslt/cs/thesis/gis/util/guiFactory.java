package edu.uslt.cs.thesis.gis.util;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

public class GuiFactory implements Factory {

    public TextField createTextField(Skin skin, String styleName, boolean password) {
        TextField textField = new TextField("", skin, styleName);
        textField.getStyle().cursor.setMinHeight(3);
        textField.getStyle().cursor.setMinWidth(4);
        textField.setBlinkTime(.55f);
        textField.setAlignment(Align.center);
        if (password) {
            textField.setPasswordMode(true);
            textField.setPasswordCharacter('*');
            return textField;
        }

        return textField;
    }
}
