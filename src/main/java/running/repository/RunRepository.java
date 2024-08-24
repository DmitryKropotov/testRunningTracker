package running.repository;

import running.entity.RunEntity;
import org.springframework.data.repository.CrudRepository;

public interface RunRepository extends CrudRepository<RunEntity, Integer>{
}
