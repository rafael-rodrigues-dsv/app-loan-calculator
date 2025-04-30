package br.com.devio.component.domain.calculator.component.chain.impl;

import br.com.devio.component.domain.calculator.constant.CalculationConstant;
import br.com.devio.component.domain.calculator.enumeration.PaymentTypeEnum;
import br.com.devio.component.domain.calculator.enumeration.TaxTypeEnum;
import br.com.devio.component.domain.calculator.model.PaymentPlanModel;
import br.com.devio.component.domain.calculator.component.chain.CalculatorEngine;

import java.math.BigDecimal;

public class CalculationTotalFinancedAmount extends CalculatorEngine<PaymentPlanModel> {

    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        double totalInsurances = paymentPlanModel.getInsurances() != null && !paymentPlanModel.getInsurances().isEmpty()
                ? paymentPlanModel.getInsurances().stream()
                .filter(f -> f.getPaymentType().equals(PaymentTypeEnum.FINANCIADO))
                .mapToDouble(map -> map.getValue().doubleValue())
                .sum()
                : 0;

        double totalFees = paymentPlanModel.getFees() != null && !paymentPlanModel.getFees().isEmpty()
                ? paymentPlanModel.getFees().stream()
                .filter(f -> f.getPaymentType().equals(PaymentTypeEnum.FINANCIADO))
                .mapToDouble(map -> map.getValue().doubleValue())
                .sum()
                : 0;

        double totalTaxes = paymentPlanModel.getTaxes() != null && !paymentPlanModel.getTaxes().isEmpty()
                ? paymentPlanModel.getTaxes().stream()
                .filter(f -> f.getPaymentType().equals(PaymentTypeEnum.FINANCIADO) && f.getTaxType().equals(TaxTypeEnum.IOF_TOTAL))
                .mapToDouble(map -> map.getValue().doubleValue())
                .sum()
                : 0;

        paymentPlanModel.setTotalFinancedAmount(
                BigDecimal.valueOf(paymentPlanModel.getAmount().doubleValue()
                                + totalFees
                                + totalInsurances
                                + totalTaxes)
                        .setScale(CalculationConstant.SCALE, CalculationConstant.ROUNDING_MODE));

        return paymentPlanModel;
    }
}
