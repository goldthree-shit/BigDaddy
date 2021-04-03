package com.hx.service;

import com.hx.Dao.UserDao;
import com.hx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }
    @Override
    public User findUserbyName(String name) {
        return userDao.findUserbyName(name);
    }
}
