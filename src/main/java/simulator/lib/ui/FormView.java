package simulator.lib.ui;

import javafx.beans.property.Property;
import javafx.scene.control.TextField;
import simulator.lib.Validator;

public interface FormView
{
    default void registerNumericFieldListeners (TextField field, Property<Number> targetProperty){
        field.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue.isEmpty()){
                newValue = "0";
            }

            if (Validator.checkDecimalValue(field, newValue, oldValue)) {
                targetProperty.setValue(Double.parseDouble(newValue));
            }
        });
    }
}
