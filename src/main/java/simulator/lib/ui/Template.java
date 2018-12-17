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
import simulator.ui.ViewLoader;
import simulator.ui.views.ViewReference;

/**
 * @author Utilisateur
 * @date 05/07/2017
 */
public class Template extends Scene
{
    public final static Template instance = new Template();

    public final static int FRAME_WIDTH = 600;
    public final static int FRAME_HEIGHT = 400;

    public final static double MAX_HEIGHT = 8192;
    public final static double MAX_WIDTH = 8192;

    private int nbSteps;

    private SimpleIntegerProperty step = new SimpleIntegerProperty(-1);

    private Label titleLabel;
    private HBox prevNextButtonsRow;

    private BorderPane layout;

    private Button prevBtn;
    private Button nextBtn;
    private Pane[] stepViews;

    private Runnable resultsReloader;

    private Template () {
        super(new BorderPane());
    }

    private void prepareStepViews () {
        stepViews = new Pane[]{
                ViewLoader.load(ViewReference.INPUTS),
                ViewLoader.load(ViewReference.RESULTS)
        };

        nbSteps = stepViews.length;
    }

    private void updateMiddleRow (int newStep) {
        if (newStep == nbSteps - 1) {
            this.resultsReloader.run();
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
        row.setPadding(new Insets(30, 0, 0, 0));

        return row;
    }

    private void updatePrevNext (int newValue) {
        if (newValue == 0) {
            disableButton(prevBtn);
            enableButton(nextBtn);
        }
        else if (newValue > 0 && newValue < nbSteps - 1){
            enableButton(prevBtn);
            enableButton(nextBtn);
        }
        else{
            disableButton(nextBtn);
            enableButton(prevBtn);
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
        Label label = new Label("Simulateur de files d'attente");

        label.setFont(Font.font(26));
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setMaxWidth(MAX_WIDTH);

        return label;
    }

    public void startSimulation () {
        this.step.setValue(1);
    }

    public void setOnResultsReload (Runnable callable) {
        this.resultsReloader = callable;
    }

    public void load () {
        prepareStepViews();

        layout = (BorderPane) this.getRoot();
        layout.setPadding(new Insets(32));
        layout.setPrefWidth(FRAME_WIDTH);

        titleLabel = buildTitle();
        prevNextButtonsRow = builtPrevNexButtons();

        layout.setTop(titleLabel);
        layout.setBottom(prevNextButtonsRow);

        step.addListener((observable, oldValue, newValue) -> {
            updateMiddleRow(newValue.intValue());
            updatePrevNext(newValue.intValue());
        });

        step.setValue(0);
    }
}