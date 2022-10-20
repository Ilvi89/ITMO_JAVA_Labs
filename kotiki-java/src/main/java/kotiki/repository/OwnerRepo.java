package kotiki.repository;

import kotiki.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepo extends JpaRepository<Owner, Long> {
    Optional<Owner> findByName(String name);
}
