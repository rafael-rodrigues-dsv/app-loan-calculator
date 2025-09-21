package br.com.devio.component.calculator.factory;

import br.com.devio.component.calculator.chain.impl.CalculationPrice;
import br.com.devio.component.calculator.template.PaymentPlanGenerator;
import br.com.devio.domain.enumeration.CalculationTypeEnum;
import br.com.devio.domain.model.LoanModel;
import br.com.devio.domain.model.PaymentPlanModel;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CalculatorFactory {

    private static final List<CalculationTypeEnum> VALID_CALCULATION_TYPES = List.of(CalculationTypeEnum.PRICE);

    public static PaymentPlanModel calculate(LoanModel loanModel) {
        return Optional.of(loanModel.getCalculationType())
            .filter(VALID_CALCULATION_TYPES::contains)
            .map(type -> new PaymentPlanGenerator().generate(loanModel))
            .filter(Objects::nonNull)
            .map(paymentPlan -> new CalculationPrice().calculate(paymentPlan))
            .orElseThrow(() -> new UnsupportedOperationException("Invalid calculation type"));
    }
}