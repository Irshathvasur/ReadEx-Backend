package com.app.bookEx.service;
import com.app.bookEx.domain.pojo.UserDetails;
import java.util.List;

public interface UserDetailsInterface {
    List<UserDetails> getAllUsers();
    String saveUser(String body);

    String getUser(String body);
}

