package running.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "run")
public class RunEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private UserEntity user;

    @Column(name = "startlatitude")
    private Double startLatitude;

    @Column(name = "finishlatitude")
    private Double finishLatitude;

    @Column(name = "startlongitude")
    private Double startLongitude;

    @Column(name = "finishlongitude")
    private Double finishLongitude;

    @Column(name = "startdatetime")
    private Instant startDateTime;

    @Column(name = "finishdatetime")
    private Instant finishDateTime;

    @Column(name = "distance")
    private int distance;

    public RunEntity(Integer id, UserEntity user, Double startLatitude, Double finishLatitude, Double startLongitude, Double finishLongitude, Instant startDateTime, Instant finishDateTime, int distance) {
        this.id = id;
        this.user = user;
        this.startLatitude = startLatitude;
        this.finishLatitude = finishLatitude;
        this.startLongitude = startLongitude;
        this.finishLongitude = finishLongitude;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
        this.distance = distance;
    }

    public RunEntity(UserEntity user, Double startLatitude, Double finishLatitude, Double startLongitude, Double finishLongitude,
                     Instant startDateTime, Instant finishDateTime, int distance) {
        this.user = user;
        this.startLatitude = startLatitude;
        this.finishLatitude = finishLatitude;
        this.startLongitude = startLongitude;
        this.finishLongitude = finishLongitude;
        this.startDateTime = startDateTime;
        this.finishDateTime = finishDateTime;
        this.distance = distance;
    }

    public RunEntity() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity userEntity) {
        this.user = userEntity;
    }

    public Double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Double startlatitude) {
        this.startLatitude = startlatitude;
    }

    public Double getFinishLatitude() {
        return finishLatitude;
    }

    public void setFinishLatitude(Double finishlatitude) {
        this.finishLatitude = finishlatitude;
    }

    public Double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Double startlongitude) {
        this.startLongitude = startlongitude;
    }

    public Double getFinishLongitude() {
        return finishLongitude;
    }

    public void setFinishLongitude(Double finishlongitude) {
        this.finishLongitude = finishlongitude;
    }

    public Instant getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Instant startdatetime) {
        this.startDateTime = startdatetime;
    }

    public Instant getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(Instant finishdatetime) {
        this.finishDateTime = finishdatetime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}