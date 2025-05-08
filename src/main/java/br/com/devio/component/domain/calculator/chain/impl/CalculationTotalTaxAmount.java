package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.constant.CalculationConstant;
import br.com.devio.component.domain.model.TaxModel;
import br.com.devio.component.domain.model.PaymentPlanModel;

import java.math.BigDecimal;
import java.util.Objects;

import static br.com.devio.component.domain.constant.CalculationConstant.INSTALLMENT_NUMBER_INITIAL;

public class CalculationTotalTaxAmount extends CalculatorEngine<PaymentPlanModel> {

    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {
        TaxModel tax = paymentPlanModel.getTax();

        if (Objects.nonNull(tax)) {
            double totalFinancialOperationalTax = paymentPlanModel.getInstallments() != null && !paymentPlanModel.getInstallments().isEmpty()
                    ? paymentPlanModel.getInstallments().stream()
                    .filter(f -> f.getNumber() != INSTALLMENT_NUMBER_INITIAL && Objects.nonNull(f.getTotalFinancialOperationalTax()))
                    .reduce((first, second) -> second) // Obtém a última parcela
                    .map(last -> last.getTotalFinancialOperationalTax().doubleValue())
                    .orElse(0.0)
                    : 0;

            tax.setTotalAmount(
                    BigDecimal.valueOf(totalFinancialOperationalTax)
                            .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE));

            paymentPlanModel.setTax(tax);
        }

        return paymentPlanModel;
    }
}
