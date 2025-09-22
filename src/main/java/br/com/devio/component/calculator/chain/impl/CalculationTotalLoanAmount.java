package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.AmountModel;
import br.com.devio.domain.model.PaymentPlanModel;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.devio.domain.constant.CalculationConstant.INSTALLMENT_NUMBER_INITIAL;

/**
 * 📊 Calculadora do valor total do empréstimo
 */
public class CalculationTotalLoanAmount extends CalculatorEngine<PaymentPlanModel> {

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     *        n
     * TE = Σ PMT(i)    (ascii)
     *      i=1
     * 
     * TE = PMT(1) + PMT(2) + ... + PMT(n)    (algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * Σ PMT(i) = soma de todas as parcelas (i=1 até n)
     * TE = Total do Empréstimo
     * n = Número de parcelas
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: 24 × 2.500,00 = R$ 60.000,00
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        BigDecimal totalLoanAmount = paymentPlanModel.getInstalments() != null && !paymentPlanModel.getInstalments().isEmpty()
                ? paymentPlanModel.getInstalments().stream()
                .filter(f -> f.getInstallmentNumber() != INSTALLMENT_NUMBER_INITIAL)
                .map(installment -> Optional.ofNullable(installment.getTotalInstalmentValue())
                        .map(AmountModel::getAmount)
                        .orElse(BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                : BigDecimal.ZERO;

        paymentPlanModel.setTotalLoanAmount(AmountModel.builder()
                .amount(totalLoanAmount.setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE))
                .currency(CalculationConstant.DEFAULT_CURRENCY)
                .build());

        return paymentPlanModel;
    }
}
