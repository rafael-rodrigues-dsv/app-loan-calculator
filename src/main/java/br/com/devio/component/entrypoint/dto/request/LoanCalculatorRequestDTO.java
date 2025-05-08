package br.com.devio.component.entrypoint.dto.request;

import br.com.devio.component.domain.enumeration.CalculationTypeEnum;
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
public class LoanCalculatorRequestDTO {
    private CalculationTypeEnum calculationType;
    private Integer installmentQuantity;
    private BigDecimal requestedAmount;
    private LocalDate contractDate;
    private LocalDate firstInstallmentDate;
    private LocalDate lastInstallmentDate;
    private BigDecimal monthlyInterestRate;
    private InsuranceRequestDTO insurance;
    private FeeRequestDTO fee;
    private TaxRequestDTO tax;
}
