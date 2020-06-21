package com.tw.homework.springbootuserservice.service;

import com.tw.homework.springbootuserservice.model.User;
import com.tw.homework.springbootuserservice.model.UserQuery;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void save(User user);
    void delete(String id);
    void update(User user);
    User get(String id);
    Page<User> findUsers(Integer page, Integer size);
    Page<User> findUsersBy(Integer page, Integer size, UserQuery userQuery);
}
