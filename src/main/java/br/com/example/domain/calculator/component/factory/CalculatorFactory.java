package br.com.example.domain.calculator.component.factory;

import br.com.example.domain.calculator.enumeration.CalculationTypeEnum;
import br.com.example.domain.calculator.model.LoanModel;
import br.com.example.domain.calculator.model.PaymentPlanModel;
import br.com.example.domain.calculator.component.template.PaymentPlanGenerator;
import br.com.example.domain.calculator.service.calculator.impl.CalculatorPrice;

public class CalculatorFactory {
    public static PaymentPlanModel calculate(LoanModel loanModel) {
        if (loanModel.getCalculationType().equals(CalculationTypeEnum.PRICE)) {
            PaymentPlanModel paymentPlanModel = new PaymentPlanGenerator().generate(loanModel);

            if (paymentPlanModel != null) {
                return new CalculatorPrice().calculate(paymentPlanModel);
            }
        }

        throw new UnsupportedOperationException();
    }
}
