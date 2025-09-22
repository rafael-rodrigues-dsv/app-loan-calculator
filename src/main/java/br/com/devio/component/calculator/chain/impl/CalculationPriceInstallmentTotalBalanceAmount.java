package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.AmountModel;
import br.com.devio.domain.model.InstallmentModel;

import java.math.BigDecimal;

/**
 * 📊 Calculadora de saldo devedor - sistema PRICE
 */
public class CalculationPriceInstallmentTotalBalanceAmount extends CalculatorEngine<InstallmentModel> {

    private static AmountModel totalFinancedAmount;

    public CalculationPriceInstallmentTotalBalanceAmount(AmountModel totalFinancedAmount) {
        this.totalFinancedAmount = totalFinancedAmount;
    }

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     * S(i) = S(i-1) - A(i)    (ascii e algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * S(i) = Saldo devedor da parcela i
     * S(i-1) = Saldo devedor da parcela anterior
     * A(i) = Amortização da parcela i
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: 100.000 - 1.325,22 = R$ 98.674,78
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        BigDecimal totalBalanceAmount;

        if (currentInstallment.getInstallmentNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)) {
            totalBalanceAmount = totalFinancedAmount.getAmount();
        } else {
            totalBalanceAmount = beforeInstallment.getTotalBalanceAmount().getAmount()
                    .subtract(currentInstallment.getTotalAmortizationAmount().getAmount())
                    .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE);

            if (totalBalanceAmount.compareTo(BigDecimal.ZERO) < 0) {
                totalBalanceAmount = BigDecimal.ZERO;
            }
        }

        currentInstallment.setTotalBalanceAmount(AmountModel.builder()
                .amount(totalBalanceAmount)
                .currency(CalculationConstant.DEFAULT_CURRENCY)
                .build());

        return currentInstallment;
    }
}
