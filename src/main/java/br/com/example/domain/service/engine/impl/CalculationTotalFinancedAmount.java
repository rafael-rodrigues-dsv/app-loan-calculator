package br.com.example.domain.service.engine.impl;

import br.com.example.domain.enumeration.PaymentTypeEnum;
import br.com.example.domain.enumeration.TaxTypeEnum;
import br.com.example.domain.model.PaymentPlanModel;
import br.com.example.domain.service.engine.CalculatorEngine;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
                        .setScale(4, RoundingMode.HALF_EVEN));

        return paymentPlanModel;
    }
}
