package br.com.devio.infraestructure.exception;

import br.com.fluentvalidator.context.ValidationResult;
import br.com.fluentvalidator.exception.ValidationException;

public class BadRequestException extends ValidationException {
    private final ValidationResult validationResult;

    public BadRequestException(ValidationResult validationResult) {
        super(validationResult);
        this.validationResult = validationResult;
    }

    public ValidationResult getValidationResult() {
        return validationResult;
    }
}
