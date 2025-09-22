package br.com.devio.component.calculator.command.impl;

import br.com.devio.component.calculator.command.MathCalculationCommand;
import br.com.devio.domain.model.InstalmentModel;

import java.util.List;

import static br.com.devio.domain.constant.CalculationConstant.DAYS_IN_YEAR;

public class MathCalculationNetPresentValue implements MathCalculationCommand<List<InstalmentModel>, Double> {

    private final Double interestRate;

    public MathCalculationNetPresentValue(Double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public Double execute(List<InstalmentModel> installments) {
        if (installments == null) {
            throw new IllegalArgumentException("Installments list cannot be null");
        }
        
        Double totalNetPresentValue = 0.0;

        for (InstalmentModel currentInstallment : installments) {
            Double currentInstallmentValue = currentInstallment.getTotalInstalmentValue() != null
                    ? currentInstallment.getTotalInstalmentValue().getAmount().doubleValue()
                    : 0.0;

            if (currentInstallment.getInstallmentNumber() == 0) {
                currentInstallmentValue = currentInstallment.getTotalBalanceAmount() != null
                        ? currentInstallment.getTotalBalanceAmount().getAmount().negate().doubleValue()
                        : 0.0;
            }

            Double discountFactor = Math.pow(
                    1 + interestRate,
                    (double) currentInstallment.getContractDays() / DAYS_IN_YEAR);

            totalNetPresentValue += currentInstallmentValue / discountFactor;
        }

        return totalNetPresentValue;
    }
}
