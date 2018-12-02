package simulator.lib;

import javafx.scene.control.TextField;

public class Validator
{
    public static boolean checkNumericalValue (TextField field, String newValue) {
        if (!newValue.matches("\\d*")) {
            field.setText(newValue.replaceAll("[^\\d]", ""));
            return false;
        }
        return true;
    }

    public static boolean checkDecimalValue (TextField field, String newValue, String oldValue) {
        if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
            field.setText(oldValue);
            return false;
        }
        return true;
    }
}
