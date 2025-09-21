package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.domain.model.InstallmentModel;

/**
 * 💵 Definidor do valor da parcela - sistema PRICE
 */
public class CalculationPriceInstallmentTotalInstallmentValue extends CalculatorEngine<InstallmentModel> {

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 FÓRMULA MATEMÁTICA
     * ═══════════════════════════════════════════════════════════════
     * Parcela = PMT    (ascii e algébrica)
     * ───────────────────────────────────────────────────────────────
     * ONDE:
     * Parcela = Valor da parcela
     * PMT = Valor presente calculado
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: PMT = 2.625,22 → Todas as parcelas = R$ 2.625,22
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public InstallmentModel calculate(InstallmentModel beforeInstallment, InstallmentModel currentInstallment) {
        currentInstallment.setTotalInstalmentValue(currentInstallment.getTotalPresentValue());
        return currentInstallment;
    }
}