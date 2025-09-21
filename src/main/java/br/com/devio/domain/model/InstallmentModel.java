package br.com.devio.domain.model;

import br.com.devio.domain.enumeration.PeriodTypeEnum;
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
    private BigDecimal totalPresentValue;
    private BigDecimal totalInstalmentValue;
    private BigDecimal totalInterestAmount;
    private BigDecimal totalAmortizationAmount;
    private BigDecimal totalBalanceAmount;
    private BigDecimal totalDailyFinancialOperationalTax;
    private BigDecimal totalAdditionalFinancialOperationalTax;
    private BigDecimal totalFinancialOperationalTax;
}
