package br.com.example.domain.calculator.model;

import br.com.example.domain.calculator.enumeration.PeriodTypeEnum;
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
public class InstallmentModel {
    private Integer number;
    private LocalDate dueDate;
    private Integer contractDays;
    private Integer periodDays;
    private BigDecimal interestRate;
    private PeriodTypeEnum interestRateType;
    private BigDecimal totalInstalmentValue;
    private BigDecimal totalInterestAmount;
    private BigDecimal totalAmortizationAmount;
    private BigDecimal totalBalanceAmount;
}
