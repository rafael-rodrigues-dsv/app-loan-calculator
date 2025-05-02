package br.com.devio.component.domain.model;

import br.com.devio.component.domain.enumeration.CalculationTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentPlanModelTest {

    private PaymentPlanModel paymentPlanModel;

    @BeforeEach
    void setUp() {
        paymentPlanModel = new PaymentPlanModel();
    }

    @Test
    void testNoArgsConstructor() {
        PaymentPlanModel model = new PaymentPlanModel();
        assertNotNull(model);
        assertNull(model.getCalculationType());
        assertNull(model.getPricing());
        assertNull(model.getInstallmentQuantity());
        assertNull(model.getAmount());
        assertNull(model.getContractDate());
        assertNull(model.getFirstInstallmentDate());
        assertNull(model.getLastInstallmentDate());
        assertNull(model.getInsurances());
        assertNull(model.getFees());
        assertNull(model.getTaxes());
        assertNull(model.getTotalFinancedAmount());
        assertNull(model.getTotalLoanAmount());
        assertNull(model.getInstallments());
    }

    @Test
    void testAllArgsConstructor() {
        PricingModel pricing = new PricingModel();
        InsuranceModel insurance = new InsuranceModel();
        FeeModel fee = new FeeModel();
        TaxModel tax = new TaxModel();
        InstallmentModel installment = new InstallmentModel();

        PaymentPlanModel model = new PaymentPlanModel(
                CalculationTypeEnum.PRICE,
                pricing,
                12,
                BigDecimal.valueOf(10000.0),
                LocalDate.of(2025, 4, 30),
                LocalDate.of(2025, 5, 30),
                LocalDate.of(2026, 5, 30),
                List.of(insurance),
                List.of(fee),
                List.of(tax),
                BigDecimal.valueOf(12000.0),
                BigDecimal.valueOf(15000.0),
                List.of(installment)
        );

        assertEquals(CalculationTypeEnum.PRICE, model.getCalculationType());
        assertEquals(pricing, model.getPricing());
        assertEquals(12, model.getInstallmentQuantity());
        assertEquals(BigDecimal.valueOf(10000.0), model.getAmount());
        assertEquals(LocalDate.of(2025, 4, 30), model.getContractDate());
        assertEquals(LocalDate.of(2025, 5, 30), model.getFirstInstallmentDate());
        assertEquals(LocalDate.of(2026, 5, 30), model.getLastInstallmentDate());
        assertEquals(1, model.getInsurances().size());
        assertEquals(insurance, model.getInsurances().get(0));
        assertEquals(1, model.getFees().size());
        assertEquals(fee, model.getFees().get(0));
        assertEquals(1, model.getTaxes().size());
        assertEquals(tax, model.getTaxes().get(0));
        assertEquals(BigDecimal.valueOf(12000.0), model.getTotalFinancedAmount());
        assertEquals(BigDecimal.valueOf(15000.0), model.getTotalLoanAmount());
        assertEquals(1, model.getInstallments().size());
        assertEquals(installment, model.getInstallments().get(0));
    }

    @Test
    void testBuilder() {
        PricingModel pricing = new PricingModel();
        InsuranceModel insurance = new InsuranceModel();
        FeeModel fee = new FeeModel();
        TaxModel tax = new TaxModel();
        InstallmentModel installment = new InstallmentModel();

        PaymentPlanModel model = PaymentPlanModel.builder()
                .calculationType(CalculationTypeEnum.PRICE)
                .pricing(pricing)
                .installmentQuantity(24)
                .amount(BigDecimal.valueOf(20000.0))
                .contractDate(LocalDate.of(2025, 6, 1))
                .firstInstallmentDate(LocalDate.of(2025, 7, 1))
                .lastInstallmentDate(LocalDate.of(2027, 6, 1))
                .insurances(List.of(insurance))
                .fees(List.of(fee))
                .taxes(List.of(tax))
                .totalFinancedAmount(BigDecimal.valueOf(25000.0))
                .totalLoanAmount(BigDecimal.valueOf(30000.0))
                .installments(List.of(installment))
                .build();

        assertEquals(CalculationTypeEnum.PRICE, model.getCalculationType());
        assertEquals(pricing, model.getPricing());
        assertEquals(24, model.getInstallmentQuantity());
        assertEquals(BigDecimal.valueOf(20000.0), model.getAmount());
        assertEquals(LocalDate.of(2025, 6, 1), model.getContractDate());
        assertEquals(LocalDate.of(2025, 7, 1), model.getFirstInstallmentDate());
        assertEquals(LocalDate.of(2027, 6, 1), model.getLastInstallmentDate());
        assertEquals(1, model.getInsurances().size());
        assertEquals(insurance, model.getInsurances().get(0));
        assertEquals(1, model.getFees().size());
        assertEquals(fee, model.getFees().get(0));
        assertEquals(1, model.getTaxes().size());
        assertEquals(tax, model.getTaxes().get(0));
        assertEquals(BigDecimal.valueOf(25000.0), model.getTotalFinancedAmount());
        assertEquals(BigDecimal.valueOf(30000.0), model.getTotalLoanAmount());
        assertEquals(1, model.getInstallments().size());
        assertEquals(installment, model.getInstallments().get(0));
    }

    @Test
    void testSettersAndGetters() {
        PricingModel pricing = new PricingModel();
        InsuranceModel insurance = new InsuranceModel();
        FeeModel fee = new FeeModel();
        TaxModel tax = new TaxModel();
        InstallmentModel installment = new InstallmentModel();

        paymentPlanModel.setCalculationType(CalculationTypeEnum.PRICE);
        paymentPlanModel.setPricing(pricing);
        paymentPlanModel.setInstallmentQuantity(36);
        paymentPlanModel.setAmount(BigDecimal.valueOf(30000.0));
        paymentPlanModel.setContractDate(LocalDate.of(2025, 8, 1));
        paymentPlanModel.setFirstInstallmentDate(LocalDate.of(2025, 9, 1));
        paymentPlanModel.setLastInstallmentDate(LocalDate.of(2028, 8, 1));
        paymentPlanModel.setInsurances(List.of(insurance));
        paymentPlanModel.setFees(List.of(fee));
        paymentPlanModel.setTaxes(List.of(tax));
        paymentPlanModel.setTotalFinancedAmount(BigDecimal.valueOf(35000.0));
        paymentPlanModel.setTotalLoanAmount(BigDecimal.valueOf(40000.0));
        paymentPlanModel.setInstallments(List.of(installment));

        assertEquals(CalculationTypeEnum.PRICE, paymentPlanModel.getCalculationType());
        assertEquals(pricing, paymentPlanModel.getPricing());
        assertEquals(36, paymentPlanModel.getInstallmentQuantity());
        assertEquals(BigDecimal.valueOf(30000.0), paymentPlanModel.getAmount());
        assertEquals(LocalDate.of(2025, 8, 1), paymentPlanModel.getContractDate());
        assertEquals(LocalDate.of(2025, 9, 1), paymentPlanModel.getFirstInstallmentDate());
        assertEquals(LocalDate.of(2028, 8, 1), paymentPlanModel.getLastInstallmentDate());
        assertEquals(1, paymentPlanModel.getInsurances().size());
        assertEquals(insurance, paymentPlanModel.getInsurances().get(0));
        assertEquals(1, paymentPlanModel.getFees().size());
        assertEquals(fee, paymentPlanModel.getFees().get(0));
        assertEquals(1, paymentPlanModel.getTaxes().size());
        assertEquals(tax, paymentPlanModel.getTaxes().get(0));
        assertEquals(BigDecimal.valueOf(35000.0), paymentPlanModel.getTotalFinancedAmount());
        assertEquals(BigDecimal.valueOf(40000.0), paymentPlanModel.getTotalLoanAmount());
        assertEquals(1, paymentPlanModel.getInstallments().size());
        assertEquals(installment, paymentPlanModel.getInstallments().get(0));
    }
}