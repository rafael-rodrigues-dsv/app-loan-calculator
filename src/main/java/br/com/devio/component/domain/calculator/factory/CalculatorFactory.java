package br.com.devio.component.domain.calculator.factory;

import br.com.devio.component.domain.calculator.chain.impl.CalculationPrice;
import br.com.devio.component.domain.calculator.template.PaymentPlanGenerator;
import br.com.devio.component.domain.enumeration.CalculationTypeEnum;
import br.com.devio.component.domain.model.LoanModel;
import br.com.devio.component.domain.model.PaymentPlanModel;

import java.util.Objects;

public class CalculatorFactory {

    public static PaymentPlanModel calculate(LoanModel loanModel) {
        CalculationTypeEnum calculationType = loanModel.getCalculationType();

        if (calculationType.equals(CalculationTypeEnum.PRICE)) {
            PaymentPlanModel paymentPlanModel = new PaymentPlanGenerator().generate(loanModel);

            if (Objects.nonNull(paymentPlanModel)) {
                return new CalculationPrice().calculate(paymentPlanModel);
            }
        }

        throw new UnsupportedOperationException("Invalid calculation type");
    }
}