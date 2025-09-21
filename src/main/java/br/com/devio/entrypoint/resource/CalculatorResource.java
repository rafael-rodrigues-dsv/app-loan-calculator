package br.com.devio.entrypoint.resource;

import br.com.devio.entrypoint.command.ServiceCommand;
import br.com.devio.generated.api.CalculatorApi;
import br.com.devio.generated.dto.LoanCalculatorRequestDTO;
import br.com.devio.generated.dto.LoanCalculatorResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class CalculatorResource implements CalculatorApi {

    @Inject
    ServiceCommand<LoanCalculatorRequestDTO, LoanCalculatorResponseDTO> calculatorCommand;

    @Override
    public Response calculateLoan(LoanCalculatorRequestDTO request) {
        LoanCalculatorResponseDTO response = calculatorCommand.execute(request);
        return Response.ok(response).build();
    }
}