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
    private RunRepository runRepository;

    @Autowired
    private UserRepository userRepository;

    private Map<Integer, CurrentUserRun> userCurrentRun = new HashMap<>();

    @Override
    public void startRun(int userId, int startLatitude, int startLongitude, String startDateTime) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new RuntimeException("User with id " + userId + " not found");
        }
        userCurrentRun.put(userId, new CurrentUserRun(startLatitude, startLongitude, convertStringToInstant(startDateTime)));
    }

    @Override
    public void finishRun(int userId, int finishLatitude, int finishLongitude, String finishDateTime, Optional<Integer> distance) {
        CurrentUserRun userRun = userCurrentRun.get(userId);
        if (userRun == null) {
            throw new RuntimeException("Run of user with userId " + userId + " was not started");
        }
        RunEntity run = new RunEntity(userRepository.findById(userId).get(), userRun.startLatitude, finishLatitude,
                userRun.startLongitude, finishLongitude, userRun.startDateTime, convertStringToInstant(finishDateTime),
                             distance.orElse(calculateDistance(userRun.startLatitude, finishLatitude, userRun.startLongitude, finishLongitude)));
        userCurrentRun.remove(userId);
        runRepository.save(run);
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
        return new UserStatistics(runModels.size(), runModels.stream().mapToInt(RunModel::getDistance).sum(),
                runModels.stream().mapToInt(RunModel::getDistance).sum()/
                        runModels.stream().mapToInt(runModel -> runModel.getDistance()/runModel.getAvgSpeed()).sum(),
                convertOptionalStringToInstant(startDateTimeFrom, Instant.MIN), convertOptionalStringToInstant(startDateTimeTo, Instant.MAX));
    }

    private int calculateDistance(int startLatitude, int startLongitude, int finishLatitude, int finishLongitude) {
        return (int) Math.sqrt((finishLatitude-startLatitude)^2 + (startLongitude-finishLongitude)^2);
    }

    private Instant convertStringToInstant(String dateTime) {
        return Instant.parse(dateTime);//?
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

    private int calculateAvgSpeed(int distance, Instant startDateTime, Instant finishDateTime) {
        return (distance/((int)(startDateTime.toEpochMilli()-finishDateTime.toEpochMilli())))/1000;
    }

    private class CurrentUserRun {
        private int startLatitude;
        private int startLongitude;
        private Instant startDateTime;


        public CurrentUserRun(int startLatitude, int startLongitude, Instant startDateTime) {
            this.startLatitude = startLatitude;
            this.startLongitude = startLongitude;
            this.startDateTime = startDateTime;
        }

        public int getStartLatitude() {
            return startLatitude;
        }

        public int getStartLongitude() {
            return startLongitude;
        }

        public Instant getStartDateTime() {
            return startDateTime;
        }
    }
}
