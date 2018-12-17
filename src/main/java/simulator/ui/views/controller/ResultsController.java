package simulator.ui.views.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import simulator.Simulation;
import simulator.WaitingQueueSolver;
import simulator.lib.TimeUnit;
import simulator.lib.exception.NotImplementedException;
import simulator.lib.exception.QueueingException;
import simulator.lib.exception.enums.QueueingExceptionType;
import simulator.lib.ui.Template;

public class ResultsController
{
    private static final String RED_COLOR_CLASS = "text-red";

    @FXML private ComboBox<TimeUnit> timeUnitChoice;
    @FXML private Label lLabel;
    @FXML private Label lqLabel;
    @FXML private Label wLabel;
    @FXML private Label wqLabel;
    @FXML private Label rhoLabel;
    @FXML private Label minServersLabel;
    @FXML private Label minServersTextLabel;

    private TimeUnit timeUnit = Simulation.DEFAULT_MU_TIME_UNIT;

    public ResultsController () {
        Template.instance.setOnResultsReload(() -> this.updateValues());
    }

    @FXML
    public void initialize () {
        timeUnitChoice.setValue(Simulation.DEFAULT_MU_TIME_UNIT);
        timeUnitChoice.valueProperty().addListener((observable, oldTimeUnit, newTimeUnit) -> {
            try {
                this.convertWAndWq(newTimeUnit);
            }
            catch (QueueingException e) {
                // Nothing to do
            }
        });
    }

    private void convertWAndWq (TimeUnit newTimeUnit) {
        Simulation simulation = Simulation.instance;
        WaitingQueueSolver solver = (WaitingQueueSolver) simulation.getSolver();

        double w = simulation.getMuTimeUnit().convertTimeTo(solver.W(), newTimeUnit);
        double wq = simulation.getMuTimeUnit().convertTimeTo(solver.Wq(), newTimeUnit);

        wLabel.setText(String.format("%.2f %s", w, newTimeUnit.shortValue));
        wqLabel.setText(String.format("%.2f %s", wq, newTimeUnit.shortValue));
    }

    private void updateValues () {
        Simulation simulation = Simulation.instance;
        unredifyLabels();

        try {
            WaitingQueueSolver solver = (WaitingQueueSolver) simulation.guessSolverInstance();

            timeUnit = simulation.getMuTimeUnit();
            timeUnitChoice.setValue(timeUnit);

            try {
                minServersLabel.setText(String.format("%d", (int) Math.ceil(solver.bottleNeck())));
            }
            catch (QueueingException e) {
                if (e.getType() == QueueingExceptionType.NO_POSSIBLE_BOTTLENECK) {
                    minServersLabel.setText("-");
                }
                else {
                    throw e;
                }
            }

            lLabel.setText(String.format("%.2f", solver.L()));
            lqLabel.setText(String.format("%.2f", solver.Lq()));
            wLabel.setText(String.format("%.2f %s", solver.W(), timeUnit.shortValue));
            wqLabel.setText(String.format("%.2f %s", solver.Wq(), timeUnit.shortValue));
            rhoLabel.setText(String.format("%.2f%%", solver.rho() * 100));
        }
        catch (QueueingException e) {
            System.err.println(e.getMessage());
            String header = "Engorgement";
            String content;

            if (e.getType() == QueueingExceptionType.RHO_GT_1) {
                content = "Le rapport (Arrivées ÷ (Services * Guichets)) est supérieur ou égal 1.\n";
                content += "Cela signifie que les clients arrivent trop vite par rapport au temps\n";
                content += "de service, la file s'étend donc jusqu'à l'infini\n";
                content += "Il faut donc augmenter le nombre de serveurs, le nombre de services\n";
                content += "ou diminuer le nombre d'arrivées.";

                alert(header, content, Alert.AlertType.WARNING);
            }
            else {
                e.printStackTrace();
                minServersLabel.setText("??");
            }

            this.resetLabels();

            redifyLabels();
        }
        catch (NotImplementedException e) {
            String header = "Impossible de résoudre le problème";
            String content = "La fonctionnalité permettant de simuler\n" +
                    "plusieurs guichets avec une file de taille\n" +
                    "limitée n'a pas été implémentée.";

            alert(header, content, Alert.AlertType.ERROR);
            this.resetLabels();
            minServersLabel.setText("??");
        }
    }

    private void redifyLabels(){
        minServersLabel.setTextFill(Color.RED);
        minServersTextLabel.setTextFill(Color.RED);
    }

    private void unredifyLabels(){
        minServersLabel.setTextFill(Color.BLACK);
        minServersTextLabel.setTextFill(Color.BLACK);
    }

    private void resetLabels(){lLabel.setText("??");
        lLabel.setText("??");
        lqLabel.setText("??");
        wLabel.setText("??");
        wqLabel.setText("??");
        rhoLabel.setText("??");
    }

    public void alert (String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(header);
        Label label = new Label(content);
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        alert.getDialogPane().setContent(label);
        alert.showAndWait();
    }
}