package com.application.SeckillingSystem.BaseModule.dao;

import com.application.SeckillingSystem.BaseModule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author PG
 */

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query(value = "select * from user where user_id=?1 limit 1", nativeQuery = true)
    User queryDistinctByUserId(String userId);
}
