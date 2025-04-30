package br.com.devio.component.domain.calculator.component.template;

import br.com.devio.component.domain.calculator.model.LoanModel;
import br.com.devio.component.domain.calculator.model.PaymentPlanModel;


public abstract class PaymentPlanGeneratorTemplate {
    public final PaymentPlanModel generate(LoanModel loanModel) {
        return generatePaymentPlan(loanModel);
    }

    public abstract PaymentPlanModel generatePaymentPlan(LoanModel loanModel);
}
