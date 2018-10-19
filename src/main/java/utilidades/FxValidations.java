package utilidades;

import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.decoration.StyleClassValidationDecoration;
import org.controlsfx.validation.decoration.ValidationDecoration;

public class FxValidations {

    private ValidationSupport validationSupport = new ValidationSupport();
    private ValidationDecoration cssDecorator = new StyleClassValidationDecoration();

    public FxValidations() {

       //validationSupport.setValidationDecorator(cssDecorator);
        validationSupport.setValidationDecorator(new StyleClassValidationDecoration("error","warning"));
    }

    public ValidationSupport getValidationSupport() {
        return validationSupport;
    }

    public void setValidationSupport(ValidationSupport validationSupport) {
        this.validationSupport = validationSupport;
    }
}
