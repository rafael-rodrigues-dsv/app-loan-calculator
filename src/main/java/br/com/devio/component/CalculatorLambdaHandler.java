package br.com.devio.component;

import br.com.devio.component.configuration.ObjectMapperConfig;
import br.com.devio.component.domain.calculator.exception.CalculatorValidationException;
import br.com.devio.component.entrypoint.command.ServiceCommand;
import br.com.devio.component.entrypoint.command.calculator.CreateLoanCalculatorCommand;
import br.com.devio.component.entrypoint.dto.request.LoanCalculatorRequestDTO;
import br.com.devio.component.entrypoint.dto.response.LoanCalculatorResponseDTO;
import br.com.devio.component.entrypoint.mapper.impl.CustomMapperImpl;
import br.com.devio.component.domain.calculator.service.impl.CalculatorServiceImpl;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CalculatorLambdaHandler implements RequestHandler<String, String> {

    private static final ServiceCommand<LoanCalculatorRequestDTO, LoanCalculatorResponseDTO> calculatorCommand =
            new CreateLoanCalculatorCommand(new CalculatorServiceImpl(), new CustomMapperImpl());

    private ObjectMapper objectMapper = ObjectMapperConfig.getObjectMapper();

    @Override
    public String handleRequest(String input, Context context) {
        try {
            context.getLogger().log("Received input: " + input);
            LoanCalculatorRequestDTO requestDTO;
            try {
                requestDTO = objectMapper.readValue(input, LoanCalculatorRequestDTO.class);
            } catch (Exception e) {
                context.getLogger().log("Error parsing input to LoanCalculatorRequestDTO: " + e.getMessage());
                throw new RuntimeException("Invalid input format", e);
            }
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(calculatorCommand.execute(requestDTO));
        } catch (CalculatorValidationException e) {
            context.getLogger().log("Error validation request: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (Exception e) {
            context.getLogger().log("Error processing request: " + e.getMessage());
            throw new RuntimeException("Error processing loan calculation", e);
        }
    }
}