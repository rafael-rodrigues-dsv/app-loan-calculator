package br.com.devio.component.entrypoint.resource;

import br.com.devio.component.domain.exception.CalculatorValidationException;
import br.com.devio.component.entrypoint.command.ServiceCommand;
import br.com.devio.component.entrypoint.dto.request.LoanCalculatorRequestDTO;
import br.com.devio.component.entrypoint.dto.response.LoanCalculatorResponseDTO;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Singleton
@Path("/calculator")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalculatorResource {

    private final ServiceCommand<LoanCalculatorRequestDTO, LoanCalculatorResponseDTO> calculatorCommand;

    @POST
    public Response calculateLoan(LoanCalculatorRequestDTO request) {
        try {
            LoanCalculatorResponseDTO result = calculatorCommand.execute(request);
            return Response.ok(result).build();
        } catch (CalculatorValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getValidationResult().getErrors()).build();
        } catch (Exception e) {
            return Response.serverError().entity("Erro interno").build();
        }
    }
}

