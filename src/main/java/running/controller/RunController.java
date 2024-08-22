package running.controller;

import running.entity.RunEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RunController {
    void startRun(int userId, int startLatitude, int startLongitude, Date startDateTime);
    void finishRun(int userId, int finishLatitude, int finishLongitude, Date finishDateTime, Optional<Integer> distance);
    List<RunEntity> getAllRuns(int userId, Date startDateTimeFrom, Date startDateTimeTo);
}
