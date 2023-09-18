package br.com.example.domain.model;

import br.com.example.domain.enumeration.CalculationTypeEnum;
import lombok.*;

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
    private List<InsuranceModel> insurances;
    private List<FeeModel> fees;
    private List<TaxModel> taxes;
}
