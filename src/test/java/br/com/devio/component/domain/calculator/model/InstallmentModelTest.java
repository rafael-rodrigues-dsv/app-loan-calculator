package br.com.devio.component.domain.calculator.model;

import br.com.devio.component.domain.calculator.enumeration.PeriodTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class InstallmentModelTest {

    private InstallmentModel installmentModel;

    @BeforeEach
    void setUp() {
        installmentModel = new InstallmentModel();
    }

    @Test
    void testNoArgsConstructor() {
        InstallmentModel model = new InstallmentModel();
        assertNotNull(model);
        assertNull(model.getNumber());
        assertNull(model.getDueDate());
        assertNull(model.getContractDays());
        assertNull(model.getPeriodDays());
        assertNull(model.getInterestRate());
        assertNull(model.getInterestRateType());
        assertNull(model.getTotalInstalmentValue());
        assertNull(model.getTotalInterestAmount());
        assertNull(model.getTotalAmortizationAmount());
        assertNull(model.getTotalBalanceAmount());
    }

    @Test
    void testAllArgsConstructor() {
        InstallmentModel model = new InstallmentModel(
                1,
                LocalDate.of(2025, 4, 30),
                30,
                15,
                BigDecimal.valueOf(5.0),
                PeriodTypeEnum.MONTHLY,
                BigDecimal.valueOf(1000.0),
                BigDecimal.valueOf(50.0),
                BigDecimal.valueOf(950.0),
                BigDecimal.valueOf(5000.0)
        );

        assertEquals(1, model.getNumber());
        assertEquals(LocalDate.of(2025, 4, 30), model.getDueDate());
        assertEquals(30, model.getContractDays());
        assertEquals(15, model.getPeriodDays());
        assertEquals(BigDecimal.valueOf(5.0), model.getInterestRate());
        assertEquals(PeriodTypeEnum.MONTHLY, model.getInterestRateType());
        assertEquals(BigDecimal.valueOf(1000.0), model.getTotalInstalmentValue());
        assertEquals(BigDecimal.valueOf(50.0), model.getTotalInterestAmount());
        assertEquals(BigDecimal.valueOf(950.0), model.getTotalAmortizationAmount());
        assertEquals(BigDecimal.valueOf(5000.0), model.getTotalBalanceAmount());
    }

    @Test
    void testBuilder() {
        InstallmentModel model = InstallmentModel.builder()
                .number(2)
                .dueDate(LocalDate.of(2025, 5, 15))
                .contractDays(60)
                .periodDays(30)
                .interestRate(BigDecimal.valueOf(3.5))
                .interestRateType(PeriodTypeEnum.DAILY)
                .totalInstalmentValue(BigDecimal.valueOf(2000.0))
                .totalInterestAmount(BigDecimal.valueOf(70.0))
                .totalAmortizationAmount(BigDecimal.valueOf(1930.0))
                .totalBalanceAmount(BigDecimal.valueOf(4000.0))
                .build();

        assertEquals(2, model.getNumber());
        assertEquals(LocalDate.of(2025, 5, 15), model.getDueDate());
        assertEquals(60, model.getContractDays());
        assertEquals(30, model.getPeriodDays());
        assertEquals(BigDecimal.valueOf(3.5), model.getInterestRate());
        assertEquals(PeriodTypeEnum.DAILY, model.getInterestRateType());
        assertEquals(BigDecimal.valueOf(2000.0), model.getTotalInstalmentValue());
        assertEquals(BigDecimal.valueOf(70.0), model.getTotalInterestAmount());
        assertEquals(BigDecimal.valueOf(1930.0), model.getTotalAmortizationAmount());
        assertEquals(BigDecimal.valueOf(4000.0), model.getTotalBalanceAmount());
    }

    @Test
    void testSettersAndGetters() {
        installmentModel.setNumber(3);
        installmentModel.setDueDate(LocalDate.of(2025, 6, 1));
        installmentModel.setContractDays(90);
        installmentModel.setPeriodDays(45);
        installmentModel.setInterestRate(BigDecimal.valueOf(4.0));
        installmentModel.setInterestRateType(PeriodTypeEnum.YEARLY);
        installmentModel.setTotalInstalmentValue(BigDecimal.valueOf(1500.0));
        installmentModel.setTotalInterestAmount(BigDecimal.valueOf(60.0));
        installmentModel.setTotalAmortizationAmount(BigDecimal.valueOf(1440.0));
        installmentModel.setTotalBalanceAmount(BigDecimal.valueOf(3000.0));

        assertEquals(3, installmentModel.getNumber());
        assertEquals(LocalDate.of(2025, 6, 1), installmentModel.getDueDate());
        assertEquals(90, installmentModel.getContractDays());
        assertEquals(45, installmentModel.getPeriodDays());
        assertEquals(BigDecimal.valueOf(4.0), installmentModel.getInterestRate());
        assertEquals(PeriodTypeEnum.YEARLY, installmentModel.getInterestRateType());
        assertEquals(BigDecimal.valueOf(1500.0), installmentModel.getTotalInstalmentValue());
        assertEquals(BigDecimal.valueOf(60.0), installmentModel.getTotalInterestAmount());
        assertEquals(BigDecimal.valueOf(1440.0), installmentModel.getTotalAmortizationAmount());
        assertEquals(BigDecimal.valueOf(3000.0), installmentModel.getTotalBalanceAmount());
    }
}