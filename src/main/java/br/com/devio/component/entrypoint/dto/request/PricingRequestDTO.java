package br.com.devio.component.entrypoint.dto.request;

import br.com.devio.component.domain.enumeration.ModalityTypeEnum;
import br.com.devio.component.domain.enumeration.PeriodTypeEnum;
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
public class PricingRequestDTO {
    private ModalityTypeEnum modalityType;
    private PeriodTypeEnum periodType;
    private BigDecimal interestRate;
    private List<BenchmarkRequestDTO> benchmarks;
}
