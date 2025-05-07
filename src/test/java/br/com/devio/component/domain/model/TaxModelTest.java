package br.com.devio.component.domain.model;

import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import br.com.devio.component.domain.enumeration.TaxTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class TaxModelTest {

    private TaxModel taxModel;

    @BeforeEach
    void setUp() {
        taxModel = new TaxModel();
    }

    @Test
    void testNoArgsConstructor() {
        TaxModel model = new TaxModel();
        assertNotNull(model);
        assertNull(model.getPaymentType());
        assertNull(model.getValue());
        assertNull(model.getTaxType());
    }

    @Test
    void testAllArgsConstructor() {
        TaxModel model = new TaxModel(
                PaymentTypeEnum.FINANCED,
                BigDecimal.valueOf(100.0),
                TaxTypeEnum.DAILY_IOF
        );

        assertEquals(PaymentTypeEnum.FINANCED, model.getPaymentType());
        assertEquals(BigDecimal.valueOf(100.0), model.getValue());
        assertEquals(TaxTypeEnum.DAILY_IOF, model.getTaxType());
    }

    @Test
    void testBuilder() {
        TaxModel model = TaxModel.builder()
                .paymentType(PaymentTypeEnum.FINANCED)
                .value(BigDecimal.valueOf(200.0))
                .taxType(TaxTypeEnum.DAILY_IOF)
                .build();

        assertEquals(PaymentTypeEnum.FINANCED, model.getPaymentType());
        assertEquals(BigDecimal.valueOf(200.0), model.getValue());
        assertEquals(TaxTypeEnum.DAILY_IOF, model.getTaxType());
    }

    @Test
    void testSettersAndGetters() {
        taxModel.setPaymentType(PaymentTypeEnum.FINANCED);
        taxModel.setValue(BigDecimal.valueOf(300.0));
        taxModel.setTaxType(TaxTypeEnum.DAILY_IOF);

        assertEquals(PaymentTypeEnum.FINANCED, taxModel.getPaymentType());
        assertEquals(BigDecimal.valueOf(300.0), taxModel.getValue());
        assertEquals(TaxTypeEnum.DAILY_IOF, taxModel.getTaxType());
    }
}