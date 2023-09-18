package br.com.example.domain.service.generator;

import br.com.example.domain.model.InstallmentModel;
import br.com.example.domain.model.LoanModel;
import br.com.example.domain.model.PaymentPlanModel;

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
                .pricing(loanModel.getPricing())
                .installmentQuantity(loanModel.getInstallmentQuantity())
                .amount(loanModel.getAmount())
                .contractDate(loanModel.getContractDate())
                .firstInstallmentDate(loanModel.getFirstInstallmentDate())
                .lastInstallmentDate(addLastInstallmentDate(installments))
                .fees(loanModel.getFees())
                .insurances(loanModel.getInsurances())
                .taxes(loanModel.getTaxes())
                .installments(installments)
                .build();
    }

    private List<InstallmentModel> addInstallments(LoanModel loanModel) {
        BigDecimal interestRate = loanModel.getPricing().getInterestRate();
        LocalDate contractDate = loanModel.getContractDate();
        List<InstallmentModel> installments = new ArrayList<>();

        LongStream.range(0L, loanModel.getInstallmentQuantity() + 1L)
                .forEach(pi -> {
                    Integer installmentNumber = (int) pi;

                    if (installmentNumber.equals(0)) {
                        installments.add(InstallmentModel.builder()
                                .number(installmentNumber)
                                .interestRate(interestRate)
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

    private LocalDate addLastInstallmentDate(List<InstallmentModel> installments) {
        Integer installmentSize = installments.size();

        if (installments != null && !installments.isEmpty()) {
            return installments.get(installmentSize - 1).getDueDate();
        }

        return null;
    }
}
