package running.model;

import java.time.Instant;
import java.time.Instant;

public class UserStatistics {
    private int numberOfRuns;
    private int totalNumberOfMeters;
    int avgSpeed;
    Instant startDateTimeFrom;
    Instant startDateTimeTo;

    public UserStatistics(int numberOfRuns, int totalNumberOfMeters, int avgSpeed,
                          Instant startDateTimeFrom, Instant startDateTimeTo) {
        this.numberOfRuns = numberOfRuns;
        this.totalNumberOfMeters = totalNumberOfMeters;
        this.avgSpeed = avgSpeed;
        this.startDateTimeFrom = startDateTimeFrom;
        this.startDateTimeTo = startDateTimeTo;
    }
}
