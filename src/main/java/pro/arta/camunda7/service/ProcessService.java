package pro.arta.camunda7.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProcessService {
    private final RuntimeService runtimeService;

    public void startProcess(String processKey, Map<String, Object> variables) {
        runtimeService.startProcessInstanceByKey(processKey, variables);
    }
}
