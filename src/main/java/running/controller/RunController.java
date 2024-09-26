package running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import running.entity.RunEntity;
import running.model.RunModel;
import running.model.UserStatistics;
import running.service.RunService;

import java.util.List;
import java.util.Optional;

@RestController
public class RunController {

    @Autowired
    protected RunService runService;

    @RequestMapping(method = RequestMethod.GET, value = "/startRun")
    public void startRun(int userId, @RequestParam(required = false) double startLatitude,
                              @RequestParam(required = false) double startLongitude, @RequestParam(required = false) String startDateTime) {
        runService.startRun(userId, startLatitude, startLongitude, startDateTime);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/finishRun")
    public RunEntity finishRun(int userId, @RequestParam double finishLatitude, @RequestParam double finishLongitude,
                          @RequestParam String finishDateTime, @RequestParam(required = false) int distance) {
        return runService.finishRun(userId, finishLatitude, finishLongitude, finishDateTime, Optional.of(distance));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllRuns")
    public List<RunModel> getAllRuns(int userId, @RequestParam(required = false) String startDateTimeFrom,
                                     @RequestParam(required = false) String startDateTimeTo) {
        return runService.getAllRuns(userId, Optional.of(startDateTimeFrom), Optional.of(startDateTimeTo));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserStatistics")
    public UserStatistics getUserStatistics(int userId, @RequestParam(required = false) String startDateTimeFrom, @RequestParam(required = false) String startDateTimeTo) {
        return runService.getUserStatistics(userId, Optional.of(startDateTimeFrom), Optional.of(startDateTimeTo));
    }
}
