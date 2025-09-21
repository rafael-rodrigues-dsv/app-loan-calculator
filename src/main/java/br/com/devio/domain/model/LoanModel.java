package br.com.devio.domain.model;

import br.com.devio.domain.enumeration.CalculationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanModel {
    private CalculationTypeEnum calculationType;
    private Integer installmentQuantity;
    private BigDecimal requestedAmount;
    private LocalDate contractDate;
    private LocalDate firstInstallmentDate;
    private BigDecimal monthlyInterestRate;
    private InsuranceModel insurance;
    private FeeModel fee;
    private TaxModel tax;
}
