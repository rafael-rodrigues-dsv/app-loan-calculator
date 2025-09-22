package br.com.devio.infraestructure.exception;

import br.com.fluentvalidator.context.ValidationResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BadRequestExceptionTest {

    @Test
    void testConstructorAndGetter() {
        // Arrange
        ValidationResult validationResult = ValidationResult.ok();

        // Act
        BadRequestException exception = new BadRequestException(validationResult);

        // Assert
        assertNotNull(exception);
        assertEquals(validationResult, exception.getValidationResult());
    }

    @Test
    void testSuperConstructor() {
        // Arrange
        ValidationResult validationResult = ValidationResult.ok();

        // Act
        BadRequestException exception = new BadRequestException(validationResult);

        // Assert
        assertEquals(validationResult, exception.getValidationResult());
    }
}