package br.com.devio.component.calculator.template;

import br.com.devio.domain.model.LoanModel;
import br.com.devio.domain.model.PaymentPlanModel;


public abstract class PaymentPlanGeneratorTemplate {
    public final PaymentPlanModel generate(LoanModel loanModel) {
        return generatePaymentPlan(loanModel);
    }

    public abstract PaymentPlanModel generatePaymentPlan(LoanModel loanModel);
}
