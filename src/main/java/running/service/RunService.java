package running.service;

import running.entity.RunEntity;
import running.model.RunModel;
import running.model.UserStatistics;
import java.util.List;
import java.util.Optional;

public interface RunService {
    void startRun(int userId, double startLatitude, double startLongitude, String startDateTime);
    RunEntity finishRun(int userId, double finishLatitude, double finishLongitude, String finishDateTime, Optional<Integer> distance);
    List<RunModel> getAllRuns(int userId, Optional<String> startDateTimeFrom, Optional<String> startDateTimeTo);
    UserStatistics getUserStatistics(int userId, Optional<String> startDateTimeFrom, Optional<String> startDateTimeTo);
}
