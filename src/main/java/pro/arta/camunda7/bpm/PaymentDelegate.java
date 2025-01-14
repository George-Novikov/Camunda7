package pro.arta.camunda7.bpm;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class PaymentDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderId = (String) execution.getVariable("orderId");
        Double amount = (Double) execution.getVariable("amount");

        log.info("Processing payment for order: {} amount: {}", orderId, amount);

        boolean paymentSuccess = Math.random() > 0.1; // 90% chance

        execution.setVariable("paymentProcessed", paymentSuccess);
        execution.setVariable("transactionId", UUID.randomUUID().toString());
        execution.setVariable("paymentTimestamp", new Date());
    }
}
