package br.com.devio.component.domain.calculator.exception;

import br.com.devio.component.domain.exception.CalculatorValidationException;
import br.com.fluentvalidator.context.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorValidationExceptionTest {

    @Test
    void testConstructorAndGetter() {
        // Arrange
        ValidationResult validationResult = ValidationResult.ok();

        // Act
        CalculatorValidationException exception = new CalculatorValidationException(validationResult);

        // Assert
        assertNotNull(exception);
        assertEquals(validationResult, exception.getValidationResult());
    }

    @Test
    void testSuperConstructor() {
        // Arrange
        ValidationResult validationResult = ValidationResult.ok();

        // Act
        CalculatorValidationException exception = new CalculatorValidationException(validationResult);

        // Assert
        assertEquals(validationResult, exception.getValidationResult());
    }
}