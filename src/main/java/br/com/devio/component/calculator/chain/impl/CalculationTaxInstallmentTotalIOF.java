package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.constant.CalculationConstant;
import br.com.devio.domain.model.AmountModel;
import br.com.devio.domain.model.InstalmentModel;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * ➕ Totalizador de IOF
 */
public class CalculationTaxInstallmentTotalIOF extends CalculatorEngine<InstalmentModel> {

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
    public InstalmentModel calculate(InstalmentModel currentInstallment) {
        BigDecimal totalFinancialOperationalTax = BigDecimal.ZERO;

        if (!currentInstallment.getInstallmentNumber().equals(CalculationConstant.INSTALLMENT_NUMBER_INITIAL)) {
            BigDecimal dailyTax = Optional.ofNullable(currentInstallment.getTotalDailyFinancialOperationalTax())
                    .map(AmountModel::getAmount)
                    .orElse(BigDecimal.ZERO);
            
            BigDecimal additionalTax = Optional.ofNullable(currentInstallment.getTotalAdditionalFinancialOperationalTax())
                    .map(AmountModel::getAmount)
                    .orElse(BigDecimal.ZERO);
            
            totalFinancialOperationalTax = dailyTax.add(additionalTax)
                    .setScale(CalculationConstant.SCALE_4, CalculationConstant.ROUNDING_MODE);
        }

        currentInstallment.setTotalFinancialOperationalTax(AmountModel.builder()
                .amount(totalFinancialOperationalTax)
                .currency(CalculationConstant.DEFAULT_CURRENCY)
                .build());

        return currentInstallment;
    }
}