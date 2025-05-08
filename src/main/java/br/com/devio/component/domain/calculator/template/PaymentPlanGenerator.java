package br.com.devio.component.domain.calculator.template;

import br.com.devio.component.domain.enumeration.PeriodTypeEnum;
import br.com.devio.component.domain.model.InstallmentModel;
import br.com.devio.component.domain.model.LoanModel;
import br.com.devio.component.domain.model.PaymentPlanModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import static java.time.temporal.ChronoUnit.DAYS;

public class PaymentPlanGenerator extends PaymentPlanGeneratorTemplate {
    private int limitSize = 365;

    @Override
    public PaymentPlanModel generatePaymentPlan(LoanModel loanModel) {
        List<InstallmentModel> installments = addInstallments(loanModel);

        return PaymentPlanModel.builder()
                .calculationType(loanModel.getCalculationType())
                .installmentQuantity(loanModel.getInstallmentQuantity())
                .requestedAmount(loanModel.getRequestedAmount())
                .contractDate(loanModel.getContractDate())
                .firstInstallmentDate(loanModel.getFirstInstallmentDate())
                .lastInstallmentDate(addLastInstallmentDate(installments))
                .monthlyInterestRate(loanModel.getMonthlyInterestRate())
                .fee(loanModel.getFee())
                .insurance(loanModel.getInsurance())
                .tax(loanModel.getTax())
                .installments(installments)
                .build();
    }

    protected List<InstallmentModel> addInstallments(LoanModel loanModel) {
        BigDecimal interestRate = loanModel.getMonthlyInterestRate();
        PeriodTypeEnum interestRateType = PeriodTypeEnum.MONTHLY;
        LocalDate contractDate = loanModel.getContractDate();
        List<InstallmentModel> installments = new ArrayList<>();

        LongStream.range(0L, loanModel.getInstallmentQuantity() + 1L)
                .forEach(pi -> {
                    Integer installmentNumber = (int) pi;

                    if (installmentNumber.equals(0)) {
                        installments.add(InstallmentModel.builder()
                                .number(installmentNumber)
                                .interestRate(interestRate)
                                .interestRateType(interestRateType)
                                .dueDate(contractDate)
                                .contractDays(0)
                                .periodDays(0)
                                .build());
                    } else {
                        InstallmentModel beforeInstallment = installments.get(installmentNumber - 1);

                        LocalDate dueDate = installmentNumber.equals(1)
                                ? loanModel.getFirstInstallmentDate()
                                : beforeInstallment.getDueDate().plusMonths(1);

                        installments.add(InstallmentModel.builder()
                                .number(installmentNumber)
                                .interestRate(interestRate)
                                .interestRateType(interestRateType)
                                .dueDate(dueDate)
                                .contractDays(Math.min(
                                        (int) DAYS.between(
                                                contractDate, dueDate),
                                        limitSize))
                                .periodDays(Math.min(
                                        (int) DAYS.between(
                                                beforeInstallment.getDueDate(), dueDate),
                                        limitSize))
                                .build());
                    }
                });

        return installments;
    }

    protected LocalDate addLastInstallmentDate(List<InstallmentModel> installments) {
        Integer installmentSize = installments.size();

        if (installments != null && !installments.isEmpty()) {
            return installments.get(installmentSize - 1).getDueDate();
        }

        return null;
    }
}
