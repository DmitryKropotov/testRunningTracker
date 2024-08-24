package running.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import running.entity.RunEntity;
import running.model.RunModel;
import running.repository.RunRepository;

import java.util.Date;
import java.util.List;

@Service
public class RunServiceImpl implements RunService {

    @Autowired
    private RunRepository runRepository;

    @Override
    public List<RunModel> getAllRuns(int userId, Date startDateTimeFrom, Date startDateTimeTo) {
        List<RunEntity> runEntities = runRepository.findByUserId(userId);
        return runEntities.stream().filter(runEntity -> runEntity.getStartDateTime().after(startDateTimeFrom) &&
                runEntity.getStartDateTime().before(startDateTimeTo) ||
                runEntity.getStartDateTime().equals(startDateTimeFrom) ||
                runEntity.getStartDateTime().equals(startDateTimeTo)).map(runEntity -> );
    }

    private RunModel convertRunEntityToRunModel(RunEntity runEntity) {
        return new RunModel(runEntity.getUserId(), runEntity.getStartLatitude(), runEntity.getStartLongutude(),
                runEntity.getStartDateTime(), runEntity.getFinishLatitude(), runEntity.getFinishLongutude(),
                runEntity.getFinishDateTime(), runEntity.getDistance(), calculateAvgSpeed());
    }

    private int calculateAvgSpeed() {
        return 0;
    }
}
