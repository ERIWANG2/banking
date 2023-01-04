package rdc.ericwangi.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rdc.ericwangi.banking.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByNomRole(String nomRole);
}
