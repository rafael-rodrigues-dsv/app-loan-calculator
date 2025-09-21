package br.com.devio.component.calculator.chain.impl;

import br.com.devio.component.calculator.chain.CalculatorEngine;
import br.com.devio.component.calculator.chain.CalculatorEngineBuilder;
import br.com.devio.domain.model.InstallmentModel;
import br.com.devio.domain.model.TaxModel;
import br.com.devio.domain.model.PaymentPlanModel;

import java.util.List;
import java.util.Objects;

import static br.com.devio.domain.constant.CalculationConstant.INSTALLMENT_NUMBER_INITIAL;

/**
 * 🏦 Orquestrador principal do sistema PRICE
 */
public class CalculationPrice {
    
    /**
     * ═══════════════════════════════════════════════════════════════
     * 🏦 FLUXO DE CÁLCULOS
     * ═══════════════════════════════════════════════════════════════
     * 1. TF = VS + Seguros + Tarifas + Impostos    (valor financiado)
     * 2. PMT = (TF × r) ÷ (1 - (1+r)^-n)    (parcelas fixas)
     * 3. IOF = (P × t × d) ÷ 100    (se configurado)
     * 4. TE = Σ PMT(i)    (total do empréstimo)
     * ───────────────────────────────────────────────────────────────
     * EXEMPLO: 50.000 + 1.500 = 51.500 → 24x 2.500 = R$ 60.000
     * ═══════════════════════════════════════════════════════════════
     */
    public PaymentPlanModel calculate(PaymentPlanModel paymentPlanModel) {

        CalculatorEngine<PaymentPlanModel> chain = new CalculatorEngineBuilder<PaymentPlanModel>()
                .add(new CalculationTotalFinancedAmount())
                .add(new CalculationPriceInstallment())
                .addIf(this::hasFinancialOperationalTax,
                        () -> new CalculationTaxInstallment(),
                        () -> new CalculationTotalTaxAmount(),
                        () -> new CalculationTotalFinancedAmount(),
                        () -> new CalculationPriceInstallment())
                .add(new CalculationTotalGrantedAmount())
                .add(new CalculationTotalLoanAmount())
                .build();

        paymentPlanModel = chain.calculate(paymentPlanModel);
        paymentPlanModel.setInstallments(filterValidInstallments(paymentPlanModel.getInstallments()));

        return paymentPlanModel;
    }

    private boolean hasFinancialOperationalTax(PaymentPlanModel model) {
        TaxModel financialOperationalTax = model.getTax();
        return Objects.nonNull(financialOperationalTax)
                && Objects.nonNull(financialOperationalTax.getDailyFinancialOperationalTax())
                && Objects.nonNull(financialOperationalTax.getAdditionalFinancialOperationalTax());
    }

    private List<InstallmentModel> filterValidInstallments(List<InstallmentModel> installments) {
        if (Objects.nonNull(installments) && !installments.isEmpty()) {
            return installments.stream()
                    .filter(f -> f.getInstallmentNumber() != INSTALLMENT_NUMBER_INITIAL)
                    .toList();
        }
        return installments;
    }
}

