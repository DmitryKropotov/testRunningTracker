package running.service;

import running.model.RunModel;

import java.util.Date;
import java.util.List;

public interface RunService {
    List<RunModel> getAllRuns(int userId, Date startDateTimeFrom, Date startDateTimeTo);
}
