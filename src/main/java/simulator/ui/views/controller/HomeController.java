package simulator.ui.views.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import simulator.lib.ui.Template;

public class HomeController
{
    @FXML
    public void newSimulation (ActionEvent actionEvent) {
        Template.instance.startSimulation();
    }
}
