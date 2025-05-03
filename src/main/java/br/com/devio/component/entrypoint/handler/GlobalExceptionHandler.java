package br.com.devio.component.entrypoint.handler;

import br.com.devio.component.domain.exception.CalculatorValidationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler {

    @Provider
    public static class CalculatorValidationExceptionHandler implements ExceptionMapper<CalculatorValidationException> {
        @Override
        public Response toResponse(CalculatorValidationException exception) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(exception.getValidationResult().getErrors())
                    .build();
        }
    }

    @Provider
    public static class GenericExceptionHandler implements ExceptionMapper<Exception> {
        @Override
        public Response toResponse(Exception exception) {
            exception.printStackTrace(); // Ou use um logger
            return Response.serverError()
                    .entity(exception.getMessage())
                    .build();
        }
    }
}