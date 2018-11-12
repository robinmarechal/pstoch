package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import lib.ui.Template;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        window.setResizable(false);
        window.setTitle("Processus stochastiques - Fanch Brault & Robin Maréchal");
        window.setOnCloseRequest(event -> System.exit(0));
        window.setScene(Template.instance);
        window.show();

        window.centerOnScreen();
    }
}