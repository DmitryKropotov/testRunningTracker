package exp.service;

import org.springframework.stereotype.Component;
import running.service.RunServiceImpl;
import running.repository.RunRepository;
import running.repository.UserRepository;

@Component
public class RunServiceForTest extends RunServiceImpl {
    public void setRunRepository(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
