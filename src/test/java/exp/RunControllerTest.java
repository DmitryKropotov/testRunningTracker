package exp;

import exp.controller.RunControllerForTest;
import exp.service.RunServiceForTest;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;
import running.entity.RunEntity;
import running.entity.UserEntity;
import running.model.RunModel;
import running.model.UserStatistics;
import running.repository.RunRepository;
import running.repository.UserRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class RunControllerTest {

    private RunControllerForTest runController = new RunControllerForTest();

    private RunServiceForTest runService = new RunServiceForTest();

    @Before
    public void setUpBeforeClass() {
        runController.setRunService(runService);
    }


    @org.junit.Test
    public void testStartRunWithLittleLatitude() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->runController.startRun(1, -100, 40, "01.01.2024"));
        Assert.assertEquals("startLatitude must be between -90 and 90 degrees", ex.getMessage());
    }

    @org.junit.Test
    public void testStartRunWithBigLatitude() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->runController.startRun(1, 100, 40, "01.01.2024"));
        Assert.assertEquals("startLatitude must be between -90 and 90 degrees", ex.getMessage());
    }

    @org.junit.Test
    public void testStartRunWithLittleLongitude() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->runController.startRun(1, 20, -200, "01.01.2024"));
        Assert.assertEquals("startLongitude must be between -180 and 180 degrees", ex.getMessage());
    }

    @org.junit.Test
    public void testStartRunWithBigLongitude() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->runController.startRun(1, 20, 200, "01.01.2024"));
        Assert.assertEquals("startLongitude must be between -180 and 180 degrees", ex.getMessage());
    }

    @org.junit.Test
    public void testStartRunWithWrongUser() {
        RunRepository runRepository = Mockito.mock(RunRepository.class);
        runService.setRunRepository(runRepository);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        runService.setUserRepository(userRepository);
        RuntimeException ex = assertThrows(RuntimeException.class, ()->runController.startRun(1, 20, 40, "01.01.2024"));
        Assert.assertEquals("User with id 1 not found", ex.getMessage());
    }

    @org.junit.Test
    public void testFinishRunWithLittleLatitude() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->runController.finishRun(1, -100, 40, "01.01.2024", 1000));
        Assert.assertEquals("startLatitude must be between -90 and 90 degrees", ex.getMessage());
    }

    @org.junit.Test
    public void testFinishRunWithBigLatitude() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->runController.finishRun(1, 100, 40, "01.01.2024", 1000));
        Assert.assertEquals("startLatitude must be between -90 and 90 degrees", ex.getMessage());
    }

    @org.junit.Test
    public void testFinishRunWithLittleLongitude() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->runController.finishRun(1, 20, -200, "01.01.2024", 1000));
        Assert.assertEquals("startLongitude must be between -180 and 180 degrees", ex.getMessage());
    }

    @org.junit.Test
    public void testFinishRunWithBigLongitude() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->runController.finishRun(1, 20, 200, "01.01.2024", 1000));
        Assert.assertEquals("startLongitude must be between -180 and 180 degrees", ex.getMessage());
    }

    @org.junit.Test
    public void testFinishRunWithWrongUser() {
        RuntimeException ex = assertThrows(RuntimeException.class, ()->runController.finishRun(1, 20, 40, "01.01.2024", 2));
        Assert.assertEquals("Run of user with userId 1 was not started", ex.getMessage());
    }

    @org.junit.Test
    public void testFinishRunWithRightUser() {
        RunRepository runRepository = Mockito.mock(RunRepository.class);
        UserEntity user = new UserEntity(1, "Ivan", "Ivanov", LocalDate.of(2000, 1, 1), 'm');
        RunEntity run = new RunEntity(1, user, 20.0, 20.05, 40.0, 40.05, Instant.parse("2024-01-01T10:00:00Z"),
                Instant.parse("2024-01-01T10:30:00Z"), 2000);
        when(runRepository.save(Mockito.any())).thenReturn(run);
        runService.setRunRepository(runRepository);

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        runService.setUserRepository(userRepository);

        runController.startRun(1, 20, 40, "2024-01-01T10:00:00Z");
        assertEquals(run,runController.finishRun(1, 21, 41, "2024-01-01T10:30:00Z", 2));
    }

    @org.junit.Test
    public void testGetAllRunsOfUserWithoutRuns() {
        RunRepository runRepository = Mockito.mock(RunRepository.class);
        when(runRepository.findByUserId(1)).thenReturn(new ArrayList<>());
        runService.setRunRepository(runRepository);
        List<RunModel> runModels =  runController.getAllRuns(1,  "2024-01-01T00:00:00Z", "2024-01-30T23:59:59Z");
        Assert.assertEquals(0, runModels.size());
    }

    @org.junit.Test
    public void testGetAllRunsOfUserWithRuns() {
        List<RunEntity> runModels =  new ArrayList<>();
        UserEntity user = new UserEntity(1, "Ivan", "Ivanov", LocalDate.of(2000, 1, 1), 'm');
        RunEntity run1 = new RunEntity(1, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2023-07-13T12:00:00Z"),
                Instant.parse("2023-07-13T13:00:00Z"), 5000);
        RunEntity run2 = new RunEntity(2, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2023-12-31T23:30:00Z"),
                Instant.parse("2024-01-01T00:30:00Z"), 5000);
        RunEntity run3 = new RunEntity(3, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2024-01-15T12:00:00Z"),
                Instant.parse("2024-01-15T13:00:00Z"), 5000);
        RunEntity run4 = new RunEntity(4, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2024-01-20T12:00:00Z"),
                Instant.parse("2024-01-20T13:00:00Z"), 5000);
        RunEntity run5 = new RunEntity(5, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2024-01-31T23:30:00Z"),
                Instant.parse("2024-02-01T00:30:00Z"), 5000);
        RunEntity run6 = new RunEntity(6, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2024-02-20T12:00:00Z"),
                Instant.parse("2024-02-20T13:00:00Z"), 5000);
        runModels.add(run1);
        runModels.add(run2);
        runModels.add(run3);
        runModels.add(run4);
        runModels.add(run5);
        runModels.add(run6);
        RunRepository runRepository = Mockito.mock(RunRepository.class);
        when(runRepository.findByUserId(1)).thenReturn(runModels);
        runService.setRunRepository(runRepository);
        RunModel runModel3 = new RunModel(1, 20, 20.1, Instant.parse("2024-01-15T12:00:00Z"),
                20, 20.1, Instant.parse("2024-01-15T13:00:00Z"), 5000, 5000.0/3600.0);
        RunModel runModel4 = new RunModel(1, 20, 20.1, Instant.parse("2024-01-20T12:00:00Z"),
                20, 20.1, Instant.parse("2024-01-20T13:00:00Z"), 5000, 5000.0/3600.0);
        RunModel runModel5 = new RunModel(1, 20, 20.1, Instant.parse("2024-01-31T23:30:00Z"),
                20, 20.1, Instant.parse("2024-02-01T00:30:00Z"), 5000, 5000.0/3600.0);
        List<RunModel> runModelsResult =  runController.getAllRuns(1,  "2024-01-01T00:00:00Z", "2024-01-31T23:59:59Z");
        Assert.assertEquals(3, runModelsResult.size());
        Assert.assertTrue(runModelsResult.contains(runModel3));
        Assert.assertTrue(runModelsResult.contains(runModel4));
        Assert.assertTrue(runModelsResult.contains(runModel5));
    }

    @org.junit.Test
    public void testGetUserStatisticsNoRuns() {
        UserEntity user = new UserEntity(1, "Ivan", "Ivanov", LocalDate.of(2000, 1, 1), 'm');
        RunRepository runRepository = Mockito.mock(RunRepository.class);
        when(runRepository.findByUserId(1)).thenReturn(new ArrayList<>());
        runService.setRunRepository(runRepository);
        UserStatistics userStatistics = runController.getUserStatistics(1, "2024-01-01T00:00:00Z", "2024-01-31T23:59:59Z");
        assertEquals(0, userStatistics.getNumberOfRuns());
        assertEquals(0, userStatistics.getTotalNumberOfMeters());
        assertEquals(0, userStatistics.getAvgSpeed());
        assertEquals(Instant.parse("2024-01-01T00:00:00Z"), userStatistics.getStartDateTimeFrom());
        assertEquals(Instant.parse("2024-01-31T23:59:59Z"), userStatistics.getStartDateTimeTo());
    }

    @org.junit.Test
    public void testGetUserStatisticsRunsExist() {
        List<RunEntity> runModels =  new ArrayList<>();
        UserEntity user = new UserEntity(1, "Ivan", "Ivanov", LocalDate.of(2000, 1, 1), 'm');
        RunEntity run1 = new RunEntity(1, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2023-07-13T12:00:00Z"),
                Instant.parse("2023-07-13T13:00:00Z"), 1000);
        RunEntity run2 = new RunEntity(2, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2023-12-31T23:30:00Z"),
                Instant.parse("2024-01-01T00:30:00Z"), 2000);
        RunEntity run3 = new RunEntity(3, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2024-01-15T12:00:00Z"),
                Instant.parse("2024-01-15T13:00:00Z"), 3000);
        RunEntity run4 = new RunEntity(4, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2024-01-20T12:00:00Z"),
                Instant.parse("2024-01-20T13:00:00Z"), 4000);
        RunEntity run5 = new RunEntity(5, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2024-01-31T23:30:00Z"),
                Instant.parse("2024-02-01T00:30:00Z"), 5000);
        RunEntity run6 = new RunEntity(6, user, 20.0, 20.0, 20.1, 20.1, Instant.parse("2024-02-20T12:00:00Z"),
                Instant.parse("2024-02-20T13:00:00Z"), 6000);
        runModels.add(run1);
        runModels.add(run2);
        runModels.add(run3);
        runModels.add(run4);
        runModels.add(run5);
        runModels.add(run6);
        RunRepository runRepository = Mockito.mock(RunRepository.class);
        when(runRepository.findByUserId(1)).thenReturn(runModels);
        runService.setRunRepository(runRepository);
        UserStatistics userStatistics = runController.getUserStatistics(1, "2024-01-01T00:00:00Z", "2024-01-31T23:59:59Z");
        assertEquals(3, userStatistics.getNumberOfRuns());
        assertEquals(12000, userStatistics.getTotalNumberOfMeters());
        assertEquals(4000.0/3600.0, userStatistics.getAvgSpeed());
        assertEquals(Instant.parse("2024-01-01T00:00:00Z"), userStatistics.getStartDateTimeFrom());
        assertEquals(Instant.parse("2024-01-31T23:59:59Z"), userStatistics.getStartDateTimeTo());
    }
}
