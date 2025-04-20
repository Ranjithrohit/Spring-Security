package com.ranjith.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ranjith.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByEmail(String username);

}
