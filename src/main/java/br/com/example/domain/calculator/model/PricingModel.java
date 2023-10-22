package br.com.example.domain.calculator.model;

import br.com.example.domain.calculator.enumeration.PeriodTypeEnum;
import br.com.example.domain.calculator.enumeration.ModalityTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricingModel {
    private ModalityTypeEnum modalityType;
    private PeriodTypeEnum periodType;
    private BigDecimal interestRate;
    private List<BenchmarkModel> benchmarks;
}
