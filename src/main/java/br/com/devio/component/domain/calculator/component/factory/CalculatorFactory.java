package br.com.devio.component.domain.calculator.component.factory;

import br.com.devio.component.domain.calculator.component.template.PaymentPlanGenerator;
import br.com.devio.component.domain.calculator.enumeration.CalculationTypeEnum;
import br.com.devio.component.domain.calculator.model.LoanModel;
import br.com.devio.component.domain.calculator.model.PaymentPlanModel;
import br.com.devio.component.domain.calculator.service.impl.CalculatorPriceServiceImpl;

public class CalculatorFactory {
    public static PaymentPlanModel calculate(LoanModel loanModel) {
        if (loanModel.getCalculationType().equals(CalculationTypeEnum.PRICE)) {
            PaymentPlanModel paymentPlanModel = new PaymentPlanGenerator().generate(loanModel);

            if (paymentPlanModel != null) {
                return new CalculatorPriceServiceImpl().calculate(paymentPlanModel);
            }
        }

        throw new UnsupportedOperationException();
    }
}
