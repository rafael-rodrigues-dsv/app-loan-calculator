package br.com.example.entrypoint.dto.response;

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
public class InstallmentResponseDTO {
    private Integer number;
    private LocalDate dueDate;
    private BigDecimal totalInstalmentValue;
    private BigDecimal totalInterestAmount;
    private BigDecimal totalAmortizationAmount;
    private BigDecimal totalBalanceAmount;
}
