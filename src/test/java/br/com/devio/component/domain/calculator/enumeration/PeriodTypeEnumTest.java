package br.com.devio.component.domain.calculator.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeriodTypeEnumTest {

    @Test
    void testEnumValues() {
        // Act
        PeriodTypeEnum[] values = PeriodTypeEnum.values();

        // Assert
        assertEquals(3, values.length);
        assertEquals(PeriodTypeEnum.DAILY, values[0]);
        assertEquals(PeriodTypeEnum.MONTHLY, values[1]);
        assertEquals(PeriodTypeEnum.YEARLY, values[2]);
    }

    @Test
    void testEnumValueOf() {
        // Act & Assert
        assertEquals(PeriodTypeEnum.DAILY, PeriodTypeEnum.valueOf("DAILY"));
        assertEquals(PeriodTypeEnum.MONTHLY, PeriodTypeEnum.valueOf("MONTHLY"));
        assertEquals(PeriodTypeEnum.YEARLY, PeriodTypeEnum.valueOf("YEARLY"));
    }
}