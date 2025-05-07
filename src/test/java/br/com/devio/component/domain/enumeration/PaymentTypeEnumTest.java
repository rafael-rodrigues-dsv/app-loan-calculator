package br.com.devio.component.domain.enumeration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentTypeEnumTest {

    @Test
    void testEnumValues() {
        // Act
        PaymentTypeEnum[] values = PaymentTypeEnum.values();

        // Assert
        assertEquals(2, values.length);
        assertEquals(PaymentTypeEnum.FINANCED, values[0]);
        assertEquals(PaymentTypeEnum.UPFRONT, values[1]);
    }

    @Test
    void testEnumValueOf() {
        // Act & Assert
        assertEquals(PaymentTypeEnum.FINANCED, PaymentTypeEnum.valueOf("FINANCED"));
        assertEquals(PaymentTypeEnum.UPFRONT, PaymentTypeEnum.valueOf("UPFRONT"));
    }
}