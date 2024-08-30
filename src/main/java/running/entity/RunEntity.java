package running.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

public class RunEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private int userId;
    private int startLatitude;
    private int startLongutude;
    private Instant startDateTime;
    private int finishLatitude;
    private int finishLongutude;
    private Instant finishDateTime;
    private int distance;

    public RunEntity(int userId, int startLatitude, int startLongutude, Instant startDateTime, int finishLatitude,
                     int finishLongutude, Instant finishDateTime, int distance) {
        this.userId = userId;
        this.startLatitude = startLatitude;
        this.startLongutude = startLongutude;
        this.startDateTime = startDateTime;
        this.finishLatitude = finishLatitude;
        this.finishLongutude = finishLongutude;
        this.finishDateTime = finishDateTime;
        this.distance = distance;
    }

    public int getID() {
        return ID;
    }

    public int getUserId() {
        return userId;
    }

    public int getStartLatitude() {
        return startLatitude;
    }

    public int getStartLongutude() {
        return startLongutude;
    }

    public Instant getStartDateTime() {
        return startDateTime;
    }

    public int getFinishLatitude() {
        return finishLatitude;
    }

    public int getFinishLongutude() {
        return finishLongutude;
    }

    public Instant getFinishDateTime() {
        return finishDateTime;
    }

    public int getDistance() {
        return distance;
    }
}
