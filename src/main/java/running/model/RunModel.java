package running.model;

import java.util.Date;

public class RunModel {
    private int userId;
    private int startLatitude;
    private int startLongutude;
    private Date startDateTime;
    private int finishLatitude;
    private int finishLongutude;
    private Date finishDateTime;
    private int distance;
    private int avgSpeed;

    public RunModel(int userId, int startLatitude, int startLongutude, Date startDateTime, int finishLatitude,
                    int finishLongutude, Date finishDateTime, int distance, int avgSpeed) {
        this.userId = userId;
        this.startLatitude = startLatitude;
        this.startLongutude = startLongutude;
        this.startDateTime = startDateTime;
        this.finishLatitude = finishLatitude;
        this.finishLongutude = finishLongutude;
        this.finishDateTime = finishDateTime;
        this.distance = distance;
        this.avgSpeed = avgSpeed;
    }
}
