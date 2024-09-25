package running.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import running.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
