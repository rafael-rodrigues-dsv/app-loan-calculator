package br.com.devio.component.domain.model;

import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import br.com.devio.component.domain.model.InsuranceModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class InsuranceModelTest {

    private InsuranceModel insuranceModel;

    @BeforeEach
    void setUp() {
        insuranceModel = new InsuranceModel();
    }

    @Test
    void testNoArgsConstructor() {
        InsuranceModel model = new InsuranceModel();
        assertNotNull(model);
        assertNull(model.getPaymentType());
        assertNull(model.getValue());
    }

    @Test
    void testAllArgsConstructor() {
        InsuranceModel model = new InsuranceModel(
                PaymentTypeEnum.FINANCIADO,
                BigDecimal.valueOf(500.0)
        );

        assertEquals(PaymentTypeEnum.FINANCIADO, model.getPaymentType());
        assertEquals(BigDecimal.valueOf(500.0), model.getValue());
    }

    @Test
    void testBuilder() {
        InsuranceModel model = InsuranceModel.builder()
                .paymentType(PaymentTypeEnum.FINANCIADO)
                .value(BigDecimal.valueOf(1000.0))
                .build();

        assertEquals(PaymentTypeEnum.FINANCIADO, model.getPaymentType());
        assertEquals(BigDecimal.valueOf(1000.0), model.getValue());
    }

    @Test
    void testSettersAndGetters() {
        insuranceModel.setPaymentType(PaymentTypeEnum.FINANCIADO);
        insuranceModel.setValue(BigDecimal.valueOf(750.0));

        assertEquals(PaymentTypeEnum.FINANCIADO, insuranceModel.getPaymentType());
        assertEquals(BigDecimal.valueOf(750.0), insuranceModel.getValue());
    }
}