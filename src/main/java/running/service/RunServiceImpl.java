package running.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import running.entity.RunEntity;
import running.entity.UserEntity;
import running.model.RunModel;
import running.model.UserStatistics;
import running.repository.RunRepository;
import running.repository.UserRepository;

import java.time.Instant;
import java.util.*;

@Service
public class RunServiceImpl implements RunService {

    @Autowired
    protected RunRepository runRepository;

    @Autowired
    protected UserRepository userRepository;

    private Map<Integer, CurrentUserRun> userCurrentRun = new HashMap<>();

    @Override
    public void startRun(int userId, double startLatitude, double startLongitude, String startDateTime) {
        if(startLatitude<-90 || startLatitude>90) {
            throw new IllegalArgumentException("startLatitude must be between -90 and 90 degrees");
        }
        if( startLongitude<-180 || startLongitude>180) {
            throw new IllegalArgumentException("startLongitude must be between -180 and 180 degrees");
        }
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new RuntimeException("User with id " + userId + " not found");
        }
        userCurrentRun.put(userId, new CurrentUserRun(startLatitude, startLongitude, convertStringToInstant(startDateTime)));
    }

    @Override
    public RunEntity finishRun(int userId, double finishLatitude, double finishLongitude, String finishDateTime, Optional<Integer> distance) {
        if(finishLatitude<-90 || finishLatitude>90) {
            throw new IllegalArgumentException("startLatitude must be between -90 and 90 degrees");
        }
        if( finishLongitude<-180 || finishLongitude>180) {
            throw new IllegalArgumentException("startLongitude must be between -180 and 180 degrees");
        }
        CurrentUserRun userRun = userCurrentRun.get(userId);
        if (userRun == null) {
            throw new RuntimeException("Run of user with userId " + userId + " was not started");
        }
        RunEntity run = new RunEntity(userRepository.findById(userId).get(), userRun.startLatitude, finishLatitude,
                userRun.startLongitude, finishLongitude, userRun.startDateTime, convertStringToInstant(finishDateTime),
                             distance.orElse(calculateDistance(userRun.startLatitude, finishLatitude, userRun.startLongitude, finishLongitude)));
        userCurrentRun.remove(userId);
        return runRepository.save(run);
    }

    @Override
    public List<RunModel> getAllRuns(int userId, Optional<String> startDateTimeFrom, Optional<String> startDateTimeTo) {
        List<RunEntity> runEntities = runRepository.findByUserId(userId);
        return runEntities.stream().filter(run -> run.getStartDateTime().isAfter(convertOptionalStringToInstant(startDateTimeFrom, Instant.MIN)) &&
                run.getStartDateTime().isBefore(convertOptionalStringToInstant(startDateTimeTo, Instant.MAX)) ||
                run.getStartDateTime().equals(convertOptionalStringToInstant(startDateTimeFrom, Instant.MIN)) ||
                run.getStartDateTime().equals(convertOptionalStringToInstant(startDateTimeTo, Instant.MAX))).map(this::convertRunEntityToRunModel).toList();
    }

    @Override
    public UserStatistics getUserStatistics(int userId, Optional<String> startDateTimeFrom, Optional<String> startDateTimeTo) {
        List<RunModel> runModels = getAllRuns(userId, startDateTimeFrom, startDateTimeTo);
        if (runModels.isEmpty()) {
            return new UserStatistics(0, 0, 0,
                convertOptionalStringToInstant(startDateTimeFrom, Instant.MIN), convertOptionalStringToInstant(startDateTimeTo, Instant.MAX));
        } else {
            return new UserStatistics(runModels.size(), runModels.stream().mapToInt(RunModel::getDistance).sum(),
                runModels.stream().mapToInt(RunModel::getDistance).sum() /
                        runModels.stream().mapToDouble(runModel -> runModel.getDistance() / runModel.getAvgSpeed()).sum(),
                convertOptionalStringToInstant(startDateTimeFrom, Instant.MIN), convertOptionalStringToInstant(startDateTimeTo, Instant.MAX));
        }
    }

    private int calculateDistance(double startLatitude, double startLongitude, double finishLatitude, double finishLongitude) {
        return (int) Math.sqrt((finishLatitude-startLatitude)*(finishLatitude-startLatitude)*111000*111000 +
                (startLongitude-finishLongitude)*(startLongitude-finishLongitude)*111300*111300);//Assuming 1 latitude = 111 km, 1 longitude = 111.3 km
    }

    private Instant convertStringToInstant(String dateTime) {
        return Instant.parse(dateTime);
    }

    private Instant convertOptionalStringToInstant(Optional<String> dateTime, Instant defaultValue) {
        return !dateTime.isEmpty()? convertStringToInstant(dateTime.get()): defaultValue;
    }

    private RunModel convertRunEntityToRunModel(RunEntity run) {
        return new RunModel(run.getUser().getId(), run.getStartLatitude(), run.getStartLongitude(),
                run.getStartDateTime(), run.getFinishLatitude(), run.getFinishLongitude(),
                run.getFinishDateTime(), run.getDistance(), calculateAvgSpeed(run.getDistance(),
                run.getStartDateTime(), run.getFinishDateTime()));
    }

    private double calculateAvgSpeed(int distance, Instant startDateTime, Instant finishDateTime) {
        return ((double) distance/((int)(finishDateTime.toEpochMilli()-startDateTime.toEpochMilli())))*1000;
    }

    private class CurrentUserRun {
        private double startLatitude;
        private double startLongitude;
        private Instant startDateTime;


        public CurrentUserRun(double startLatitude, double startLongitude, Instant startDateTime) {
            this.startLatitude = startLatitude;
            this.startLongitude = startLongitude;
            this.startDateTime = startDateTime;
        }

        public double getStartLatitude() {
            return startLatitude;
        }

        public double getStartLongitude() {
            return startLongitude;
        }

        public Instant getStartDateTime() {
            return startDateTime;
        }
    }
}
