package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.constant.CalculationConstant;
import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import br.com.devio.component.domain.enumeration.TaxTypeEnum;
import br.com.devio.component.domain.model.PaymentPlanModel;

import java.math.BigDecimal;

public class CalculationTotalLoanAmount extends CalculatorEngine<PaymentPlanModel> {

    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        double totalLoanAmount = paymentPlanModel.getInstallments() != null && !paymentPlanModel.getInstallments().isEmpty()
                ? paymentPlanModel.getInstallments().stream()
                .mapToDouble(map -> map.getTotalInstalmentValue().doubleValue())
                .sum()
                : 0;

        paymentPlanModel.setTotalLoanAmount(
                BigDecimal.valueOf(totalLoanAmount)
                        .setScale(CalculationConstant.SCALE, CalculationConstant.ROUNDING_MODE));

        return paymentPlanModel;
    }
}
