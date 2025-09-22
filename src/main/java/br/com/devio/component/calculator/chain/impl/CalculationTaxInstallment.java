package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.component.calculator.chain.CalculatorEngineBuilder;
import br.com.devio.domain.model.AmountModel;
import br.com.devio.domain.model.InstalmentModel;
import br.com.devio.domain.model.PaymentPlanModel;
import br.com.devio.domain.model.TaxModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.devio.domain.constant.CalculationConstant.INSTALLMENT_NUMBER_INITIAL;

/**
 * 📊 Processador de IOF nas parcelas
 */
public class CalculationTaxInstallment extends CalculatorEngine<PaymentPlanModel> {

    /**
     * ═══════════════════════════════════════════════════════════════
     * 📊 SEQUÊNCIA DE CÁLCULOS DE IOF
     * ═══════════════════════════════════════════════════════════════
     * IOFᴅ = (P × tᴅ × d) ÷ 100    (diário)
     * IOFᴀ = (P × tᴀ) ÷ 100    (adicional)
     * IOF Total = IOFᴅ + IOFᴀ    (soma)
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: 12,30 + 380,00 = R$ 392,30
     * ═══════════════════════════════════════════════════════════════
     */
    @Override
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {
        List<InstalmentModel> installments = new ArrayList<>();
        final AmountModel totalFinancedAmount = Optional.ofNullable(paymentPlanModel.getTotalFinancedAmount())
                .orElse(AmountModel.builder().amount(BigDecimal.ZERO).currency("BRL").build());
        final TaxModel financialOperationalTax = paymentPlanModel.getTax();
        final AmountModel dailyFinancialOperationalTax = Optional.ofNullable(financialOperationalTax)
                .map(TaxModel::getDailyFinancialOperationalTax)
                .orElse(AmountModel.builder().amount(BigDecimal.ZERO).currency("BRL").build());
        final AmountModel additionalFinancialOperationalTax = Optional.ofNullable(financialOperationalTax)
                .map(TaxModel::getAdditionalFinancialOperationalTax)
                .orElse(AmountModel.builder().amount(BigDecimal.ZERO).currency("BRL").build());

        paymentPlanModel.getInstalments().forEach(currentInstallment -> {
            if (!currentInstallment.getInstallmentNumber().equals(INSTALLMENT_NUMBER_INITIAL)) {
                CalculatorEngine<InstalmentModel> chain = new CalculatorEngineBuilder<InstalmentModel>()
                        .add(new CalculationTaxInstallmentDailyIOF(dailyFinancialOperationalTax, totalFinancedAmount))
                        .add(new CalculationTaxInstallmentAdditionalIOF(additionalFinancialOperationalTax, totalFinancedAmount))
                        .add(new CalculationTaxInstallmentTotalIOF())
                        .build();

                currentInstallment = chain.calculate(currentInstallment);
            }

            installments.add(currentInstallment);
        });

        paymentPlanModel.setInstalments(installments);

        return paymentPlanModel;
    }
}