package br.com.example;

import br.com.example.domain.calculator.exception.CalculatorValidationException;
import br.com.example.domain.calculator.service.impl.CalculatorServiceImpl;
import br.com.example.entrypoint.command.ServiceCommand;
import br.com.example.entrypoint.command.calculator.CreateLoanCalculatorCommand;
import br.com.example.entrypoint.dto.request.LoanCalculatorRequestDTO;
import br.com.example.entrypoint.dto.response.LoanCalculatorResponseDTO;
import br.com.example.entrypoint.mapper.impl.CustomMapperImpl;
import br.com.example.entrypoint.util.ObjectMapperUtil;
import br.com.fluentvalidator.context.ValidationResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainCalculatorLambda implements RequestHandler<LoanCalculatorRequestDTO, LoanCalculatorResponseDTO> {

    private static final ServiceCommand<LoanCalculatorRequestDTO, LoanCalculatorResponseDTO> calculatorCommand =
            new CreateLoanCalculatorCommand(new CalculatorServiceImpl(), new CustomMapperImpl());

    private static final ObjectMapper objectMapper = ObjectMapperUtil.createObjectMapper();

    @Override
    public LoanCalculatorResponseDTO handleRequest(LoanCalculatorRequestDTO input, Context context) {
        try {
            context.getLogger().log("Received input: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(input));
            return calculatorCommand.execute(input);
        } catch (CalculatorValidationException e) {
            ValidationResult validationResult = e.getValidationResult();
            try {
                context.getLogger().log("Validation error: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(validationResult));
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(String.valueOf(validationResult));
        } catch (Exception e) {
            context.getLogger().log("Error processing request: " + e.getMessage());
            throw new RuntimeException("Error processing loan calculation", e);
        }
    }
}
