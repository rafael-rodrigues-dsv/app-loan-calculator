package br.com.devio.component.domain.calculator.component.template;

import br.com.devio.component.domain.calculator.model.LoanModel;
import br.com.devio.component.domain.calculator.model.PaymentPlanModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PaymentPlanGeneratorTemplateTest {

    @Test
    void testGenerate() {
        // Arrange
        LoanModel loanModel = mock(LoanModel.class);
        PaymentPlanModel paymentPlanModel = mock(PaymentPlanModel.class);

        PaymentPlanGeneratorTemplate generator = mock(PaymentPlanGeneratorTemplate.class);
        when(generator.generatePaymentPlan(loanModel)).thenReturn(paymentPlanModel);

        // Act
        PaymentPlanModel result = generator.generate(loanModel);

        // Assert
        assertNotNull(result);
        assertEquals(paymentPlanModel, result);
        verify(generator, times(1)).generatePaymentPlan(loanModel);
    }
}