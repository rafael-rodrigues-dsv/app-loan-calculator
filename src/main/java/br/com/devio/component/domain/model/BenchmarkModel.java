package br.com.devio.component.domain.model;

import br.com.devio.component.domain.enumeration.PeriodTypeEnum;
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
public class BenchmarkModel {
    private String name;
    private PeriodTypeEnum periodType;
    private BigDecimal interestRate;
    private BigDecimal interestRateTotalComposition;
}
