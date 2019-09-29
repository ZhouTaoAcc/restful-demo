package com.kedacom.service;

import com.kedacom.domain.User;

import java.util.List;

/**
 * Created by kedacom on 2019/8/2.
 */
public interface UserService{
    Object saveUser(User user);

    User updateUser(User user);

    User getById(int id);

    List<User> queryAll();

    void deleteById(int id);
}
