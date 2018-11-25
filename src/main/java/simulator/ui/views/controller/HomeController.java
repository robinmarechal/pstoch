package simulator.ui.views.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import simulator.lib.ui.Template;

public class HomeController extends VBox
{
    @FXML
    public void newSimulation (ActionEvent actionEvent) {
        Template.instance.startSimulation();
    }
}
