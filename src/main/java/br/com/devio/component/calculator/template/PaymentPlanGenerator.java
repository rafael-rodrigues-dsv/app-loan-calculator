package br.com.devio.component.calculator.template;

import br.com.devio.domain.enumeration.PeriodTypeEnum;
import br.com.devio.domain.model.InstalmentModel;
import br.com.devio.domain.model.LoanModel;
import br.com.devio.domain.model.PaymentPlanModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.LongStream;

import static br.com.devio.domain.constant.CalculationConstant.DAYS_IN_YEAR;
import static java.time.temporal.ChronoUnit.DAYS;

public class PaymentPlanGenerator extends PaymentPlanGeneratorTemplate {
    @Override
    public PaymentPlanModel generatePaymentPlan(LoanModel loanModel) {
        List<InstalmentModel> installments = addInstallments(loanModel);

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
                .instalments(installments)
                .build();
    }

    protected List<InstalmentModel> addInstallments(LoanModel loanModel) {
        BigDecimal interestRate = loanModel.getMonthlyInterestRate();
        PeriodTypeEnum interestRateType = PeriodTypeEnum.MONTHLY;
        LocalDate contractDate = loanModel.getContractDate();
        List<InstalmentModel> installments = new ArrayList<>();

        LongStream.range(0L, loanModel.getInstallmentQuantity() + 1L)
                .forEach(pi -> {
                    Integer installmentNumber = (int) pi;

                    if (installmentNumber.equals(0)) {
                        installments.add(InstalmentModel.builder()
                                .installmentNumber(installmentNumber)
                                .interestRate(interestRate)
                                .interestRateType(interestRateType)
                                .dueDate(contractDate)
                                .contractDays(0)
                                .periodDays(0)
                                .build());
                    } else {
                        InstalmentModel beforeInstallment = installments.get(installmentNumber - 1);

                        LocalDate dueDate = installmentNumber.equals(1)
                                ? loanModel.getFirstInstallmentDate()
                                : beforeInstallment.getDueDate().plusMonths(1);

                        installments.add(InstalmentModel.builder()
                                .installmentNumber(installmentNumber)
                                .interestRate(interestRate)
                                .interestRateType(interestRateType)
                                .dueDate(dueDate)
                                .contractDays(Math.min(
                                        (int) DAYS.between(
                                                contractDate, dueDate),
                                        DAYS_IN_YEAR))
                                .periodDays(Math.min(
                                        (int) DAYS.between(
                                                beforeInstallment.getDueDate(), dueDate),
                                        DAYS_IN_YEAR))
                                .build());
                    }
                });

        return installments;
    }

    protected LocalDate addLastInstallmentDate(List<InstalmentModel> installments) {
        return Optional.ofNullable(installments)
                .filter(list -> Objects.nonNull(list) && !list.isEmpty())
                .map(list -> list.get(list.size() - 1).getDueDate())
                .orElse(null);
    }
}
