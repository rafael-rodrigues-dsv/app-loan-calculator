package br.com.devio.domain.model;

import br.com.devio.domain.enumeration.PeriodTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dados da parcela")
public class InstallmentModel {
    @Schema(description = "Número da parcela", example = "1")
    private Integer number;
    
    @Schema(description = "Data de vencimento", example = "2024-02-15")
    private LocalDate dueDate;
    
    @Schema(description = "Dias do contrato")
    private Integer contractDays;
    
    @Schema(description = "Dias do período")
    private Integer periodDays;
    
    @Schema(description = "Taxa de juros")
    private BigDecimal interestRate;
    
    @Schema(description = "Tipo da taxa de juros")
    private PeriodTypeEnum interestRateType;
    
    @Schema(description = "Valor presente", example = "2123.24")
    private BigDecimal totalPresentValue;
    
    @Schema(description = "Valor da parcela", example = "2176.32")
    private BigDecimal totalInstalmentValue;
    
    @Schema(description = "Valor dos juros", example = "1300.00")
    private BigDecimal totalInterestAmount;
    
    @Schema(description = "Valor da amortização", example = "876.32")
    private BigDecimal totalAmortizationAmount;
    
    @Schema(description = "Saldo devedor", example = "51123.68")
    private BigDecimal totalBalanceAmount;
    
    @Schema(description = "IOF diário")
    private BigDecimal totalDailyFinancialOperationalTax;
    
    @Schema(description = "IOF adicional")
    private BigDecimal totalAdditionalFinancialOperationalTax;
    
    @Schema(description = "IOF total")
    private BigDecimal totalFinancialOperationalTax;
}
