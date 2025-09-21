package br.com.devio.domain.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModalityTypeEnumTest {

    @Test
    void testEnumValues() {
        // Act
        ModalityTypeEnum[] values = ModalityTypeEnum.values();

        // Assert
        assertEquals(2, values.length);
        assertEquals(ModalityTypeEnum.FIXED_RATE, values[0]);
        assertEquals(ModalityTypeEnum.FLOATING_RATE, values[1]);
    }

    @Test
    void testEnumValueOf() {
        // Act & Assert
        assertEquals(ModalityTypeEnum.FIXED_RATE, ModalityTypeEnum.valueOf("FIXED_RATE"));
        assertEquals(ModalityTypeEnum.FLOATING_RATE, ModalityTypeEnum.valueOf("FLOATING_RATE"));
    }
}