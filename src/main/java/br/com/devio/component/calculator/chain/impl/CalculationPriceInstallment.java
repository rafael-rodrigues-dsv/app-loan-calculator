package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.component.calculator.chain.CalculatorEngineBuilder;
import br.com.devio.domain.model.InstallmentModel;
import br.com.devio.domain.model.PaymentPlanModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.devio.domain.constant.CalculationConstant.INSTALLMENT_NUMBER_INITIAL;

public class CalculationPriceInstallment extends CalculatorEngine<PaymentPlanModel> {

    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {
        List<InstallmentModel> installments = new ArrayList<>();
        final BigDecimal totalFinancedAmount = paymentPlanModel.getTotalFinancedAmount();
        final Integer installmentQuantity = paymentPlanModel.getInstallmentQuantity();

        paymentPlanModel.getInstallments().forEach(currentInstallment -> {
            if (currentInstallment.getInstallmentNumber().equals(INSTALLMENT_NUMBER_INITIAL)) {
                CalculatorEngine<InstallmentModel> chain = new CalculatorEngineBuilder<InstallmentModel>()
                        .add(new CalculationPriceInstallmentTotalPresentValue(totalFinancedAmount, installmentQuantity))
                        .add(new CalculationPriceInstallmentTotalBalanceAmount(totalFinancedAmount))
                        .build();

                currentInstallment = chain.calculate(currentInstallment, currentInstallment);
            } else {
                InstallmentModel beforeInstallment = installments.get(currentInstallment.getInstallmentNumber() - 1);

                CalculatorEngine<InstallmentModel> chain = new CalculatorEngineBuilder<InstallmentModel>()
                        .add(new CalculationPriceInstallmentTotalPresentValue(totalFinancedAmount, installmentQuantity))
                        .add(new CalculationPriceInstallmentTotalInterestAmount())
                        .add(new CalculationPriceInstallmentTotalInstallmentValue())
                        .add(new CalculationPriceInstallmentTotalAmortizationAmount())
                        .add(new CalculationPriceInstallmentTotalBalanceAmount(totalFinancedAmount))
                        .build();

                currentInstallment = chain.calculate(beforeInstallment, currentInstallment);
            }

            installments.add(currentInstallment);
        });

        paymentPlanModel.setInstallments(installments);

        return paymentPlanModel;
    }
}
