package br.com.devio.component.domain.model;

import br.com.devio.component.domain.enumeration.PeriodTypeEnum;
import br.com.devio.component.domain.model.BenchmarkModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class BenchmarkModelTest {

    private BenchmarkModel benchmarkModel;

    @BeforeEach
    void setUp() {
        benchmarkModel = new BenchmarkModel();
    }

    @Test
    void testNoArgsConstructor() {
        BenchmarkModel model = new BenchmarkModel();
        assertNotNull(model);
        assertNull(model.getName());
        assertNull(model.getPeriodType());
        assertNull(model.getInterestRate());
        assertNull(model.getInterestRateTotalComposition());
    }

    @Test
    void testAllArgsConstructor() {
        BenchmarkModel model = new BenchmarkModel(
                "Benchmark Test",
                PeriodTypeEnum.MONTHLY,
                BigDecimal.valueOf(5.0),
                BigDecimal.valueOf(10.0)
        );

        assertEquals("Benchmark Test", model.getName());
        assertEquals(PeriodTypeEnum.MONTHLY, model.getPeriodType());
        assertEquals(BigDecimal.valueOf(5.0), model.getInterestRate());
        assertEquals(BigDecimal.valueOf(10.0), model.getInterestRateTotalComposition());
    }

    @Test
    void testBuilder() {
        BenchmarkModel model = BenchmarkModel.builder()
                .name("Benchmark Builder")
                .periodType(PeriodTypeEnum.DAILY)
                .interestRate(BigDecimal.valueOf(3.5))
                .interestRateTotalComposition(BigDecimal.valueOf(7.0))
                .build();

        assertEquals("Benchmark Builder", model.getName());
        assertEquals(PeriodTypeEnum.DAILY, model.getPeriodType());
        assertEquals(BigDecimal.valueOf(3.5), model.getInterestRate());
        assertEquals(BigDecimal.valueOf(7.0), model.getInterestRateTotalComposition());
    }

    @Test
    void testSettersAndGetters() {
        benchmarkModel.setName("Benchmark Setter");
        benchmarkModel.setPeriodType(PeriodTypeEnum.YEARLY);
        benchmarkModel.setInterestRate(BigDecimal.valueOf(2.5));
        benchmarkModel.setInterestRateTotalComposition(BigDecimal.valueOf(5.0));

        assertEquals("Benchmark Setter", benchmarkModel.getName());
        assertEquals(PeriodTypeEnum.YEARLY, benchmarkModel.getPeriodType());
        assertEquals(BigDecimal.valueOf(2.5), benchmarkModel.getInterestRate());
        assertEquals(BigDecimal.valueOf(5.0), benchmarkModel.getInterestRateTotalComposition());
    }
}