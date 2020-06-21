package com.tw.homework.springbootuserservice.repository;

import com.tw.homework.springbootuserservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID >, JpaSpecificationExecutor<User> {
}
