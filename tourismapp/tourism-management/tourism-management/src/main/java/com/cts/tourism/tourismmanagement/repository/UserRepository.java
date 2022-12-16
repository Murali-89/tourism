package com.cts.tourism.tourismmanagement.repository;

import com.cts.tourism.tourismmanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public UserEntity findByEmail(String userName);

}


