package br.com.example.domain.model;

import br.com.example.domain.enumeration.PeriodTypeEnum;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BenchmarkModel {
    private String name;
    private PeriodTypeEnum periodType;
    private BigDecimal interestRate;
    private BigDecimal interestRateTotalComposition;
}
