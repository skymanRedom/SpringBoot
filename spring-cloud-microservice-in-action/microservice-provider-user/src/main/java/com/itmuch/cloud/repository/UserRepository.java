package com.itmuch.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itmuch.cloud.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

}
