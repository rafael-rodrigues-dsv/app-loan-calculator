package br.com.example.entrypoint.dto.request;

import br.com.example.domain.calculator.enumeration.PeriodTypeEnum;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BenchmarkRequestDTO {
    private String name;
    private PeriodTypeEnum periodType;
    private BigDecimal interestRate;
    private BigDecimal interestRateTotalComposition;
}
