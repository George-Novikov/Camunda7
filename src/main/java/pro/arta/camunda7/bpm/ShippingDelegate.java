package pro.arta.camunda7.bpm;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class ShippingDelegate implements JavaDelegate {
    private static final Logger log = LoggerFactory.getLogger(ShippingDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String orderId = (String) execution.getVariable("orderId");

        log.info("Preparing shipping for order: {}", orderId);

        String trackingNumber = "TRACK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        execution.setVariable("trackingNumber", trackingNumber);
        execution.setVariable("shippingStatus", "PREPARING");
        execution.setVariable("estimatedDelivery", Date.from(Instant.now().plus(3, ChronoUnit.DAYS)));
    }
}
