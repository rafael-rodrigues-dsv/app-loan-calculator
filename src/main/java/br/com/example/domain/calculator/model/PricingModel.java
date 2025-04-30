package br.com.example.domain.calculator.model;

import br.com.example.domain.calculator.enumeration.ModalityTypeEnum;
import br.com.example.domain.calculator.enumeration.PeriodTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
