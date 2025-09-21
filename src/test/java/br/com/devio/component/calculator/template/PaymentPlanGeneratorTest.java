package br.com.devio.component.calculator.template;

import br.com.devio.component.calculator.template.PaymentPlanGenerator;
import br.com.devio.domain.enumeration.CalculationTypeEnum;
import br.com.devio.domain.model.FeeModel;
import br.com.devio.domain.model.TaxModel;
import br.com.devio.domain.model.InstallmentModel;
import br.com.devio.domain.model.InsuranceModel;
import br.com.devio.domain.model.LoanModel;
import br.com.devio.domain.model.PaymentPlanModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentPlanGeneratorTest {

    private PaymentPlanGenerator paymentPlanGenerator;

    @BeforeEach
    void setUp() {
        paymentPlanGenerator = new PaymentPlanGenerator();
    }

    @Test
    void testGeneratePaymentPlan() {
        // Arrange
        LoanModel loanModel = LoanModel.builder()
                .calculationType(CalculationTypeEnum.PRICE)
                .monthlyInterestRate(BigDecimal.valueOf(5.0))
                .installmentQuantity(12)
                .requestedAmount(BigDecimal.valueOf(10000.0))
                .contractDate(LocalDate.of(2025, 1, 1))
                .firstInstallmentDate(LocalDate.of(2025, 2, 1))
                .fee(new FeeModel())
                .insurance(new InsuranceModel())
                .tax(new TaxModel())
                .build();

        // Act
        PaymentPlanModel paymentPlan = paymentPlanGenerator.generatePaymentPlan(loanModel);

        // Assert
        assertNotNull(paymentPlan);
        assertEquals(CalculationTypeEnum.PRICE, paymentPlan.getCalculationType());
        assertEquals(loanModel.getMonthlyInterestRate(), paymentPlan.getMonthlyInterestRate());
        assertEquals(12, paymentPlan.getInstallmentQuantity());
        assertEquals(BigDecimal.valueOf(10000.0), paymentPlan.getRequestedAmount());
        assertEquals(LocalDate.of(2025, 1, 1), paymentPlan.getContractDate());
        assertEquals(LocalDate.of(2025, 2, 1), paymentPlan.getFirstInstallmentDate());
        assertEquals(LocalDate.of(2026, 1, 1), paymentPlan.getLastInstallmentDate());
        assertEquals(13, paymentPlan.getInstallments().size());
    }

    @Test
    void testAddInstallments() {
        // Arrange
        LoanModel loanModel = LoanModel.builder()
                .monthlyInterestRate(BigDecimal.valueOf(5.0))
                .installmentQuantity(3)
                .contractDate(LocalDate.of(2025, 1, 1))
                .firstInstallmentDate(LocalDate.of(2025, 2, 1))
                .build();

        // Act
        List<InstallmentModel> installments = paymentPlanGenerator.addInstallments(loanModel);

        // Assert
        assertNotNull(installments);
        assertEquals(4, installments.size()); // Includes the initial installment (0)
        assertEquals(0, installments.get(0).getNumber());
        assertEquals(LocalDate.of(2025, 1, 1), installments.get(0).getDueDate());
        assertEquals(1, installments.get(1).getNumber());
        assertEquals(LocalDate.of(2025, 2, 1), installments.get(1).getDueDate());
        assertEquals(2, installments.get(2).getNumber());
        assertEquals(LocalDate.of(2025, 3, 1), installments.get(2).getDueDate());
        assertEquals(3, installments.get(3).getNumber());
        assertEquals(LocalDate.of(2025, 4, 1), installments.get(3).getDueDate());
    }

    @Test
    void testAddLastInstallmentDate() {
        // Arrange
        List<InstallmentModel> installments = List.of(
                InstallmentModel.builder().dueDate(LocalDate.of(2025, 1, 1)).build(),
                InstallmentModel.builder().dueDate(LocalDate.of(2025, 2, 1)).build(),
                InstallmentModel.builder().dueDate(LocalDate.of(2025, 3, 1)).build()
        );

        // Act
        LocalDate lastInstallmentDate = paymentPlanGenerator.addLastInstallmentDate(installments);

        // Assert
        assertEquals(LocalDate.of(2025, 3, 1), lastInstallmentDate);
    }

    @Test
    void testAddLastInstallmentDateWithEmptyList() {
        // Act
        LocalDate lastInstallmentDate = paymentPlanGenerator.addLastInstallmentDate(List.of());

        // Assert
        assertNull(lastInstallmentDate);
    }
}