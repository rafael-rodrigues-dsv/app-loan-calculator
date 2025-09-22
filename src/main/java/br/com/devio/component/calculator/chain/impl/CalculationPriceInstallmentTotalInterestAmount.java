package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.AmountModel;
import br.com.devio.domain.model.InstallmentModel;

import java.math.BigDecimal;

/**
 * 💸 Calculadora de juros - sistema PRICE
 */
public class CalculationPriceInstallmentTotalInterestAmount extends CalculatorEngine<InstallmentModel> {

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     *                    r × S(i-1)
     * Juros(i) = ─────────────────    (ascii)
     *                      100
     * 
     * Juros(i) = (r × S(i-1)) ÷ 100    (algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * r = Taxa mensal (%)
     * S(i-1) = Saldo devedor da parcela anterior
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: (2 × 100.000) ÷ 100 = R$ 2.000,00
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        if (beforeInstallment == null || currentInstallment == null) {
            throw new IllegalArgumentException("Installment parameters cannot be null");
        }
        
        BigDecimal interestRate = currentInstallment.getInterestRate();

        BigDecimal totalInterestAmount = interestRate.multiply(beforeInstallment.getTotalBalanceAmount().getAmount())
                .divide(CalculationConstant.PERCENTAGE_DIVISOR_100)
                .setScale(CalculationConstant.SCALE_2, CalculationConstant.ROUNDING_MODE);

        currentInstallment.setTotalInterestAmount(AmountModel.builder()
                .amount(totalInterestAmount)
                .currency(CalculationConstant.DEFAULT_CURRENCY)
                .build());

        return currentInstallment;
    }
}
