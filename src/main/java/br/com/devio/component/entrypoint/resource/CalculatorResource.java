package br.com.devio.component.entrypoint.resource;

import br.com.devio.component.entrypoint.command.ServiceCommand;
import br.com.devio.component.entrypoint.dto.request.LoanCalculatorRequestDTO;
import br.com.devio.component.entrypoint.dto.response.LoanCalculatorResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
@Path("/calculator")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalculatorResource {
    @Inject
    ServiceCommand<LoanCalculatorRequestDTO, LoanCalculatorResponseDTO> calculatorCommand;

    @POST
    public Response calculateLoan(LoanCalculatorRequestDTO request) {
        return Response.ok(calculatorCommand.execute(request)).build();
    }
}

