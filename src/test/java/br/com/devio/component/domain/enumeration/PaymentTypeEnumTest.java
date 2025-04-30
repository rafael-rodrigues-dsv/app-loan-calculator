package br.com.devio.component.domain.enumeration;

import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentTypeEnumTest {

    @Test
    void testEnumValues() {
        // Act
        PaymentTypeEnum[] values = PaymentTypeEnum.values();

        // Assert
        assertEquals(2, values.length);
        assertEquals(PaymentTypeEnum.FINANCIADO, values[0]);
        assertEquals(PaymentTypeEnum.NO_ATO, values[1]);
    }

    @Test
    void testEnumValueOf() {
        // Act & Assert
        assertEquals(PaymentTypeEnum.FINANCIADO, PaymentTypeEnum.valueOf("FINANCIADO"));
        assertEquals(PaymentTypeEnum.NO_ATO, PaymentTypeEnum.valueOf("NO_ATO"));
    }
}