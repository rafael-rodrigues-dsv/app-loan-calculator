package br.com.example.entrypoint.dto.response;

import br.com.example.domain.calculator.enumeration.PeriodTypeEnum;
import lombok.*;

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
