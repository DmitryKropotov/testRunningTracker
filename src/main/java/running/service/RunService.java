package running.service;

import running.model.RunModel;
import running.model.UserStatistics;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface RunService {
    void startRun(int userId, int startLatitude, int startLongitude, String startDateTime);
    void finishRun(int userId, int finishLatitude, int finishLongitude, String finishDateTime, Optional<Integer> distance);
    List<RunModel> getAllRuns(int userId, Optional<String> startDateTimeFrom, Optional<String> startDateTimeTo);
    UserStatistics getUserStatistics(int userId, Optional<String> startDateTimeFrom, Optional<String> startDateTimeTo);
}
