package br.com.devio.component.entrypoint.dto.request;

import br.com.devio.component.domain.calculator.enumeration.PeriodTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
