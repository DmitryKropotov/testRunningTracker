package running.model;

import java.util.Date;

public class UserStatistics {
    private int numberOfRuns;
    private int totalNumberOfMeters;
    int avgSpeed;
    Date startDateTimeFrom;
    Date startDateTimeTo;

    public UserStatistics(int numberOfRuns, int totalNumberOfMeters, int avgSpeed,
                          Date startDateTimeFrom, Date startDateTimeTo) {
        this.numberOfRuns = numberOfRuns;
        this.totalNumberOfMeters = totalNumberOfMeters;
        this.avgSpeed = avgSpeed;
        this.startDateTimeFrom = startDateTimeFrom;
        this.startDateTimeTo = startDateTimeTo;
    }
}
