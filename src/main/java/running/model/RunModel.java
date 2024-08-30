package running.model;

import java.time.Instant;

public class RunModel {
    private int userId;
    private int startLatitude;
    private int startLongitude;
    private Instant startInstantTime;
    private int finishLatitude;
    private int finishLongitude;
    private Instant finishInstantTime;
    private int distance;
    private int avgSpeed;

    public RunModel(int userId, int startLatitude, int startLongitude, Instant startInstantTime, int finishLatitude,
                    int finishLongitude, Instant finishInstantTime, int distance, int avgSpeed) {
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

    public int getStartLatitude() {
        return startLatitude;
    }

    public int getStartLongitude() {
        return startLongitude;
    }

    public Instant getStartInstantTime() {
        return startInstantTime;
    }

    public int getFinishLatitude() {
        return finishLatitude;
    }

    public int getFinishLongitude() {
        return finishLongitude;
    }

    public Instant getFinishInstantTime() {
        return finishInstantTime;
    }

    public int getDistance() {
        return distance;
    }

    public int getAvgSpeed() {
        return avgSpeed;
    }
}
