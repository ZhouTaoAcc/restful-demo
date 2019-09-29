package com.kedacom.service.Impl;

import com.kedacom.dao.UserDao;
import com.kedacom.domain.User;
import com.kedacom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kedacom on 2019/8/2.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Override
    public User saveUser(User user) {
        return (User) userDao.save(user);
    }

    @Override
    public User updateUser(User user) {
        return (User) userDao.save(user);
    }

    @Override
    public User getById(int id) {
        return (User) userDao.getOne(id);
    }

    @Override
    public List<User> queryAll() {
        return userDao.findAll();
    }
    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }
}
