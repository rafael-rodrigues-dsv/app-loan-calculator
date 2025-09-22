package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.component.calculator.chain.CalculatorEngineBuilder;
import br.com.devio.domain.model.AmountModel;
import br.com.devio.domain.model.InstalmentModel;
import br.com.devio.domain.model.PaymentPlanModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<InstalmentModel> installments = new ArrayList<>();
        final AmountModel totalFinancedAmount = Optional.ofNullable(paymentPlanModel.getTotalFinancedAmount())
                .orElse(AmountModel.builder().amount(BigDecimal.ZERO).currency("BRL").build());
        final Integer installmentQuantity = paymentPlanModel.getInstallmentQuantity();

        paymentPlanModel.getInstalments().forEach(currentInstallment -> {
            if (currentInstallment.getInstallmentNumber().equals(INSTALLMENT_NUMBER_INITIAL)) {
                CalculatorEngine<InstalmentModel> chain = new CalculatorEngineBuilder<InstalmentModel>()
                        .add(new CalculationPriceInstallmentTotalPresentValue(totalFinancedAmount, installmentQuantity))
                        .add(new CalculationPriceInstallmentTotalBalanceAmount(totalFinancedAmount))
                        .build();

                currentInstallment = chain.calculate(currentInstallment, currentInstallment);
            } else {
                InstalmentModel beforeInstallment = installments.get(currentInstallment.getInstallmentNumber() - 1);

                CalculatorEngine<InstalmentModel> chain = new CalculatorEngineBuilder<InstalmentModel>()
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

        paymentPlanModel.setInstalments(installments);

        return paymentPlanModel;
    }
}
