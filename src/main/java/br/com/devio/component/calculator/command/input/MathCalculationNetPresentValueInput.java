package br.com.devio.component.calculator.command.input;

import br.com.devio.domain.model.InstalmentModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MathCalculationNetPresentValueInput {
    private final List<InstalmentModel> installments;
    private final BigDecimal interestRate;
}
