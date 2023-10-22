package br.com.example.domain.calculator.component.template;

import br.com.example.domain.calculator.model.LoanModel;
import br.com.example.domain.calculator.model.PaymentPlanModel;


public abstract class PaymentPlanGeneratorTemplate {
    public final PaymentPlanModel generate(LoanModel loanModel) {
        return generatePaymentPlan(loanModel);
    }

    public abstract PaymentPlanModel generatePaymentPlan(LoanModel loanModel);
}
