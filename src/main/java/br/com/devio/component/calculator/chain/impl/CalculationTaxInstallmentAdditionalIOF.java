package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.AmountModel;
import br.com.devio.domain.model.InstalmentModel;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 💰 Calculadora de IOF adicional
 */
public class CalculationTaxInstallmentAdditionalIOF extends CalculatorEngine<InstalmentModel> {
    private AmountModel additionalFinancialOperationalTax;
    private AmountModel totalFinancedAmount;

    public CalculationTaxInstallmentAdditionalIOF(AmountModel additionalFinancialOperationalTax, AmountModel totalFinancedAmount) {
        this.additionalFinancialOperationalTax = additionalFinancialOperationalTax;
        this.totalFinancedAmount = totalFinancedAmount;
    }

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     *                      P × t
     * IOF Adicional = ─────────────    (ascii)
     *                      100
     * 
     * IOF Adicional = (P × t) ÷ 100    (algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * P = Principal (valor financiado)
     * t = Taxa IOF adicional (0,38%)
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: (100.000 × 0,38) ÷ 100 = R$ 380,00
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public InstalmentModel calculate(InstalmentModel currentInstallment) {
        if (currentInstallment == null) {
            throw new IllegalArgumentException("Current installment cannot be null");
        }
        
        BigDecimal totalAdditionalFinancialOperationalTax = BigDecimal.ZERO;

        if (!currentInstallment.getInstallmentNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)
                && Objects.nonNull(additionalFinancialOperationalTax)
                && Objects.nonNull(totalFinancedAmount)
                && Objects.nonNull(totalFinancedAmount.getAmount())) {
            totalAdditionalFinancialOperationalTax = totalFinancedAmount.getAmount()
                    .multiply(additionalFinancialOperationalTax.getAmount())
                    .divide(CalculationConstant.PERCENTAGE_DIVISOR_100)
                    .setScale(CalculationConstant.SCALE_4, CalculationConstant.ROUNDING_MODE);
        }

        currentInstallment.setTotalAdditionalFinancialOperationalTax(AmountModel.builder()
                .amount(totalAdditionalFinancialOperationalTax)
                .currency(CalculationConstant.DEFAULT_CURRENCY)
                .build());

        return currentInstallment;
    }
}