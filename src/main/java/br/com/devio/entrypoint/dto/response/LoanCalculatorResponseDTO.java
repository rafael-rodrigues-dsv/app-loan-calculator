package br.com.devio.entrypoint.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanCalculatorResponseDTO {
    private BigDecimal requestedAmount;
    private LocalDate contractDate;
    private LocalDate firstInstallmentDate;
    private LocalDate lastInstallmentDate;
    private BigDecimal monthlyInterestRate;
    private BigDecimal annualInterestRate;
    private InsuranceResponseDTO insurance;
    private FeeResponseDTO fee;
    private TaxResponseDTO tax;
    private BigDecimal totalFinancedAmount;
    private BigDecimal totalGrantedAmount;
    private BigDecimal totalLoanAmount;
    private List<InstallmentResponseDTO> installments;
}
