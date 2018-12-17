package simulator.ui.views.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import simulator.Simulation;
import simulator.lib.ui.FormView;

public class MaxQueueCapacityController implements FormView
{
    @FXML private TextField field;

    @FXML
    public void initialize () {
        this.registerNumericFieldListeners(field, Simulation.instance.kProperty());
    }
}
