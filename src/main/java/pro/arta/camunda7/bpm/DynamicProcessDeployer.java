package pro.arta.camunda7.bpm;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DynamicProcessDeployer {

    @Autowired
    private RepositoryService repositoryService;

    @EventListener(ApplicationStartedEvent.class)
    public void deployProcess() {
        BpmnModelInstance processModel = Bpmn.createExecutableProcess("dynamic-order-process")
                .camundaHistoryTimeToLive(30)
                .startEvent()
                .serviceTask()
                .name("Validate Order")
                .camundaDelegateExpression("${orderDelegate}")
                .exclusiveGateway()
                .name("Check Amount")
                .userTask()
                .name("Manager Approval")
                .camundaCandidateGroups("managers")
                .serviceTask()
                .name("Process Payment")
                .camundaDelegateExpression("${paymentDelegate}")
                .endEvent()
                .done();

        repositoryService.createDeployment()
                .name("Dynamic Order Process")
                .addModelInstance("dynamic-order-process.bpmn", processModel)
                .deploy();
    }
}
