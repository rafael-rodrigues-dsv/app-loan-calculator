package br.com.example.entrypoint.dto.request;

import br.com.example.domain.calculator.enumeration.PaymentTypeEnum;
import lombok.*;

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
