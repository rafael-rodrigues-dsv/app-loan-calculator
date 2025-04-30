package br.com.devio.component.domain.calculator.model;

import br.com.devio.component.domain.calculator.enumeration.ModalityTypeEnum;
import br.com.devio.component.domain.calculator.enumeration.PeriodTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PricingModelTest {

    private PricingModel pricingModel;

    @BeforeEach
    void setUp() {
        pricingModel = new PricingModel();
    }

    @Test
    void testNoArgsConstructor() {
        PricingModel model = new PricingModel();
        assertNotNull(model);
        assertNull(model.getModalityType());
        assertNull(model.getPeriodType());
        assertNull(model.getInterestRate());
        assertNull(model.getBenchmarks());
    }

    @Test
    void testAllArgsConstructor() {
        BenchmarkModel benchmark = new BenchmarkModel();

        PricingModel model = new PricingModel(
                ModalityTypeEnum.PRE_FIXADO,
                PeriodTypeEnum.MONTHLY,
                BigDecimal.valueOf(5.0),
                List.of(benchmark)
        );

        assertEquals(ModalityTypeEnum.PRE_FIXADO, model.getModalityType());
        assertEquals(PeriodTypeEnum.MONTHLY, model.getPeriodType());
        assertEquals(BigDecimal.valueOf(5.0), model.getInterestRate());
        assertEquals(1, model.getBenchmarks().size());
        assertEquals(benchmark, model.getBenchmarks().get(0));
    }

    @Test
    void testBuilder() {
        BenchmarkModel benchmark = new BenchmarkModel();

        PricingModel model = PricingModel.builder()
                .modalityType(ModalityTypeEnum.PRE_FIXADO)
                .periodType(PeriodTypeEnum.DAILY)
                .interestRate(BigDecimal.valueOf(3.5))
                .benchmarks(List.of(benchmark))
                .build();

        assertEquals(ModalityTypeEnum.PRE_FIXADO, model.getModalityType());
        assertEquals(PeriodTypeEnum.DAILY, model.getPeriodType());
        assertEquals(BigDecimal.valueOf(3.5), model.getInterestRate());
        assertEquals(1, model.getBenchmarks().size());
        assertEquals(benchmark, model.getBenchmarks().get(0));
    }

    @Test
    void testSettersAndGetters() {
        BenchmarkModel benchmark = new BenchmarkModel();

        pricingModel.setModalityType(ModalityTypeEnum.PRE_FIXADO);
        pricingModel.setPeriodType(PeriodTypeEnum.YEARLY);
        pricingModel.setInterestRate(BigDecimal.valueOf(4.0));
        pricingModel.setBenchmarks(List.of(benchmark));

        assertEquals(ModalityTypeEnum.PRE_FIXADO, pricingModel.getModalityType());
        assertEquals(PeriodTypeEnum.YEARLY, pricingModel.getPeriodType());
        assertEquals(BigDecimal.valueOf(4.0), pricingModel.getInterestRate());
        assertEquals(1, pricingModel.getBenchmarks().size());
        assertEquals(benchmark, pricingModel.getBenchmarks().get(0));
    }
}