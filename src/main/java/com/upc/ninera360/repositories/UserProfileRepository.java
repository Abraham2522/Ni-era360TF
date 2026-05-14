package com.upc.ninera360.repositories;

import com.upc.ninera360.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByDni(Integer dni);
    public long count();
    public List<UserProfile> findByNombre(String nombre);

}
