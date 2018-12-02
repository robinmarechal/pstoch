package simulator.ui.views.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import simulator.Simulation;
import simulator.lib.TimeUnit;
import simulator.lib.ui.FormView;

public class MuController implements FormView
{
    @FXML private TextField nbServicesField;
    @FXML private ComboBox<TimeUnit> periodChoice;

    @FXML
    public void initialize () {
        periodChoice.getSelectionModel().select(Simulation.DEFAULT_MU_TIME_UNIT);

        this.registerNumericFieldListeners(nbServicesField, Simulation.instance.muProperty());

        periodChoice.valueProperty().addListener((observable, oldValue, newValue) -> {
            Simulation.instance.setMuTimeUnit(newValue);
        });
    }
}
