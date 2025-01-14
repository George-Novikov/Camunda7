package pro.arta.camunda7.bpm;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderId = (String) execution.getVariable("orderId");
        Double amount = (Double) execution.getVariable("amount");

        log.info("Validating order: {} with amount: {}", orderId, amount);

        boolean isValid = true;

        execution.setVariable("orderValidated", isValid);
    }
}
