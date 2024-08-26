package running.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import running.entity.RunEntity;
import running.model.RunModel;
import running.model.UserStatistics;
import running.repository.RunRepository;

import java.util.*;

@Service
public class RunServiceImpl implements RunService {

    @Autowired
    private RunRepository runRepository;

    private Map<Integer, CurrentUserRun> userCurrentRun = new HashMap<>();

    @Override
    public void startRun(int userId, int startLatitude, int startLongitude, Date startDateTime) {
        userCurrentRun.put(userId, new CurrentUserRun(startLatitude, startLatitude, startDateTime));
    }

    @Override
    public void finishRun(int userId, int finishLatitude, int finishLongitude, Date finishDateTime, Optional<Integer> distance) {
        CurrentUserRun userRun = userCurrentRun.get(userId);
        if (userRun == null) {
            throw new RuntimeException("Run of user with userId " + userId + " was not started");
        }
        RunEntity runEntity = new RunEntity(userId, userRun.startLatitude, userRun.startLongutude, userRun.startDateTime,
                                    finishLatitude, finishLatitude, finishDateTime,
                             distance.orElse(calculateDistance(userRun.startLatitude, finishLatitude, userRun.startLongutude, finishLongitude)));
        userCurrentRun.remove(userId);
        runRepository.save(runEntity);
    }

    @Override
    public List<RunModel> getAllRuns(int userId, Optional<Date> startDateTimeFrom, Optional<Date> startDateTimeTo) {
        List<RunEntity> runEntities = runRepository.findByUserId(userId);
        return runEntities.stream().filter(runEntity -> runEntity.getStartDateTime().after(startDateTimeFrom.orElse(new Date(0))) &&
                runEntity.getStartDateTime().before(startDateTimeTo.orElse(new Date(9999, Calendar.JANUARY, 1))) ||
                runEntity.getStartDateTime().equals(startDateTimeFrom) ||
                runEntity.getStartDateTime().equals(startDateTimeTo)).map(this::convertRunEntityToRunModel).toList();
    }

    @Override
    public UserStatistics getUserStatistics(int userId, Optional<Date> startDateTimeFrom, Optional<Date> startDateTimeTo) {
        List<RunModel> runModels = getAllRuns(userId, startDateTimeFrom, startDateTimeTo);
        runModels.stream().forEach(runModel -> {});
        return new UserStatistics(runModels.size(), runModels.stream().mapToInt(RunModel::getDistance).sum(),
                runModels.stream().mapToInt(runModel -> calculateAvgSpeed(runModel.getDistance(),
                        runModel.getStartDateTime(), runModel.getFinishDateTime())).sum()/runModels.size(),
                startDateTimeFrom.orElse(null), startDateTimeTo.orElse(null));
    }

    private int calculateDistance(int startLatitude, int startLongitude, int finishLatitude, int finishLongitude) {
        return (int) Math.sqrt((finishLatitude-startLatitude)^2 + (startLongitude-finishLongitude)^2);
    }

    private RunModel convertRunEntityToRunModel(RunEntity runEntity) {
        return new RunModel(runEntity.getUserId(), runEntity.getStartLatitude(), runEntity.getStartLongutude(),
                runEntity.getStartDateTime(), runEntity.getFinishLatitude(), runEntity.getFinishLongutude(),
                runEntity.getFinishDateTime(), runEntity.getDistance(), calculateAvgSpeed(runEntity.getDistance(),
                runEntity.getStartDateTime(), runEntity.getFinishDateTime()));
    }

    private int calculateAvgSpeed(int distance, Date startDateTime, Date finishDateTime) {
        return (distance/((int)(startDateTime.getTime()-finishDateTime.getTime())))/1000;
    }

    private class CurrentUserRun {
        private int startLatitude;
        private int startLongutude;
        private Date startDateTime;


        public CurrentUserRun(int startLatitude, int startLongutude, Date startDateTime) {
            this.startLatitude = startLatitude;
            this.startLongutude = startLongutude;
            this.startDateTime = startDateTime;
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
    }
}
