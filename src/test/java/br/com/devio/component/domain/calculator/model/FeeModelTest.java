package br.com.devio.component.domain.calculator.model;

import br.com.devio.component.domain.calculator.enumeration.PaymentTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class FeeModelTest {

    private FeeModel feeModel;

    @BeforeEach
    void setUp() {
        feeModel = new FeeModel();
    }

    @Test
    void testNoArgsConstructor() {
        FeeModel model = new FeeModel();
        assertNotNull(model);
        assertNull(model.getPaymentType());
        assertNull(model.getValue());
    }

    @Test
    void testAllArgsConstructor() {
        FeeModel model = new FeeModel(
                PaymentTypeEnum.FINANCIADO,
                BigDecimal.valueOf(100.0)
        );

        assertEquals(PaymentTypeEnum.FINANCIADO, model.getPaymentType());
        assertEquals(BigDecimal.valueOf(100.0), model.getValue());
    }

    @Test
    void testBuilder() {
        FeeModel model = FeeModel.builder()
                .paymentType(PaymentTypeEnum.FINANCIADO)
                .value(BigDecimal.valueOf(200.0))
                .build();

        assertEquals(PaymentTypeEnum.FINANCIADO, model.getPaymentType());
        assertEquals(BigDecimal.valueOf(200.0), model.getValue());
    }

    @Test
    void testSettersAndGetters() {
        feeModel.setPaymentType(PaymentTypeEnum.FINANCIADO);
        feeModel.setValue(BigDecimal.valueOf(300.0));

        assertEquals(PaymentTypeEnum.FINANCIADO, feeModel.getPaymentType());
        assertEquals(BigDecimal.valueOf(300.0), feeModel.getValue());
    }
}