package simulator.ui.views.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import simulator.Simulation;
import simulator.lib.TimeUnit;
import simulator.lib.ui.FormView;

public class InputsController implements FormView
{
    @FXML private TextField nbServersField;
    @FXML private TextField maxQueueSizeField;
    @FXML private TextField nbArrivalsPerPeriodField;
    @FXML private TextField nbServicesPerPeriodField;

    @FXML private ComboBox<TimeUnit> servicePeriodChoice;
    @FXML private ComboBox<TimeUnit> arrivalPeriodChoice;

    @FXML
    public void initialize () {
        arrivalPeriodChoice.getSelectionModel().select(Simulation.DEFAULT_LAMBDA_TIME_UNIT);
        servicePeriodChoice.getSelectionModel().select(Simulation.DEFAULT_MU_TIME_UNIT);

        this.registerNumericFieldListeners(nbArrivalsPerPeriodField, Simulation.instance.lambdaProperty());
        this.registerNumericFieldListeners(nbServicesPerPeriodField, Simulation.instance.muProperty());
        this.registerNumericFieldListeners(maxQueueSizeField, Simulation.instance.kProperty());
        this.registerNumericFieldListeners(nbServersField, Simulation.instance.sProperty());

        arrivalPeriodChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            Simulation.instance.setLambdaTimeUnit(newValue);
        });

        servicePeriodChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            Simulation.instance.setMuTimeUnit(newValue);
        });
    }
}
