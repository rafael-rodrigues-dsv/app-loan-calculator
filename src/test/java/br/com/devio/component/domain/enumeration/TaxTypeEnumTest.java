package br.com.devio.component.domain.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxTypeEnumTest {

    @Test
    void testEnumValues() {
        // Act
        TaxTypeEnum[] values = TaxTypeEnum.values();

        // Assert
        assertEquals(3, values.length);
        assertEquals(TaxTypeEnum.DAILY_IOF, values[0]);
        assertEquals(TaxTypeEnum.ADDITIONAL_IOF, values[1]);
        assertEquals(TaxTypeEnum.TOTAL_IOF, values[2]);
    }

    @Test
    void testEnumValueOf() {
        // Act & Assert
        assertEquals(TaxTypeEnum.DAILY_IOF, TaxTypeEnum.valueOf("DAILY_IOF"));
        assertEquals(TaxTypeEnum.ADDITIONAL_IOF, TaxTypeEnum.valueOf("ADDITIONAL_IOF"));
        assertEquals(TaxTypeEnum.TOTAL_IOF, TaxTypeEnum.valueOf("TOTAL_IOF"));
    }
}