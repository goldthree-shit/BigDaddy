package com.hx.service;

import com.hx.pojo.User;

public interface UserService {
    void saveUser(User user);
    User findUserbyName(String name);
}
