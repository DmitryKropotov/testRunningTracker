package running.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import running.entity.RunEntity;

import java.util.List;

public interface RunRepository extends JpaRepository<RunEntity, Integer> {
    List<RunEntity> findByUserId(int userId);
}
