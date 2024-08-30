package running.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import running.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
