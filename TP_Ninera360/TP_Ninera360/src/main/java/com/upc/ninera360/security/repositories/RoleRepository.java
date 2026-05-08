package com.upc.ninera360.security.repositories;
import com.upc.ninera360.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
