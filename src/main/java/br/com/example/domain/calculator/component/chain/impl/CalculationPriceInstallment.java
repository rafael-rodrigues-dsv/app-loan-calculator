package br.com.example.domain.calculator.component.chain.impl;

import br.com.example.domain.calculator.component.chain.CalculatorEngine;
import br.com.example.domain.calculator.component.chain.CalculatorEngineBuilder;
import br.com.example.domain.calculator.model.InstallmentModel;
import br.com.example.domain.calculator.model.PaymentPlanModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CalculationPriceInstallment extends CalculatorEngine<PaymentPlanModel> {

    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {
        List<InstallmentModel> installments = new ArrayList<>();
        final BigDecimal totalFinancedAmount = paymentPlanModel.getTotalFinancedAmount();
        final Integer installmentQuantity = paymentPlanModel.getInstallmentQuantity();

        paymentPlanModel.getInstallments().forEach(currentInstallment -> {
            if (currentInstallment.getNumber().equals(0)) {
                CalculatorEngine<InstallmentModel> chain = new CalculatorEngineBuilder<InstallmentModel>()
                        .add(new CalculationPriceInstallmentTotalBalanceAmount(totalFinancedAmount))
                        .build();

                currentInstallment = chain.calculate(currentInstallment, currentInstallment);
            } else {
                InstallmentModel beforeInstallment = installments.get(currentInstallment.getNumber() - 1);

                CalculatorEngine<InstallmentModel> chain = new CalculatorEngineBuilder<InstallmentModel>()
                        .add(new CalculationPriceInstallmentTotalInterestAmount())
                        .add(new CalculationPriceInstallmentTotalInstallmentValue(totalFinancedAmount, installmentQuantity))
                        .add(new CalculationPriceInstallmentTotalAmortizationAmount())
                        .add(new CalculationPriceInstallmentTotalBalanceAmount(totalFinancedAmount))
                        .build();

                currentInstallment = chain.calculate(beforeInstallment, currentInstallment);
            }

            installments.add(currentInstallment);
        });

        paymentPlanModel.setInstallments(installments
                .stream()
                .filter(f -> f.getNumber() != 0L)
                .toList());

        return paymentPlanModel;
    }
}
