package br.com.devio.component.entrypoint.dto.request;

import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import br.com.devio.component.domain.enumeration.TaxTypeEnum;
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
public class TaxRequestDTO {
    private PaymentTypeEnum paymentType;
    private BigDecimal value;
    private TaxTypeEnum taxType;
}
