package br.com.example.domain.service.generator;

import br.com.example.domain.model.LoanModel;
import br.com.example.domain.model.PaymentPlanModel;


public abstract class PaymentPlanGeneratorTemplate {
    public final PaymentPlanModel generate(LoanModel loanModel) {
        return generatePaymentPlan(loanModel);
    }

    public abstract PaymentPlanModel generatePaymentPlan(LoanModel loanModel);
}
