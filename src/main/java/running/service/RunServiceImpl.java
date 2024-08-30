package running.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import running.entity.Run;
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
    public void startRun(int userId, int startLatitude, int startLongitude, Instant startDateTime) {
        userCurrentRun.put(userId, new CurrentUserRun(startLatitude, startLatitude, startDateTime));
    }

    @Override
    public void finishRun(int userId, int finishLatitude, int finishLongitude, Instant finishDateTime, Optional<Integer> distance) {
        CurrentUserRun userRun = userCurrentRun.get(userId);
        if (userRun == null) {
            throw new RuntimeException("Run of user with userId " + userId + " was not started");
        }
        Run run = new Run(userRepository.findById(userId).get(), userRun.startLatitude, finishLatitude,
                userRun.startLongitude, finishLongitude, userRun.startDateTime, finishDateTime,
                             distance.orElse(calculateDistance(userRun.startLatitude, finishLatitude, userRun.startLongitude, finishLongitude)));
        userCurrentRun.remove(userId);
        runRepository.save(run);
    }

    @Override
    public List<RunModel> getAllRuns(int userId, Optional<Instant> startDateTimeFrom, Optional<Instant> startDateTimeTo) {
        List<Run> runEntities = runRepository.findByUserId(userId);
        return runEntities.stream().filter(run -> run.getStartDateTime().isAfter(startDateTimeFrom.orElse(Instant.EPOCH)) &&
                run.getStartDateTime().isBefore(startDateTimeTo.orElse(Instant.MAX)) ||
                run.getStartDateTime().equals(startDateTimeFrom) ||
                run.getStartDateTime().equals(startDateTimeTo)).map(this::convertRunEntityToRunModel).toList();
    }

    @Override
    public UserStatistics getUserStatistics(int userId, Optional<Instant> startDateTimeFrom, Optional<Instant> startDateTimeTo) {
        List<RunModel> runModels = getAllRuns(userId, startDateTimeFrom, startDateTimeTo);
        runModels.stream().forEach(runModel -> {});
        return new UserStatistics(runModels.size(), runModels.stream().mapToInt(RunModel::getDistance).sum(),
                runModels.stream().mapToInt(runModel -> calculateAvgSpeed(runModel.getDistance(),
                        runModel.getStartInstantTime(), runModel.getFinishInstantTime())).sum()/runModels.size(),
                startDateTimeFrom.orElse(null), startDateTimeTo.orElse(null));
    }

    private int calculateDistance(int startLatitude, int startLongitude, int finishLatitude, int finishLongitude) {
        return (int) Math.sqrt((finishLatitude-startLatitude)^2 + (startLongitude-finishLongitude)^2);
    }

    private RunModel convertRunEntityToRunModel(Run run) {
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
