package br.com.example.domain.service.calculator;

import br.com.example.domain.enumeration.CalculationTypeEnum;
import br.com.example.domain.model.LoanModel;
import br.com.example.domain.model.PaymentPlanModel;
import br.com.example.domain.service.calculator.impl.CalculatorPrice;
import br.com.example.domain.service.generator.PaymentPlanGenerator;

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
