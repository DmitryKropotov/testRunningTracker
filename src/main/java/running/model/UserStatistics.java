package running.model;

import java.time.Instant;

public class UserStatistics {
    private int numberOfRuns;
    private int totalNumberOfMeters;
    double avgSpeed;
    Instant startDateTimeFrom;
    Instant startDateTimeTo;

    public UserStatistics(int numberOfRuns, int totalNumberOfMeters, double avgSpeed,
                          Instant startDateTimeFrom, Instant startDateTimeTo) {
        this.numberOfRuns = numberOfRuns;
        this.totalNumberOfMeters = totalNumberOfMeters;
        this.avgSpeed = avgSpeed;
        this.startDateTimeFrom = startDateTimeFrom;
        this.startDateTimeTo = startDateTimeTo;
    }

    public int getNumberOfRuns() {
        return numberOfRuns;
    }

    public int getTotalNumberOfMeters() {
        return totalNumberOfMeters;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public Instant getStartDateTimeFrom() {
        return startDateTimeFrom;
    }

    public Instant getStartDateTimeTo() {
        return startDateTimeTo;
    }
}
