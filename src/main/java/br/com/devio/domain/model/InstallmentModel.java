package br.com.devio.domain.model;

import br.com.devio.domain.enumeration.PeriodTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstallmentModel {
    private Integer installmentNumber;
    private LocalDate dueDate;
    private Integer contractDays;
    private Integer periodDays;
    private BigDecimal interestRate;
    private PeriodTypeEnum interestRateType;
    private AmountModel totalPresentValue;
    private AmountModel totalInstalmentValue;
    private AmountModel totalInterestAmount;
    private AmountModel totalAmortizationAmount;
    private AmountModel totalBalanceAmount;
    private AmountModel totalDailyFinancialOperationalTax;
    private AmountModel totalAdditionalFinancialOperationalTax;
    private AmountModel totalFinancialOperationalTax;
}
