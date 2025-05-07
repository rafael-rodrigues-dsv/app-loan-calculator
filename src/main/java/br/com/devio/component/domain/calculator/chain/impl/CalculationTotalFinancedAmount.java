package br.com.devio.component.domain.calculator.chain.impl;

import br.com.devio.component.domain.calculator.chain.CalculatorEngine;
import br.com.devio.component.domain.constant.CalculationConstant;
import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import br.com.devio.component.domain.enumeration.TaxTypeEnum;
import br.com.devio.component.domain.model.PaymentPlanModel;

import java.math.BigDecimal;


public class CalculationTotalFinancedAmount extends CalculatorEngine<PaymentPlanModel> {

    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        double totalInsurances = paymentPlanModel.getInsurances() != null && !paymentPlanModel.getInsurances().isEmpty()
                ? paymentPlanModel.getInsurances().stream()
                .filter(f -> f.getPaymentType().equals(PaymentTypeEnum.FINANCED))
                .mapToDouble(map -> map.getValue().doubleValue())
                .sum()
                : 0;

        double totalFees = paymentPlanModel.getFees() != null && !paymentPlanModel.getFees().isEmpty()
                ? paymentPlanModel.getFees().stream()
                .filter(f -> f.getPaymentType().equals(PaymentTypeEnum.FINANCED))
                .mapToDouble(map -> map.getValue().doubleValue())
                .sum()
                : 0;

        double totalTaxes = paymentPlanModel.getTaxes() != null && !paymentPlanModel.getTaxes().isEmpty()
                ? paymentPlanModel.getTaxes().stream()
                .filter(f -> f.getPaymentType().equals(PaymentTypeEnum.FINANCED) && f.getTaxType().equals(TaxTypeEnum.TOTAL_IOF))
                .mapToDouble(map -> map.getValue().doubleValue())
                .sum()
                : 0;

        paymentPlanModel.setTotalFinancedAmount(
                BigDecimal.valueOf(paymentPlanModel.getAmount().doubleValue()
                                + totalFees
                                + totalInsurances
                                + totalTaxes)
                        .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE));

        return paymentPlanModel;
    }
}
