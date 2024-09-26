package running.model;

import java.time.Instant;
import java.util.Objects;

public class RunModel {
    private int userId;
    private double startLatitude;
    private double startLongitude;
    private Instant startInstantTime;
    private double finishLatitude;
    private double finishLongitude;
    private Instant finishInstantTime;
    private int distance;
    private double avgSpeed;

    public RunModel(int userId, double startLatitude, double startLongitude, Instant startInstantTime, double finishLatitude,
                    double finishLongitude, Instant finishInstantTime, int distance, double avgSpeed) {
        this.userId = userId;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.startInstantTime = startInstantTime;
        this.finishLatitude = finishLatitude;
        this.finishLongitude = finishLongitude;
        this.finishInstantTime = finishInstantTime;
        this.distance = distance;
        this.avgSpeed = avgSpeed;
    }

    public int getUserId() {
        return userId;
    }

    public double getStartLatitude() {
        return startLatitude;
    }

    public double getStartLongitude() {
        return startLongitude;
    }

    public Instant getStartInstantTime() {
        return startInstantTime;
    }

    public double getFinishLatitude() {
        return finishLatitude;
    }

    public double getFinishLongitude() {
        return finishLongitude;
    }

    public Instant getFinishInstantTime() {
        return finishInstantTime;
    }

    public int getDistance() {
        return distance;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RunModel runModel)) return false;

        return userId == runModel.userId && Double.compare(startLatitude, runModel.startLatitude) == 0 && Double.compare(startLongitude, runModel.startLongitude) == 0 && Double.compare(finishLatitude, runModel.finishLatitude) == 0 && Double.compare(finishLongitude, runModel.finishLongitude) == 0 && distance == runModel.distance && Double.compare(avgSpeed, runModel.avgSpeed) == 0 && Objects.equals(startInstantTime, runModel.startInstantTime) && Objects.equals(finishInstantTime, runModel.finishInstantTime);
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + Double.hashCode(startLatitude);
        result = 31 * result + Double.hashCode(startLongitude);
        result = 31 * result + Objects.hashCode(startInstantTime);
        result = 31 * result + Double.hashCode(finishLatitude);
        result = 31 * result + Double.hashCode(finishLongitude);
        result = 31 * result + Objects.hashCode(finishInstantTime);
        result = 31 * result + distance;
        result = 31 * result + Double.hashCode(avgSpeed);
        return result;
    }
}
