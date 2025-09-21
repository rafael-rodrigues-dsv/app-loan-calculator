package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.component.calculator.chain.CalculatorEngineBuilder;
import br.com.devio.domain.model.InstallmentModel;
import br.com.devio.domain.model.PaymentPlanModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static br.com.devio.domain.constant.CalculationConstant.INSTALLMENT_NUMBER_INITIAL;

/**
 * 🧮 Gerador de parcelas do sistema PRICE
 */
public class CalculationPriceInstallment extends CalculatorEngine<PaymentPlanModel> {

    /**
     * ═══════════════════════════════════════════════════════════════
     * 🧮 SEQUÊNCIA DE CÁLCULOS POR PARCELA
     * ═══════════════════════════════════════════════════════════════
     * Parcela 0: PMT = (P × r) ÷ (1 - (1+r)^-n), S(0) = P
     * Parcelas 1-N: J(i) = (r × S(i-1)) ÷ 100, A(i) = PMT - J(i), S(i) = S(i-1) - A(i)
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: 100.000, 2% a.m., 60x → PMT = 2.625,22 (fixo)
     * ═══════════════════════════════════════════════════════════════
     */
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
