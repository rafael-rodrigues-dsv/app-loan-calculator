package br.com.example.entrypoint.dto.request;

import br.com.example.domain.calculator.enumeration.CalculationTypeEnum;
import br.com.example.domain.calculator.model.*;
import lombok.*;

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
    private List<InsuranceRequestDTO> insurances;
    private List<FeeRequestDTO> fees;
    private List<TaxRequestDTO> taxes;
}
