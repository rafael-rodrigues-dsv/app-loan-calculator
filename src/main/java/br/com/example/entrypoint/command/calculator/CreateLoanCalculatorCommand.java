package br.com.example.entrypoint.command.calculator;

import br.com.example.domain.calculator.model.LoanModel;
import br.com.example.domain.calculator.service.CalculatorService;
import br.com.example.entrypoint.command.ServiceCommand;
import br.com.example.entrypoint.dto.request.LoanCalculatorRequestDTO;
import br.com.example.entrypoint.dto.response.LoanCalculatorResponseDTO;
import br.com.example.entrypoint.mapper.CustomMapper;

public class CreateLoanCalculatorCommand implements ServiceCommand<LoanCalculatorRequestDTO, LoanCalculatorResponseDTO> {

    private final CalculatorService service;
    private final CustomMapper customMapper;

    public CreateLoanCalculatorCommand(CalculatorService service, CustomMapper customMapper) {
        this.service = service;
        this.customMapper = customMapper;
    }

    @Override
    public LoanCalculatorResponseDTO execute(LoanCalculatorRequestDTO loanCalculatorRequestDto) {
        return customMapper.map(service.calculate(customMapper.map(loanCalculatorRequestDto, LoanModel.class)),
                LoanCalculatorResponseDTO.class);
    }
}

