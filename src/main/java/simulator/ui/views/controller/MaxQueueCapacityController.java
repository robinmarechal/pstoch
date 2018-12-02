package simulator.ui.views.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import simulator.Simulation;
import simulator.lib.ui.FormView;

public class MaxQueueCapacityController implements FormView
{
    @FXML private TextField field;

    public void textChanged (InputMethodEvent inputMethodEvent) {
        System.out.println(inputMethodEvent);
    }

    @FXML
    public void initialize () {
        this.registerNumericFieldListeners(field, Simulation.instance.kProperty());
    }
}
