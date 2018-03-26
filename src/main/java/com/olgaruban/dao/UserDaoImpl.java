package com.olgaruban.dao;

import com.olgaruban.model.User;
import com.olgaruban.service.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private final Connection connection;

    public UserDaoImpl() {
        final Settings settings = Settings.getInstance();
        try {
            Class.forName(settings.value("jdbc.driver_class"));
            this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean add(User user) {
        return false;
    }

    public boolean deleteById(int id) {
        return false;
    }

    public User update(User user) {
        return null;
    }

    public List<User> getList() {
        return null;
    }

    public void close() {

    }
}
