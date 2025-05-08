package br.com.devio.component.entrypoint.dto.response;

import br.com.devio.component.domain.enumeration.PaymentTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceResponseDTO {
    private PaymentTypeEnum paymentType;
    private BigDecimal totalAmount;
}
