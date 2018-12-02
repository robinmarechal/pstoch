package simulator.ui.views.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import simulator.Simulation;
import simulator.lib.TimeUnit;
import simulator.lib.ui.FormView;

public class LambdaController implements FormView
{
    @FXML private TextField nbArrivalsField;
    @FXML private ComboBox<TimeUnit> periodChoice;

    @FXML
    public void initialize () {
        periodChoice.getSelectionModel().select(Simulation.DEFAULT_LAMBDA_TIME_UNIT);

        this.registerNumericFieldListeners(nbArrivalsField, Simulation.instance.lambdaProperty());

        periodChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            Simulation.instance.setLambdaTimeUnit(newValue);
        });
    }
}
