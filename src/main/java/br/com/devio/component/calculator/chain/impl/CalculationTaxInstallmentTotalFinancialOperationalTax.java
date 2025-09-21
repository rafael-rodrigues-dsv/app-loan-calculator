package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.InstallmentModel;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * ➕ Totalizador de IOF
 */
public class CalculationTaxInstallmentTotalFinancialOperationalTax extends CalculatorEngine<InstallmentModel> {

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     * IOF Total = IOFᴅ + IOFᴀ    (ascii e algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * IOFᴅ = IOF diário
     * IOFᴀ = IOF adicional
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: 12,30 + 380,00 = R$ 392,30
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public InstallmentModel calculate(InstallmentModel currentInstallment) {
        BigDecimal totalFinancialOperationalTax = BigDecimal.ZERO;

        if (!currentInstallment.getInstallmentNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)) {
            totalFinancialOperationalTax = Objects.nonNull(currentInstallment.getTotalDailyFinancialOperationalTax())
                    && Objects.nonNull(currentInstallment.getTotalAdditionalFinancialOperationalTax()) ?
                    currentInstallment.getTotalDailyFinancialOperationalTax()
                            .add(currentInstallment.getTotalAdditionalFinancialOperationalTax())
                            .setScale(CalculationConstant.SCALE_4, CalculationConstant.ROUNDING_MODE)
                    : BigDecimal.ZERO;
        }

        currentInstallment.setTotalFinancialOperationalTax(totalFinancialOperationalTax);

        return currentInstallment;
    }
}