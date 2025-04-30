package br.com.example.entrypoint.dto.request;

import br.com.example.domain.calculator.enumeration.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceRequestDTO {
    private PaymentTypeEnum paymentType;
    private BigDecimal value;
}
