package br.com.devio.domain.model;

import br.com.devio.domain.enumeration.CalculationTypeEnum;
import lombok.*;

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
    private AmountModel requestedAmount;
    private LocalDate contractDate;
    private LocalDate firstInstallmentDate;
    private LocalDate lastInstallmentDate;
    private BigDecimal monthlyInterestRate;
    private BigDecimal annualInterestRate;
    private InsuranceModel insurance;
    private FeeModel fee;
    private TaxModel tax;
    private AmountModel totalFinancedAmount;
    private AmountModel totalGrantedAmount;
    private AmountModel totalLoanAmount;
    private List<InstalmentModel> instalments;
}
