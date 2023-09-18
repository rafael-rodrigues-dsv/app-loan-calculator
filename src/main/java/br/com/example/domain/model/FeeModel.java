package br.com.example.domain.model;

import br.com.example.domain.enumeration.PaymentTypeEnum;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeeModel {
    private PaymentTypeEnum paymentType;
    private BigDecimal value;
}
