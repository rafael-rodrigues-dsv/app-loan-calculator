package br.com.devio.component.domain.model;

import br.com.devio.component.domain.enumeration.CalculationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanModel {
    private CalculationTypeEnum calculationType;
    private PricingModel pricing;
    private Integer installmentQuantity;
    private BigDecimal amount;
    private LocalDate contractDate;
    private LocalDate firstInstallmentDate;
    private InsuranceModel insurance;
    private FeeModel fee;
    private FinancialOperationalTaxModel financialOperationalTax;
}
