package simulator.lib.ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import simulator.Simulation;
import simulator.ui.ViewLoader;
import simulator.ui.views.ViewReference;

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

    public final static int NB_STEPS = 5;

    private SimpleIntegerProperty step = new SimpleIntegerProperty(-1);

    private final Label titleLabel;
    private final HBox prevNextButtonsRow;

    private BorderPane layout;

    private Button prevBtn;
    private Button nextBtn;
    private Pane[] stepViews;

    public Template () {
        super(new BorderPane());

        prepareStepViews();

        layout = (BorderPane) this.getRoot();
        layout.setPadding(new Insets(32, 64, 32, 64));
        //        layout.setPrefWidth(FRAME_WIDTH);
        //        layout.setPrefHeight(FRAME_HEIGHT);

        titleLabel = buildTitle();
        prevNextButtonsRow = builtPrevNexButtons();

        layout.setTop(titleLabel);
        layout.setBottom(prevNextButtonsRow);

        step.addListener((observable, oldValue, newValue) -> updateMiddleRow(newValue.intValue()));
        step.addListener((observable, oldValue, newValue) -> updatePrevNext(newValue.intValue()));

        step.setValue(0);
    }

    private void prepareStepViews () {
        stepViews = new Pane[]{
                ViewLoader.load(ViewReference.HOME),
                ViewLoader.load(ViewReference.NB_SERVERS),
                ViewLoader.load(ViewReference.MAX_QUEUE_CAPACITY),
                ViewLoader.load(ViewReference.PARAMETERS),
                null
        };
    }

    private void updateMiddleRow (int newStep) {

        if (newStep == NB_STEPS - 1) {
            Simulation.instance.solve();
            stepViews[newStep] = ViewLoader.load(ViewReference.RESULTS);
        }

        layout.setCenter(stepViews[newStep]);
    }

    private HBox builtPrevNexButtons () {
        Font font = Font.font(16);

        prevBtn = new Button("<");
        prevBtn.setFont(font);
        prevBtn.setOnAction(event -> step.setValue(step.intValue() - 1));

        nextBtn = new Button(">");
        nextBtn.setFont(font);
        nextBtn.setOnAction(event -> step.setValue(step.intValue() + 1));

        HBox row = new HBox(10, prevBtn, nextBtn);
        row.setAlignment(Pos.CENTER);

        return row;
    }

    private void updatePrevNext (int newValue) {
        if (newValue == 0) {
            prevBtn.setOpacity(0);
            nextBtn.setOpacity(0);

            nextBtn.setDisable(true);
            prevBtn.setDisable(true);
        }
        else {
            prevBtn.setOpacity(1);
            nextBtn.setOpacity(1);

            nextBtn.setDisable(false);
            prevBtn.setDisable(false);

            if (newValue == 1) {
                disableButton(prevBtn);
                enableButton(nextBtn);
            }
            else if (newValue == NB_STEPS - 1) {
                disableButton(nextBtn);
                enableButton(prevBtn);
            }
            else {
                enableButton(prevBtn);
                enableButton(nextBtn);
            }
        }
    }

    private void enableButton (Button btn) {
        btn.setDisable(false);
        btn.setOpacity(1);
    }

    private void disableButton (Button btn) {
        btn.setDisable(true);
        btn.setOpacity(0.5);
    }

    private Label buildTitle () {
        Label label = new Label("F&R's Waiting Queues Theory Simulator");

        label.setFont(Font.font(22));
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        //        label.setPadding(new Insets(0, 0, 32, 0));
        label.setMaxWidth(MAX_WIDTH);

        return label;
    }

    public void startSimulation () {
        this.step.setValue(1);
    }
}