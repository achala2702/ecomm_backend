package com.achala2702.auth_server.repository;

import com.achala2702.auth_server.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<UserModel, Integer> {

    Boolean existsByEmail(String email);
}
