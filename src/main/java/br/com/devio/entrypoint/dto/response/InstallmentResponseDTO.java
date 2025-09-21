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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
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
    private BigDecimal totalDailyFinancialOperationalTax;
    private BigDecimal totalAdditionalFinancialOperationalTax;
    private BigDecimal totalFinancialOperationalTax;
}
