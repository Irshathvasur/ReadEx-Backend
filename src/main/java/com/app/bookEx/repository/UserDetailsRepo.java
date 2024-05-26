package com.app.bookEx.repository;

import com.app.bookEx.domain.pojo.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDetailsRepo extends JpaRepository<UserDetails,Integer> {

        @Query("Select e from UserDetails e")
        List<UserDetails> getAllUsers();

        @Query("select max(id) from UserDetails")
        Integer getUserCount();

        @Query("select count(e.username) from UserDetails e where e.username=:userId")
        Integer checkUserExist(String userId);

        @Query("SELECT e FROM UserDetails e WHERE e.username =:userName AND e.userpassword =:userPassword")
        UserDetails validateUser(String userName,String userPassword);
}

