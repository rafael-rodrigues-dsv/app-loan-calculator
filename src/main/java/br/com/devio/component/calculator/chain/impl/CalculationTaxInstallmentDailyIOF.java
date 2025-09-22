package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.AmountModel;
import br.com.devio.domain.model.InstalmentModel;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 📅 Calculadora de IOF diário
 */
public class CalculationTaxInstallmentDailyIOF extends CalculatorEngine<InstalmentModel> {
    private AmountModel dailyFinancialOperationalTax;
    private AmountModel totalFinancedAmount;

    public CalculationTaxInstallmentDailyIOF(AmountModel dailyFinancialOperationalTax, AmountModel totalFinancedAmount) {
        this.dailyFinancialOperationalTax = dailyFinancialOperationalTax;
        this.totalFinancedAmount = totalFinancedAmount;
    }

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     *                    P × t × d
     * IOF Diário = ─────────────────    (ascii)
     *                      100
     * 
     * IOF Diário = (P × t × d) ÷ 100    (algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * P = Principal (valor financiado)
     * t = Taxa IOF diário (%)
     * d = Dias entre contrato e vencimento
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: (100.000 × 0,0041 × 30) ÷ 100 = R$ 12,30
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public InstalmentModel calculate(InstalmentModel currentInstallment) {
        if (currentInstallment == null) {
            throw new IllegalArgumentException("Current installment cannot be null");
        }
        
        BigDecimal totalDailyFinancialOperationalTax = BigDecimal.ZERO;

        if (!currentInstallment.getInstallmentNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)
                && Objects.nonNull(dailyFinancialOperationalTax)
                && Objects.nonNull(totalFinancedAmount)
                && Objects.nonNull(totalFinancedAmount.getAmount())) {
            totalDailyFinancialOperationalTax = totalFinancedAmount.getAmount()
                    .multiply(dailyFinancialOperationalTax.getAmount())
                    .multiply(BigDecimal.valueOf(currentInstallment.getContractDays()))
                    .divide(CalculationConstant.PERCENTAGE_DIVISOR_100)
                    .setScale(CalculationConstant.SCALE_4, CalculationConstant.ROUNDING_MODE);
        }

        currentInstallment.setTotalDailyFinancialOperationalTax(AmountModel.builder()
                .amount(totalDailyFinancialOperationalTax)
                .currency(CalculationConstant.DEFAULT_CURRENCY)
                .build());

        return currentInstallment;
    }
}