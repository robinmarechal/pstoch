package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import ui.views.ViewReference;

import java.io.IOException;

public class ViewLoader
{
    public static Pane load (ViewReference viewReference) {

        FXMLLoader fxmlLoader = new FXMLLoader(ViewLoader.class.getResource("views/" + viewReference + ".fxml"));

        try {
            return fxmlLoader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
