package br.com.example.domain.calculator.model;

import br.com.example.domain.calculator.enumeration.CalculationTypeEnum;
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
    private PricingModel pricing;
    private Integer installmentQuantity;
    private BigDecimal amount;
    private LocalDate contractDate;
    private LocalDate firstInstallmentDate;
    private LocalDate lastInstallmentDate;
    private List<InsuranceModel> insurances;
    private List<FeeModel> fees;
    private List<TaxModel> taxes;
    private BigDecimal totalFinancedAmount;
    private BigDecimal totalAmount;
    private List<InstallmentModel> installments;
}
