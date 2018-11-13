package lib.ui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * @author Utilisateur
 * @date 05/07/2017
 */
public class Template extends Scene
{
    public final static Template instance = new Template();

    public final static int FRAME_WIDTH = 1200;
    public final static int FRAME_HEIGHT = 800;

    private Pane layout = new BorderPane();

    public Template() {
        super(new Pane());


    }
}