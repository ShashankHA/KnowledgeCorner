package com.learning.kc.repository;


import com.learning.kc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select count(*) from User u where u.userName = ?1")
     Long getCount(String name);

    @Query("select password from User u where u.userName = ?1")
     String getPassword(String userName);
}
