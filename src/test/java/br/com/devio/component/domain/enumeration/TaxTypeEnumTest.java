package br.com.devio.component.domain.enumeration;

import br.com.devio.component.domain.enumeration.TaxTypeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxTypeEnumTest {

    @Test
    void testEnumValues() {
        // Act
        TaxTypeEnum[] values = TaxTypeEnum.values();

        // Assert
        assertEquals(3, values.length);
        assertEquals(TaxTypeEnum.IOF_DIA, values[0]);
        assertEquals(TaxTypeEnum.IOF_ADICIONAL, values[1]);
        assertEquals(TaxTypeEnum.IOF_TOTAL, values[2]);
    }

    @Test
    void testEnumValueOf() {
        // Act & Assert
        assertEquals(TaxTypeEnum.IOF_DIA, TaxTypeEnum.valueOf("IOF_DIA"));
        assertEquals(TaxTypeEnum.IOF_ADICIONAL, TaxTypeEnum.valueOf("IOF_ADICIONAL"));
        assertEquals(TaxTypeEnum.IOF_TOTAL, TaxTypeEnum.valueOf("IOF_TOTAL"));
    }
}