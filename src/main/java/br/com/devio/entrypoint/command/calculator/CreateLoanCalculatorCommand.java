package br.com.devio.entrypoint.command.calculator;

import br.com.devio.domain.service.CalculatorService;
import br.com.devio.domain.model.LoanModel;
import br.com.devio.entrypoint.command.ServiceCommand;
import br.com.devio.entrypoint.dto.request.LoanCalculatorRequestDTO;
import br.com.devio.entrypoint.dto.response.LoanCalculatorResponseDTO;
import br.com.devio.component.mapper.CustomMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CreateLoanCalculatorCommand implements ServiceCommand<LoanCalculatorRequestDTO, LoanCalculatorResponseDTO> {

    @Inject
    private CalculatorService service;
    @Inject
    private CustomMapper customMapper;

    @Override
    public LoanCalculatorResponseDTO execute(LoanCalculatorRequestDTO loanCalculatorRequestDto) {
        return customMapper.map(service.calculate(customMapper.map(loanCalculatorRequestDto, LoanModel.class)),
                LoanCalculatorResponseDTO.class);
    }
}

