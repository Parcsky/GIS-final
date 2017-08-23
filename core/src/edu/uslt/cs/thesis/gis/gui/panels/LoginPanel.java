package edu.uslt.cs.thesis.gis.gui.panels;

import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import edu.uslt.cs.thesis.gis.gui.Panel;
import edu.uslt.cs.thesis.gis.util.Factory;
import edu.uslt.cs.thesis.gis.util.GuiFactory;

public class LoginPanel implements Panel {

    public TextField txtUserName;
    public TextField txtPassword;
    public ImageTextButton login;
    public ImageTextButton btnUser;
    private Table mainBox;

    public LoginPanel(Skin skin, int width, int height) {

        Label userName = new Label("Username", skin, "default");
        userName.setAlignment(Align.left);

        Label lblPassword = new Label("Password", skin, "default");
        lblPassword.setAlignment(Align.left);

        Factory guiFactory = new GuiFactory();
        txtUserName = guiFactory.createTextField(skin, "search-little", false);
        txtPassword = guiFactory.createTextField(skin, "search-little", true);

        Table userTable = new Table();
        userTable.add(userName).minSize(0).prefSize(width / 7, 40).left().center();
        userTable.add(txtUserName).minSize(0).prefSize(width / 2.5f, 40);

        Table passTable = new Table();
        passTable.add(lblPassword).minSize(0).prefSize(width / 7, 40).left();
        passTable.add(txtPassword).minSize(0).prefSize(width / 2.5f, 40);

        login = new ImageTextButton("Log In", skin, "default");
        btnUser = new ImageTextButton("User", skin, "default");

        Table table = new Table(skin);
        table.setBackground("rectangle-small");
        table.add(userTable).padBottom(5).prefWidth(width).colspan(2).left().row();
        table.add(passTable).colspan(2).prefWidth(width ).left();
        table.left();
        table.pad(5);

        mainBox = new Table(skin);
        mainBox.setFillParent(true);
        mainBox.add(table).minSize(0).prefSize(width * .6f, height * .2f).colspan(2).center().padBottom(3).row();
        mainBox.add(btnUser).minSize(0).prefSize(width / 5f, 40).left();
        mainBox.add(login).minSize(0).prefSize(width / 5f, 40).right();
    }

    @Override
    public void hide() {
        mainBox.setVisible(false);
    }

    @Override
    public void show() {
        mainBox.setVisible(true);

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    public Table getTable() {
        return mainBox;
    }
}
