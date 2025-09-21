package br.com.devio.domain.model;

import br.com.devio.domain.enumeration.CalculationTypeEnum;
import lombok.*;

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
    private AmountModel requestedAmount;
    private LocalDate contractDate;
    private LocalDate firstInstallmentDate;
    private BigDecimal monthlyInterestRate;
    private InsuranceModel insurance;
    private FeeModel fee;
    private TaxModel tax;
}
