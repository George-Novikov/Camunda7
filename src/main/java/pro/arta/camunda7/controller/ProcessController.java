package pro.arta.camunda7.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.arta.camunda7.service.ProcessService;

import java.util.Map;

@RestController
@RequestMapping("/api/process")
@RequiredArgsConstructor
public class ProcessController {
    private final ProcessService processService;

    @PostMapping("/{processKey}/start")
    public void startProcess(
            @PathVariable String processKey,
            @RequestBody Map<String, Object> variables) {
        processService.startProcess(processKey, variables);
    }
}
