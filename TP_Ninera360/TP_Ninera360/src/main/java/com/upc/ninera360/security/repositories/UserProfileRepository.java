package com.upc.ninera360.security.repositories;

import com.upc.ninera360.security.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository("securityUserProfileRepository")
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByDni(Integer dni);
}
