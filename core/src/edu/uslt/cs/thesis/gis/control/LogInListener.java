package edu.uslt.cs.thesis.gis.control;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.uslt.cs.thesis.gis.core.GIS;
import edu.uslt.cs.thesis.gis.gui.panels.LoginPanel;
import edu.uslt.cs.thesis.gis.screen.EditorState;
import edu.uslt.cs.thesis.gis.screen.MainState;

public class LogInListener extends ClickListener {

    private ImageTextButton logIn;
    private ImageTextButton btnUser;
    private TextField user;
    private TextField password;
    private GIS GIS;

    public LogInListener(LoginPanel panel, GIS GIS) {
        this.logIn = panel.login;
        this.user = panel.txtUserName;
        this.password = panel.txtPassword;
        this.GIS = GIS;
        this.btnUser = panel.btnUser;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (event.getTarget().equals(logIn)) {
            if (user.getText().equals("admin") && password.getText().equals("admin")) {
                GIS.setScreen(new EditorState(GIS));
            }
            return;
        }
        if (event.getTarget().equals(btnUser)) {
            GIS.setScreen(new MainState(GIS));

        }
    }
}
