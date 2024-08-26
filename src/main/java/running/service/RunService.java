package running.service;

import org.springframework.web.bind.annotation.RequestParam;
import running.model.RunModel;
import running.model.UserStatistics;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RunService {
    void startRun(int userId, int startLatitude, int startLongitude, Date startDateTime);
    void finishRun(int userId, int finishLatitude, int finishLongitude, Date finishDateTime, Optional<Integer> distance);
    List<RunModel> getAllRuns(int userId, Optional<Date> startDateTimeFrom, Optional<Date> startDateTimeTo);
    UserStatistics getUserStatistics(int userId, Optional<Date> startDateTimeFrom, Optional<Date> startDateTimeTo);
}
