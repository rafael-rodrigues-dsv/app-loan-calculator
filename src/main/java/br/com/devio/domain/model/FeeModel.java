package br.com.devio.domain.model;

import br.com.devio.domain.enumeration.PaymentTypeEnum;
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
public class FeeModel {
    private PaymentTypeEnum paymentType;
    private BigDecimal totalAmount;
}
