package running.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import running.entity.Run;

import java.util.List;

public interface RunRepository extends JpaRepository<Run, Integer> {
    List<Run> findByUserId(int userId);
}
