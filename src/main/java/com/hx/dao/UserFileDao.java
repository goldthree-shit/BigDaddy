package com.hx.dao;

import com.hx.pojo.UserFile;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserFileDao {
    void saveUserFile(UserFile userFile);
}
