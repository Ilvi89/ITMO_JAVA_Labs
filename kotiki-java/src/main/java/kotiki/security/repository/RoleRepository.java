package kotiki.security.repository;

import java.util.Optional;

import kotiki.security.entity.ERole;
import kotiki.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
