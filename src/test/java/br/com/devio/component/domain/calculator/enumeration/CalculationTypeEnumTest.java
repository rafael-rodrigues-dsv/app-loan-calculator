package br.com.devio.component.domain.calculator.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculationTypeEnumTest {

    @Test
    void testEnumValues() {
        // Act
        CalculationTypeEnum[] values = CalculationTypeEnum.values();

        // Assert
        assertEquals(2, values.length);
        assertEquals(CalculationTypeEnum.PRICE, values[0]);
        assertEquals(CalculationTypeEnum.SAC, values[1]);
    }

    @Test
    void testEnumValueOf() {
        // Act & Assert
        assertEquals(CalculationTypeEnum.PRICE, CalculationTypeEnum.valueOf("PRICE"));
        assertEquals(CalculationTypeEnum.SAC, CalculationTypeEnum.valueOf("SAC"));
    }
}