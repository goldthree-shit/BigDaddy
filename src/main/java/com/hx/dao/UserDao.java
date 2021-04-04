package com.hx.dao;

import com.hx.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    void saveUser(User user);
    User findUserbyName(String name);
}
