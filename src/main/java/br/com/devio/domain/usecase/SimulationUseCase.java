package br.com.devio.domain.usecase;

import br.com.devio.domain.model.LoanModel;
import br.com.devio.domain.model.PaymentPlanModel;

public interface SimulationUseCase {
    PaymentPlanModel executeSimulation(LoanModel loanModel);
}
