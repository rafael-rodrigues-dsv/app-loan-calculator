package br.com.devio.component.domain.calculator.factory;

import br.com.devio.component.domain.calculator.strategy.CalculationStrategy;
import br.com.devio.component.domain.calculator.strategy.impl.CalculationPriceStrategy;
import br.com.devio.component.domain.enumeration.CalculationTypeEnum;
import br.com.devio.component.domain.model.LoanModel;
import br.com.devio.component.domain.model.PaymentPlanModel;

public class CalculatorFactory {

    public static PaymentPlanModel calculate(LoanModel loanModel) {
        CalculationStrategy strategy = getStrategy(loanModel.getCalculationType());
        return strategy.calculate(loanModel);
    }

    private static CalculationStrategy getStrategy(CalculationTypeEnum calculationType) {
        if (calculationType.equals(CalculationTypeEnum.PRICE)) {
            return new CalculationPriceStrategy();
        }
        throw new UnsupportedOperationException("Calculation type not supported");
    }
}