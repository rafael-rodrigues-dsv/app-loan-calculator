package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.component.calculator.chain.CalculatorEngineBuilder;
import br.com.devio.domain.model.InstallmentModel;
import br.com.devio.domain.model.TaxModel;
import br.com.devio.domain.model.PaymentPlanModel;

import java.util.List;
import java.util.Objects;

import static br.com.devio.domain.constant.CalculationConstant.INSTALLMENT_NUMBER_INITIAL;

public class CalculationPrice {
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        CalculatorEngine<PaymentPlanModel> chain = new CalculatorEngineBuilder<PaymentPlanModel>()
                .add(new CalculationTotalFinancedAmount())
                .add(new CalculationPriceInstallment())
                .addIf(this::hasFinancialOperationalTax,
                        () -> new CalculationTaxInstallment(),
                        () -> new CalculationTotalTaxAmount(),
                        () -> new CalculationTotalFinancedAmount(),
                        () -> new CalculationPriceInstallment())
                .add(new CalculationTotalGrantedAmount())
                .add(new CalculationTotalLoanAmount())
                .build();

        paymentPlanModel = chain.calculate(paymentPlanModel);
        paymentPlanModel.setInstallments(filterValidInstallments(paymentPlanModel.getInstallments()));

        return paymentPlanModel;
    }

    private boolean hasFinancialOperationalTax(PaymentPlanModel model) {
        TaxModel financialOperationalTax = model.getTax();
        return Objects.nonNull(financialOperationalTax)
                && Objects.nonNull(financialOperationalTax.getDailyFinancialOperationalTax())
                && Objects.nonNull(financialOperationalTax.getAdditionalFinancialOperationalTax());
    }

    private List<InstallmentModel> filterValidInstallments(List<InstallmentModel> installments) {
        if (Objects.nonNull(installments) && !installments.isEmpty()) {
            return installments.stream()
                    .filter(f -> f.getInstallmentNumber() != INSTALLMENT_NUMBER_INITIAL)
                    .toList();
        }
        return installments;
    }
}

