package running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import running.model.RunModel;
import running.model.UserStatistics;
import running.service.RunService;

import java.util.List;
import java.util.Optional;

@RestController
public class RunController {

    @Autowired
    private RunService runService;

    @RequestMapping(method = RequestMethod.GET, value = "/startRun")
    public void startRun(int userId, @RequestParam(required = false) int startLatitude,
                         @RequestParam(required = false) int startLongitude, @RequestParam(required = false) String startDateTime) {
        runService.startRun(userId, startLatitude, startLongitude, startDateTime);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/finishRun")
    public void finishRun(int userId, @RequestParam int finishLatitude, @RequestParam int finishLongitude,
                          @RequestParam String finishDateTime, @RequestParam(required = false) int distance) {
        runService.finishRun(userId, finishLatitude, finishLongitude, finishDateTime, Optional.of(distance));
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
