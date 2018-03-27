package com.olgaruban.dao;

import com.olgaruban.model.User;

import java.util.List;

public interface UserDao {
    int add(User user);
    boolean deleteById(int id);
    User update(User user);
    List<User> getList();
    void close();
}
