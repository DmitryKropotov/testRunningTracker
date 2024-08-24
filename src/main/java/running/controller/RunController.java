package running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import running.entity.RunEntity;
import running.service.RunService;

import java.util.Date;
import java.util.List;

@RestController
public class RunController {

    @Autowired
    private RunService runService;

    @RequestMapping(method = RequestMethod.GET)
    public void startRun(@RequestParam int userId, @RequestParam int startLatitude, @RequestParam int startLongitude, @RequestParam Date startDateTime) {

    }

    @RequestMapping(method = RequestMethod.GET)
    public void finishRun(@RequestParam int userId, @RequestParam int finishLatitude, @RequestParam int finishLongitude,
                          @RequestParam Date finishDateTime, @RequestParam(required = false) int distance) {

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RunEntity> getAllRuns(@RequestParam int userId, @RequestParam(required = false) Date startDateTimeFrom,
                                      @RequestParam(required = false) Date startDateTimeTo) {
        return List.of();
    }
}
