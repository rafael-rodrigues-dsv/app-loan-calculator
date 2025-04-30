package br.com.example.domain.calculator.component.chain.impl;

import br.com.example.domain.calculator.component.chain.CalculatorEngine;
import br.com.example.domain.calculator.enumeration.PaymentTypeEnum;
import br.com.example.domain.calculator.enumeration.TaxTypeEnum;
import br.com.example.domain.calculator.model.PaymentPlanModel;

import java.math.BigDecimal;

import static br.com.example.domain.calculator.constant.CalculationConstant.ROUNDING_MODE;
import static br.com.example.domain.calculator.constant.CalculationConstant.SCALE;

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
                        .setScale(SCALE, ROUNDING_MODE));

        return paymentPlanModel;
    }
}
