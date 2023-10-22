package br.com.example.domain.calculator.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentModel {
    private Integer number;
    private LocalDate dueDate;
    private Integer contractDays;
    private Integer periodDays;
    private BigDecimal interestRate;
    private BigDecimal instalmentValue;
    private BigDecimal totalInterestAmount;
    private BigDecimal totalAmortizationAmount;
    private BigDecimal totalBalanceAmount;
}
