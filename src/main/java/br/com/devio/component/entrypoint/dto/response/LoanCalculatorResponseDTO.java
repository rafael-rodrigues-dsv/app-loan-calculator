package br.com.devio.component.entrypoint.dto.response;

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
public class LoanCalculatorResponseDTO {
    private BigDecimal amount;
    private LocalDate contractDate;
    private LocalDate firstInstallmentDate;
    private LocalDate lastInstallmentDate;
    private BigDecimal totalFinancedAmount;
    private BigDecimal totalLoanAmount;
    private List<InstallmentResponseDTO> installments;
}
