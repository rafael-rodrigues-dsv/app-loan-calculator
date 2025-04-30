package br.com.devio.component.domain.calculator.model;

import br.com.devio.component.domain.calculator.enumeration.CalculationTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class LoanModelTest {

    private LoanModel loanModel;

    @BeforeEach
    void setUp() {
        loanModel = new LoanModel();
    }

    @Test
    void testNoArgsConstructor() {
        LoanModel model = new LoanModel();
        assertNotNull(model);
        assertNull(model.getCalculationType());
        assertNull(model.getPricing());
        assertNull(model.getInstallmentQuantity());
        assertNull(model.getAmount());
        assertNull(model.getContractDate());
        assertNull(model.getFirstInstallmentDate());
        assertNull(model.getInsurances());
        assertNull(model.getFees());
        assertNull(model.getTaxes());
    }

    @Test
    void testAllArgsConstructor() {
        PricingModel pricing = new PricingModel();
        InsuranceModel insurance = new InsuranceModel();
        FeeModel fee = new FeeModel();
        TaxModel tax = new TaxModel();

        LoanModel model = new LoanModel(
                CalculationTypeEnum.PRICE,
                pricing,
                12,
                BigDecimal.valueOf(10000.0),
                LocalDate.of(2025, 4, 30),
                LocalDate.of(2025, 5, 30),
                List.of(insurance),
                List.of(fee),
                List.of(tax)
        );

        assertEquals(CalculationTypeEnum.PRICE, model.getCalculationType());
        assertEquals(pricing, model.getPricing());
        assertEquals(12, model.getInstallmentQuantity());
        assertEquals(BigDecimal.valueOf(10000.0), model.getAmount());
        assertEquals(LocalDate.of(2025, 4, 30), model.getContractDate());
        assertEquals(LocalDate.of(2025, 5, 30), model.getFirstInstallmentDate());
        assertEquals(1, model.getInsurances().size());
        assertEquals(insurance, model.getInsurances().get(0));
        assertEquals(1, model.getFees().size());
        assertEquals(fee, model.getFees().get(0));
        assertEquals(1, model.getTaxes().size());
        assertEquals(tax, model.getTaxes().get(0));
    }

    @Test
    void testBuilder() {
        PricingModel pricing = new PricingModel();
        InsuranceModel insurance = new InsuranceModel();
        FeeModel fee = new FeeModel();
        TaxModel tax = new TaxModel();

        LoanModel model = LoanModel.builder()
                .calculationType(CalculationTypeEnum.PRICE)
                .pricing(pricing)
                .installmentQuantity(24)
                .amount(BigDecimal.valueOf(20000.0))
                .contractDate(LocalDate.of(2025, 6, 1))
                .firstInstallmentDate(LocalDate.of(2025, 7, 1))
                .insurances(List.of(insurance))
                .fees(List.of(fee))
                .taxes(List.of(tax))
                .build();

        assertEquals(CalculationTypeEnum.PRICE, model.getCalculationType());
        assertEquals(pricing, model.getPricing());
        assertEquals(24, model.getInstallmentQuantity());
        assertEquals(BigDecimal.valueOf(20000.0), model.getAmount());
        assertEquals(LocalDate.of(2025, 6, 1), model.getContractDate());
        assertEquals(LocalDate.of(2025, 7, 1), model.getFirstInstallmentDate());
        assertEquals(1, model.getInsurances().size());
        assertEquals(insurance, model.getInsurances().get(0));
        assertEquals(1, model.getFees().size());
        assertEquals(fee, model.getFees().get(0));
        assertEquals(1, model.getTaxes().size());
        assertEquals(tax, model.getTaxes().get(0));
    }

    @Test
    void testSettersAndGetters() {
        PricingModel pricing = new PricingModel();
        InsuranceModel insurance = new InsuranceModel();
        FeeModel fee = new FeeModel();
        TaxModel tax = new TaxModel();

        loanModel.setCalculationType(CalculationTypeEnum.PRICE);
        loanModel.setPricing(pricing);
        loanModel.setInstallmentQuantity(36);
        loanModel.setAmount(BigDecimal.valueOf(30000.0));
        loanModel.setContractDate(LocalDate.of(2025, 8, 1));
        loanModel.setFirstInstallmentDate(LocalDate.of(2025, 9, 1));
        loanModel.setInsurances(List.of(insurance));
        loanModel.setFees(List.of(fee));
        loanModel.setTaxes(List.of(tax));

        assertEquals(CalculationTypeEnum.PRICE, loanModel.getCalculationType());
        assertEquals(pricing, loanModel.getPricing());
        assertEquals(36, loanModel.getInstallmentQuantity());
        assertEquals(BigDecimal.valueOf(30000.0), loanModel.getAmount());
        assertEquals(LocalDate.of(2025, 8, 1), loanModel.getContractDate());
        assertEquals(LocalDate.of(2025, 9, 1), loanModel.getFirstInstallmentDate());
        assertEquals(1, loanModel.getInsurances().size());
        assertEquals(insurance, loanModel.getInsurances().get(0));
        assertEquals(1, loanModel.getFees().size());
        assertEquals(fee, loanModel.getFees().get(0));
        assertEquals(1, loanModel.getTaxes().size());
        assertEquals(tax, loanModel.getTaxes().get(0));
    }
}