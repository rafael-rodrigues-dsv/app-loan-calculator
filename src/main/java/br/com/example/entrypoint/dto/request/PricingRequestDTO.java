package br.com.example.entrypoint.dto.request;

import br.com.example.domain.calculator.enumeration.ModalityTypeEnum;
import br.com.example.domain.calculator.enumeration.PeriodTypeEnum;
import br.com.example.domain.calculator.model.BenchmarkModel;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricingRequestDTO {
    private ModalityTypeEnum modalityType;
    private PeriodTypeEnum periodType;
    private BigDecimal interestRate;
    private List<BenchmarkRequestDTO> benchmarks;
}
