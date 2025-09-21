package br.com.devio.domain.exception;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;

public class CalculatorValidationException extends ValidationException {
    private final ValidationResult validationResult;

    public CalculatorValidationException(ValidationResult validationResult) {
        super(validationResult);
        this.validationResult = validationResult;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }
}
