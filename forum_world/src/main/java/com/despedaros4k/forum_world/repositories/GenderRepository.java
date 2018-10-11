package com.despedaros4k.forum_world.repositories;

import com.despedaros4k.forum_world.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenderRepository extends JpaRepository<Gender, Long> {
    Optional<Gender> findByGenderName(String genderName);
}
