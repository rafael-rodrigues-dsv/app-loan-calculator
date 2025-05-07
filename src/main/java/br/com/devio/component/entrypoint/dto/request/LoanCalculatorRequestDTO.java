package br.com.devio.component.entrypoint.dto.request;

import br.com.devio.component.domain.enumeration.CalculationTypeEnum;
import br.com.devio.component.domain.model.FeeModel;
import br.com.devio.component.domain.model.FinancialOperationalTaxModel;
import br.com.devio.component.domain.model.InsuranceModel;
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
public class LoanCalculatorRequestDTO {
    private CalculationTypeEnum calculationType;
    private PricingRequestDTO pricing;
    private Integer installmentQuantity;
    private BigDecimal amount;
    private LocalDate contractDate;
    private LocalDate firstInstallmentDate;
    private LocalDate lastInstallmentDate;
    private InsuranceRequestDTO insurance;
    private FeeRequestDTO fee;
    private FinancialOperationalTaxRequestDTO financialOperationalTax;
}
