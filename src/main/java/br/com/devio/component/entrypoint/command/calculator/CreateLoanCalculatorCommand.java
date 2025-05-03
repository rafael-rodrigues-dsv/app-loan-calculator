package br.com.devio.component.entrypoint.command.calculator;

import br.com.devio.component.domain.service.CalculatorService;
import br.com.devio.component.domain.model.LoanModel;
import br.com.devio.component.entrypoint.command.ServiceCommand;
import br.com.devio.component.entrypoint.dto.request.LoanCalculatorRequestDTO;
import br.com.devio.component.entrypoint.dto.response.LoanCalculatorResponseDTO;
import br.com.devio.component.entrypoint.mapper.CustomMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

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

