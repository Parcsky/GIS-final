package edu.uslt.cs.thesis.gis.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class MenuPanel implements Panel {

    private Table menuTable;

    public MenuPanel(Skin skin) {
        menuTable = new Table(skin);
        menuTable.setBackground("container");
        menuTable.setVisible(false);
        menuTable.pad(5);

        String[][] buttonText = {{"Building Info", "Terrain Info", "Quit"}, {"default", "setting-btn", "exit-btn"}};

        for (int i = 0; i < buttonText[0].length; i++) {
            ImageTextButton imageTextButton = new ImageTextButton(buttonText[0][i], skin, buttonText[1][i]);
            imageTextButton.setChecked(false);
            imageTextButton.getLabelCell().minSize(0).prefSize(0);
            imageTextButton.setName(buttonText[0][i]);
            menuTable.add(imageTextButton).minSize(0, 0).pad(1).row();
        }
    }

    public Actor getChildren(String name) {
        for (int i = 0; i < size(); i++) {
            if (menuTable.getChildren().get(i).getName().equals(name)) {
                return menuTable.getChildren().get(i);
            }
        }
        return null;
    }

    @Override
    public void setName(String name) {
        menuTable.setName(name);
    }

    public int size() {
        return menuTable.getChildren().size;
    }

    @Override
    public void hide() {
        menuTable.setVisible(false);
    }

    @Override
    public void show() {
        menuTable.setVisible(true);
    }

    @Override
    public String getName() {
        return menuTable.getName();
    }

    public Table getTable() {
        return menuTable;
    }

}
