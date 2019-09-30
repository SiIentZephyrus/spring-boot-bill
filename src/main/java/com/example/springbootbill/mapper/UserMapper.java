package com.example.springbootbill.mapper;

import com.example.springbootbill.entities.User;

import java.util.List;

public interface UserMapper {
    User getUserByUsernameAndPwd(User user);
    List<User> getUsers(User user);
    User getUserById(Integer id);
    int updateUser(User user);
    int addUser(User user);
    int deleteUser(Integer id);
}
