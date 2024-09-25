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
    private Integer startLatitude;

    @Column(name = "finishlatitude")
    private Integer finishLatitude;

    @Column(name = "startlongitude")
    private Integer startLongitude;

    @Column(name = "finishlongitude")
    private Integer finishLongitude;

    @Column(name = "startdatetime")
    private Instant startDateTime;

    @Column(name = "finishdatetime")
    private Instant finishDateTime;

    @Column(name = "distance")
    private int distance;

    public RunEntity(UserEntity user, Integer startLatitude, Integer finishLatitude, Integer startLongitude, Integer finishLongitude,
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

    public Integer getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Integer startlatitude) {
        this.startLatitude = startlatitude;
    }

    public Integer getFinishLatitude() {
        return finishLatitude;
    }

    public void setFinishLatitude(Integer finishlatitude) {
        this.finishLatitude = finishlatitude;
    }

    public Integer getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Integer startlongitude) {
        this.startLongitude = startlongitude;
    }

    public Integer getFinishLongitude() {
        return finishLongitude;
    }

    public void setFinishLongitude(Integer finishlongitude) {
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