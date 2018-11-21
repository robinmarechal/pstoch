package lib.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import ui.ViewLoader;
import ui.views.ViewReference;

/**
 * @author Utilisateur
 * @date 05/07/2017
 */
public class Template extends Scene
{
    public final static Template instance = new Template();

    public final static int FRAME_WIDTH = 800;
    public final static int FRAME_HEIGHT = 500;

    public final static double MAX_HEIGHT = 8192;
    public final static double MAX_WIDTH = 8192;

    private final Label titleLabel;

    private BorderPane layout;

    public Template () {
        super(new BorderPane());

        layout = (BorderPane) this.getRoot();
        layout.setPadding(new Insets(32, 64, 32, 64));
        //        layout.setPrefWidth(FRAME_WIDTH);
        //        layout.setPrefHeight(FRAME_HEIGHT);

        titleLabel = buildTitle();

        layout.setTop(titleLabel);
        layout.setCenter(ViewLoader.load(ViewReference.HOME));
    }

    private Label buildTitle () {
        Label label = new Label("F&R's Waiting Queues Theory Simulator");

        label.setFont(Font.font(22));
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setPadding(new Insets(0, 0, 32, 0));
        label.setMaxWidth(MAX_WIDTH);

        return label;
    }
}