package br.com.devio.component.domain.calculator.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModalityTypeEnumTest {

    @Test
    void testEnumValues() {
        // Act
        ModalityTypeEnum[] values = ModalityTypeEnum.values();

        // Assert
        assertEquals(2, values.length);
        assertEquals(ModalityTypeEnum.PRE_FIXADO, values[0]);
        assertEquals(ModalityTypeEnum.POS_FIXADO, values[1]);
    }

    @Test
    void testEnumValueOf() {
        // Act & Assert
        assertEquals(ModalityTypeEnum.PRE_FIXADO, ModalityTypeEnum.valueOf("PRE_FIXADO"));
        assertEquals(ModalityTypeEnum.POS_FIXADO, ModalityTypeEnum.valueOf("POS_FIXADO"));
    }
}