package exp.controller;

import org.springframework.stereotype.Component;
import running.controller.RunController;
import running.service.RunService;

@Component
public class RunControllerForTest extends RunController {
    public void setRunService(RunService runService) {
        this.runService = runService;
    }
}
