package br.com.example.entrypoint.dto.request;

import br.com.example.domain.calculator.enumeration.PeriodTypeEnum;
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
