package running.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class RunEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private int userId;
    private int startLatitude;
    private int startLongutude;
    private Date startDateTime;
    private int finishLatitude;
    private int finishLongutude;
    private Date finishDateTime;
    private int distance;

    public RunEntity(int userId, int startLatitude, int startLongutude, Date startDateTime, int finishLatitude,
                     int finishLongutude, Date finishDateTime, int distance) {
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

    public Date getStartDateTime() {
        return startDateTime;
    }

    public int getFinishLatitude() {
        return finishLatitude;
    }

    public int getFinishLongutude() {
        return finishLongutude;
    }

    public Date getFinishDateTime() {
        return finishDateTime;
    }

    public int getDistance() {
        return distance;
    }
}
