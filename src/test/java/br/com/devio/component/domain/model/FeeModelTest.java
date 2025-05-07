package br.com.devio.component.domain.model;

import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
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
                PaymentTypeEnum.FINANCED,
                BigDecimal.valueOf(100.0)
        );

        assertEquals(PaymentTypeEnum.FINANCED, model.getPaymentType());
        assertEquals(BigDecimal.valueOf(100.0), model.getValue());
    }

    @Test
    void testBuilder() {
        FeeModel model = FeeModel.builder()
                .paymentType(PaymentTypeEnum.FINANCED)
                .value(BigDecimal.valueOf(200.0))
                .build();

        assertEquals(PaymentTypeEnum.FINANCED, model.getPaymentType());
        assertEquals(BigDecimal.valueOf(200.0), model.getValue());
    }

    @Test
    void testSettersAndGetters() {
        feeModel.setPaymentType(PaymentTypeEnum.FINANCED);
        feeModel.setValue(BigDecimal.valueOf(300.0));

        assertEquals(PaymentTypeEnum.FINANCED, feeModel.getPaymentType());
        assertEquals(BigDecimal.valueOf(300.0), feeModel.getValue());
    }
}