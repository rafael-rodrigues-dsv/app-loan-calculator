package br.com.devio.component.domain.model;

import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import br.com.devio.component.domain.enumeration.TaxTypeEnum;
import br.com.devio.component.domain.model.TaxModel;
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
                PaymentTypeEnum.FINANCIADO,
                BigDecimal.valueOf(100.0),
                TaxTypeEnum.IOF_DIA
        );

        assertEquals(PaymentTypeEnum.FINANCIADO, model.getPaymentType());
        assertEquals(BigDecimal.valueOf(100.0), model.getValue());
        assertEquals(TaxTypeEnum.IOF_DIA, model.getTaxType());
    }

    @Test
    void testBuilder() {
        TaxModel model = TaxModel.builder()
                .paymentType(PaymentTypeEnum.FINANCIADO)
                .value(BigDecimal.valueOf(200.0))
                .taxType(TaxTypeEnum.IOF_DIA)
                .build();

        assertEquals(PaymentTypeEnum.FINANCIADO, model.getPaymentType());
        assertEquals(BigDecimal.valueOf(200.0), model.getValue());
        assertEquals(TaxTypeEnum.IOF_DIA, model.getTaxType());
    }

    @Test
    void testSettersAndGetters() {
        taxModel.setPaymentType(PaymentTypeEnum.FINANCIADO);
        taxModel.setValue(BigDecimal.valueOf(300.0));
        taxModel.setTaxType(TaxTypeEnum.IOF_DIA);

        assertEquals(PaymentTypeEnum.FINANCIADO, taxModel.getPaymentType());
        assertEquals(BigDecimal.valueOf(300.0), taxModel.getValue());
        assertEquals(TaxTypeEnum.IOF_DIA, taxModel.getTaxType());
    }
}