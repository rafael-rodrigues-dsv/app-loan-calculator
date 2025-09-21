package br.com.devio.domain.model;

import br.com.devio.domain.enumeration.CalculationTypeEnum;
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
public class PaymentPlanModel {
    private CalculationTypeEnum calculationType;
    private Integer installmentQuantity;
    private BigDecimal requestedAmount;
    private LocalDate contractDate;
    private LocalDate firstInstallmentDate;
    private LocalDate lastInstallmentDate;
    private BigDecimal monthlyInterestRate;
    private BigDecimal annualInterestRate;
    private InsuranceModel insurance;
    private FeeModel fee;
    private TaxModel tax;
    private BigDecimal totalFinancedAmount;
    private BigDecimal totalGrantedAmount;
    private BigDecimal totalLoanAmount;
    private List<InstallmentModel> installments;
}
